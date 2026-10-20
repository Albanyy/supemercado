package com.example.supermercado.mapper;

import com.example.supermercado.dto.DetalleVenta.DetalleVentaRequestDTO;
import com.example.supermercado.dto.DetalleVenta.DetalleVentaResponseDTO;
import com.example.supermercado.entity.DetalleVenta;

public class DetalleVentaMapper {
    
    /*Convierte a DetalleVenta a DetalleVenteResponseDTO */
    public static DetalleVentaResponseDTO toResponseDTO(DetalleVenta detalleVenta){
        if (detalleVenta == null) {
            return null;
        }

        DetalleVentaResponseDTO response = new DetalleVentaResponseDTO();
        response.setId(detalleVenta.getId());
        response.setCantidad(detalleVenta.getCantidad());
        response.setPrecioUnitario(detalleVenta.getPrecioUnitario());
        response.setSubTotal(detalleVenta.getSubTotal());

        if (detalleVenta.getVenta()!= null) {
            response.setVentaId(detalleVenta.getVenta().getId());
        }
        
        if (detalleVenta.getProducto() != null) {
            response.setProductoId(detalleVenta.getProducto().getId());
            response.setProductoNombre(detalleVenta.getProducto().getNombre());
        }

        return response;
    }

    /*DetalleVentaRequestDTO pasa a DetalleVenta */
    public static DetalleVenta toEntity(DetalleVentaRequestDTO request){
        if (request  == null) {
            return null;
        }

        DetalleVenta detalleVenta = new DetalleVenta();

        detalleVenta.setCantidad(request.getCantidad());

        return detalleVenta;

    }
}
