package com.example.supermercado.dto.DetalleVenta;



import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data 
public class DetalleVentaRequestDTO {
    
    @NotNull (message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    @NotNull (message = "El ID del producto es obligatorio")
    private Long productoId;

}
