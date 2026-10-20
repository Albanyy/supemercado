package com.example.supermercado.dto.Venta;

import java.util.List;
import com.example.supermercado.dto.DetalleVenta.DetalleVentaRequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VentaRequestDTO {

    @NotNull(message = "La venta debe tener un cliente")
    private Long clienteId;

    @NotEmpty(message = "La venta debe tener detalles")
    private List<@Valid DetalleVentaRequestDTO> detalleVenta;
}
