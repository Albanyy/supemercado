package com.example.supermercado.dto.Producto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductoRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que cero")
    private BigDecimal precio;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @NotBlank(message = "El código de barras del producto no puede estar vacío")
    @Size(max = 50, message = "El código de barras del producto no puede tener más de 50 caracteres")
    private String codigoBarras;

    @NotNull(message = "La categoría del producto no puede estar vacía")
    @Positive(message = "El ID de la categoría debe ser mayor que cero")
    private Long categoriaId;

    @NotNull(message = "El proveedor del producto no puede estar vacío")
    @Positive(message = "El ID del proveedor debe ser mayor que cero")
    private Long proveedorId;
}
