package com.example.supermercado.dto.Cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @NotBlank(message = "El documento es obligatorio")
    @Size (max = 20, message = "El documento no puede tener más de 20 caracteres")
    private String documento;

    @Size (max = 15, message = "El teléfono no puede tener más de 15 caracteres")
    private String telefono;
    
}
