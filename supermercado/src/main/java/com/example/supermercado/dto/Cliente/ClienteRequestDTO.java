package com.example.supermercado.dto.Cliente;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String documento;

    private String telefono;
    
}
