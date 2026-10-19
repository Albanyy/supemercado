package com.example.supermercado.mapper;

import com.example.supermercado.dto.Producto.ProductoRequestDTO;
import com.example.supermercado.dto.Producto.ProductoResponseDTO;
import com.example.supermercado.entity.Producto;

import lombok.Data;

@Data 
public class ProductoMapper {

    /* Convierte a Producto a ProductoResponseDTO */
    public static ProductoResponseDTO toResponseDTO(Producto producto) {
        if (producto == null) {
            return null;
        }

        ProductoResponseDTO response = new ProductoResponseDTO();
        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setPrecio(producto.getPrecio());
        response.setStock(producto.getStock());
        response.setCodigoBarras(producto.getCodigoBarras());

        if (producto.getCategoria() != null) {
            response.setCategoriaId(producto.getCategoria().getId());
            response.setCategoriaNombre(producto.getCategoria().getNombre());
        }

        if (producto.getProveedor() != null) {
            response.setProveedorId(producto.getProveedor().getId());
            response.setProveedorNombre(producto.getProveedor().getNombre());
        }

        return response;
    }

    /* ProductoRequestDTO pasa a Producto */
    public static Producto toEntity(ProductoRequestDTO request) {
        if (request == null) {
            return null;
        }

        Producto producto = new Producto();

        producto.setNombre(request.getNombre());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        producto.setCodigoBarras(request.getCodigoBarras());

        return producto;

    }

}
