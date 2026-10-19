package com.example.supermercado.dto.Producto;

import java.math.BigDecimal;


import lombok.Data;

@Data 
public class ProductoResponseDTO {

    private Long id;

    private String nombre;
    
    private BigDecimal precio;

    private Integer stock;

    private String codigoBarras;

    private Long categoriaId;

    private String categoriaNombre;

    private Long proveedorId;

    private String proveedorNombre;

}
