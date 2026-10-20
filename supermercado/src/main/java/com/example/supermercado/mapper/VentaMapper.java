package com.example.supermercado.mapper;

import java.util.stream.Collectors;

import com.example.supermercado.dto.Venta.VentaRequestDTO;
import com.example.supermercado.dto.Venta.VentaResponseDTO;
import com.example.supermercado.entity.Venta;

public class VentaMapper {

    /* Convierte a Venta a VentaResponseDTO */
    public static VentaResponseDTO toResponseDTO(Venta venta) {
        if (venta == null) {
            return null;
        }

        VentaResponseDTO response = new VentaResponseDTO();
        response.setId(venta.getId());
        response.setFechaVenta(venta.getFechaVenta());
        response.setTotal(venta.getTotal());

        if (venta.getCliente() != null) {
            response.setClienteId(venta.getCliente().getId());
            response.setClienteNombre(venta.getCliente().getNombre());
        }

        if (venta.getDetalleVentas() != null) {
            response.setDetalleVenta(venta.getDetalleVentas().stream().map(DetalleVentaMapper::toResponseDTO)
                    .collect(Collectors.toList()));
        }

        return response;
    }

    /* VentaRequestDTO pasa a Venta */
    public static Venta toEntity(VentaRequestDTO request) {
        if (request == null) {
            return null;
        }

        Venta venta = new Venta();

        if (request.getDetalleVenta() != null) {
            venta.setDetalleVentas(request.getDetalleVenta().stream().map(detalle -> DetalleVentaMapper.toEntity(detalle)).collect(Collectors.toList()));
        }

        return venta;

    }

}
