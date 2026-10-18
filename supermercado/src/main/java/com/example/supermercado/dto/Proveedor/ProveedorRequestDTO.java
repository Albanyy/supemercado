package com.example.supermercado.dto.Proveedor;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data 
public class ProveedorRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String telefono;

    private String correo;
}
