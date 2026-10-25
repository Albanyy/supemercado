package com.example.supermercado.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.example.supermercado.dto.DetalleVenta.DetalleVentaRequestDTO;
import com.example.supermercado.dto.Venta.VentaRequestDTO;
import com.example.supermercado.dto.Venta.VentaResponseDTO;
import com.example.supermercado.entity.Cliente;
import com.example.supermercado.entity.DetalleVenta;
import com.example.supermercado.entity.Producto;
import com.example.supermercado.entity.Venta;
import com.example.supermercado.excepciones.ResourceNotFoundException;
import com.example.supermercado.mapper.DetalleVentaMapper;
import com.example.supermercado.mapper.VentaMapper;
import com.example.supermercado.repository.ClienteRepository;
import com.example.supermercado.repository.ProductoRepository;
import com.example.supermercado.repository.VentaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;

    /* Bloquea los productos en orden de id para evitar deadlocks */
    private void bloquearProductos(Set<Long> productosIds) {

        List<Long> idsOrdenados = new ArrayList<>(productosIds);
        Collections.sort(idsOrdenados);

        for (Long productoId : idsOrdenados) {
            productoRepository.findByIdForUpdate(productoId)
                    .orElseThrow(() -> new ResourceNotFoundException("No existe el producto"));
        }
    }

    /* Crea a detalleVenta y calcula el total */

    private BigDecimal creaDetallesYCalcularTotal(Venta venta, List<DetalleVentaRequestDTO> request,
            List<DetalleVenta> detallesVenta) {

        BigDecimal totalDeLaVenta = BigDecimal.ZERO;

        for (DetalleVentaRequestDTO detalleRequest : request) {

            if (detalleRequest.getCantidad() == null || detalleRequest.getCantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
            }

            Producto productoFound = productoRepository.findById(detalleRequest.getProductoId())
                    .orElseThrow(() -> new ResourceNotFoundException("No existe el producto"));

            if (productoFound.getStock() < detalleRequest.getCantidad()) {
                throw new IllegalArgumentException("No hay suficiente stock");
            }

            productoFound.setStock(productoFound.getStock() - detalleRequest.getCantidad());

            DetalleVenta detalleVenta = DetalleVentaMapper.toEntity(detalleRequest);
            detalleVenta.setProducto(productoFound);
            detalleVenta.setVenta(venta);

            BigDecimal precioDelProducto = productoFound.getPrecio();
            Integer cantidadComprada = detalleVenta.getCantidad();
            BigDecimal subTotalDelDetalle = calcularSubTotal(precioDelProducto, cantidadComprada);

            detalleVenta.setPrecioUnitario(precioDelProducto);
            detalleVenta.setSubTotal(subTotalDelDetalle);

            totalDeLaVenta = totalDeLaVenta.add(subTotalDelDetalle);
            detallesVenta.add(detalleVenta);
        }

        return totalDeLaVenta;
    }

    private BigDecimal calcularSubTotal(BigDecimal precioUnitario, Integer cantidad) {
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }

    /* Crea una Venta */

    @Transactional
    public VentaResponseDTO crearVenta(VentaRequestDTO request) {

        if (request.getDetalleVenta() == null || request.getDetalleVenta().isEmpty()) {
            throw new IllegalArgumentException("La venta debe tener al menos un detalle");
        }

        Cliente clienteFound = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("No existe cliente"));

        Venta venta = VentaMapper.toEntity(request);

        Set<Long> productosIds = new HashSet<>();

        for (DetalleVentaRequestDTO detalle : request.getDetalleVenta()) {
            productosIds.add(detalle.getProductoId());
        }

        bloquearProductos(productosIds);

        List<DetalleVenta> detalleVenta = new ArrayList<>();
        BigDecimal total = creaDetallesYCalcularTotal(
                venta, request.getDetalleVenta(), detalleVenta);

        venta.setCliente(clienteFound);
        venta.setDetalleVentas(detalleVenta);
        venta.setTotal(total);

        Venta ventaGuardada = ventaRepository.save(venta);
        return VentaMapper.toResponseDTO(ventaGuardada);
    }

    /* Trae todas las ventas */

    public List<VentaResponseDTO> getAllVentas() {

        return ventaRepository.findAll().stream().map(VentaMapper::toResponseDTO).collect(Collectors.toList());

    }

    /* Trae una venta por id */

    public VentaResponseDTO getVenta(Long ventaId) {
        Venta venta = ventaRepository.findById(ventaId)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la venta"));
        return VentaMapper.toResponseDTO(venta);
    }

    /* Actualiza venta */

    @Transactional
    public VentaResponseDTO updateVenta(Long ventaId, VentaRequestDTO request) {

        Venta ventaFound = ventaRepository.findById(ventaId)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la venta"));

        Cliente clienteFound = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("No existe cliente"));

        if (request.getDetalleVenta() == null || request.getDetalleVenta().isEmpty()) {
            throw new IllegalArgumentException("La venta debe tener al menos un detalle");
        }

        /* Junta los ids de los productos viejos y los nuevos */
        Set<Long> productosIds = new HashSet<>();

        for (DetalleVenta detalleAnterior : ventaFound.getDetalleVentas()) {
            productosIds.add(detalleAnterior.getProducto().getId());
        }

        for (DetalleVentaRequestDTO detalleNuevo : request.getDetalleVenta()) {
            productosIds.add(detalleNuevo.getProductoId());
        }

        bloquearProductos(productosIds);

        for (DetalleVenta detalleAnterior : ventaFound.getDetalleVentas()) {
            Producto productoAnterior = detalleAnterior.getProducto();
            productoAnterior.setStock(productoAnterior.getStock() + detalleAnterior.getCantidad());
        }

        ventaFound.getDetalleVentas().clear();

        BigDecimal total = creaDetallesYCalcularTotal(ventaFound, request.getDetalleVenta(),
                ventaFound.getDetalleVentas());

        ventaFound.setCliente(clienteFound);
        ventaFound.setTotal(total);

        Venta ventaActualizada = ventaRepository.save(ventaFound);
        return VentaMapper.toResponseDTO(ventaActualizada);
    }

    /* Trae venta entre dos fechas */
    public List<VentaResponseDTO> getVentasEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }

        return ventaRepository.findByFechaVentaBetween(fechaInicio.atStartOfDay(), fechaFin.atTime(LocalTime.MAX))
                .stream().map(VentaMapper::toResponseDTO).collect(Collectors.toList());

    }

    /* Trae las ventas de un cliente */

    public List<VentaResponseDTO> getVentasCliente(Long clienteId) {

        if (!clienteRepository.existsById(clienteId)) {
            throw new ResourceNotFoundException("No existe cliente");
        }

        return ventaRepository.findByClienteId(clienteId).stream().map(VentaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /* Elimina una venta */

    @Transactional
    public void deleteVenta(Long ventaId) {

        Venta ventaFound = ventaRepository.findById(ventaId)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la venta"));

        Set<Long> productosIds = new HashSet<>();

        for (DetalleVenta detalle : ventaFound.getDetalleVentas()) {
            productosIds.add(detalle.getProducto().getId());
        }

        bloquearProductos(productosIds);

        for (DetalleVenta detalle : ventaFound.getDetalleVentas()) {
            Producto producto = detalle.getProducto();
            producto.setStock(producto.getStock() + detalle.getCantidad());
        }

        ventaRepository.delete(ventaFound);
    }

}
