package com.example.supermercado.dto.Proveedor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data 
public class ProveedorRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @Size(max = 20, message = "El teléfono no puede tener más de 20 caracteres")
    private String telefono;

    @NotBlank(message = "El correo es obligatorio")
    @Email (message = "El correo debe ser válido")
    @Size(max = 150, message = "El correo no puede tener más de 150 caracteres")
    private String correo;
}
