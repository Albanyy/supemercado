package com.example.supermercado.service;

import com.example.supermercado.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.supermercado.dto.Producto.ProductoRequestDTO;
import com.example.supermercado.dto.Producto.ProductoResponseDTO;
import com.example.supermercado.entity.Categoria;
import com.example.supermercado.entity.Producto;
import com.example.supermercado.entity.Proveedor;
import com.example.supermercado.excepciones.ResourceNotFoundException;
import com.example.supermercado.mapper.ProductoMapper;
import com.example.supermercado.repository.ProductoRepository;
import com.example.supermercado.repository.ProveedorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProveedorRepository proveedorRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    /* Crea un producto */
    public ProductoResponseDTO crearProducto(ProductoRequestDTO request) {

        Optional<Producto> productoFound = productoRepository.findFirstByCodigoBarras(request.getCodigoBarras());

        if (productoFound.isPresent()) {
            throw new IllegalStateException(
                    "Ya existe un producto con ese código de barras");
        }

        Categoria categoriaFound = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("No existe la categoría"));

        Proveedor proveedorFound = proveedorRepository.findById(request.getProveedorId())
                .orElseThrow(() -> new ResourceNotFoundException("No existe este proveedor"));

        Producto producto = ProductoMapper.toEntity(request);

        producto.setCategoria(categoriaFound);
        producto.setProveedor(proveedorFound);

        Producto productoGuardado = productoRepository.save(producto);

        return ProductoMapper.toResponseDTO(productoGuardado);

    }

    /* Trae todos los productos */

    public List<ProductoResponseDTO> getAllProductos() {

        return productoRepository.findAll().stream().map(producto -> ProductoMapper.toResponseDTO(producto))
                .collect(Collectors.toList());

    }

    /* Actualiza a un producto */

    public ProductoResponseDTO updateProducto(Long productoId, ProductoRequestDTO request) {

        Optional<Producto> codigoFound = productoRepository.findFirstByCodigoBarras(request.getCodigoBarras());

        if (codigoFound.isPresent()&& !codigoFound.get().getId().equals(productoId)) {
            throw new IllegalStateException(
                    "Ya existe un producto con ese código de barras");
        }

        Producto productoFound = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el producto"));

        Categoria categoriaFound = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("No existe la categoría"));

        Proveedor proveedorFound = proveedorRepository.findById(request.getProveedorId())
                .orElseThrow(() -> new ResourceNotFoundException("No existe este proveedor"));

        productoFound.setNombre(request.getNombre());
        productoFound.setPrecio(request.getPrecio());
        productoFound.setStock(request.getStock());
        productoFound.setCodigoBarras(request.getCodigoBarras());
        productoFound.setCategoria(categoriaFound);
        productoFound.setProveedor(proveedorFound);

        Producto productoActualizado = productoRepository.save(productoFound);

        return ProductoMapper.toResponseDTO(productoActualizado);
    }

    /* Trae un producto por id */

    public ProductoResponseDTO getProducto(Long productoId) {
        Producto productoFound = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el producto"));

        return ProductoMapper.toResponseDTO(productoFound);
    }

    /* Lista los productos de una categoria */

    public List<ProductoResponseDTO> getProductoCategoria(Long categoriaId) {
        if (!categoriaRepository.existsById(categoriaId)) {
            throw new ResourceNotFoundException("No existe la categoría");
        }

        return productoRepository.findByCategoriaId(categoriaId).stream()
                .map(producto -> ProductoMapper.toResponseDTO(producto)).collect(Collectors.toList());

    }

    /* Trae un producto por el nombre */

    public List<ProductoResponseDTO> getProductoNombre(String productoNombre) {

        return productoRepository.findByNombreContainingIgnoreCase(productoNombre).stream().map(producto -> ProductoMapper.toResponseDTO(producto)).collect(Collectors.toList());
    }

    /* Lista los productos con menor o igual al limite indicado */

    public List<ProductoResponseDTO> getProductoStock(Integer stock) {

        return productoRepository.findByStockLessThanEqual(stock).stream().map(producto -> ProductoMapper.toResponseDTO(producto)).collect(Collectors.toList());

    }

    /* Eliminar un producto */

    public void deleteProducto(Long productoId) {
        if (!productoRepository.existsById(productoId)) {
            throw new ResourceNotFoundException("No existe el producto");
        }

        productoRepository.deleteById(productoId);
    }

}
