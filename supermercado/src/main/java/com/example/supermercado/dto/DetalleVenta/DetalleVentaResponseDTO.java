package com.example.supermercado.dto.DetalleVenta;

import java.math.BigDecimal;

import com.example.supermercado.entity.Producto;
import com.example.supermercado.entity.Venta;

import lombok.Data;

@Data 
public class DetalleVentaResponseDTO {

    private Long id;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    private  BigDecimal subTotal;

    private Venta venta;

    private Producto producto;

}
