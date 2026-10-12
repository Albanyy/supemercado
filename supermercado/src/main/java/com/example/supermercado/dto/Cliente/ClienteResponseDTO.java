package com.example.supermercado.dto.Cliente;

import lombok.Data;

@Data 
public class ClienteResponseDTO {

    private Long id;

    private String nombre;

    private String documento;

    private String telefono;

}
