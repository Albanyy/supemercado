package com.example.supermercado.dto.Venta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


import com.example.supermercado.dto.DetalleVenta.DetalleVentaResponseDTO;

import lombok.Data;

@Data 
public class VentaResponseDTO {

    private Long id;

    private LocalDateTime fechaVenta;

    private BigDecimal total;

    private Long clienteId;

    private String clienteNombre;

    private List<DetalleVentaResponseDTO> detalleVenta;
}
