package com.example.supermercado.dto.Cliente;

import lombok.Data;

@Data
public class ClienteRequestDTO {
    private String nombre;

    private String documento;

    private String telefono;
}
