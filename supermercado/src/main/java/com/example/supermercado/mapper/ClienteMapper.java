package com.example.supermercado.mapper;

import com.example.supermercado.dto.Cliente.ClienteRequestDTO;
import com.example.supermercado.dto.Cliente.ClienteResponseDTO;
import com.example.supermercado.entity.Cliente;

public class ClienteMapper {
    /* Convierte a Cliente a ClienteResponseDTO */
    public static ClienteResponseDTO toResponseDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setId(cliente.getId());
        response.setNombre(cliente.getNombre());
        response.setDocumento(cliente.getDocumento());
        response.setTelefono(cliente.getTelefono());

        return response;

    }

    /* ClienteRequestDTO pasa a Cliente */
    public static Cliente toEntity(ClienteRequestDTO request) {
        if (request == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        cliente.setNombre(request.getNombre());
        cliente.setDocumento(request.getDocumento());
        cliente.setTelefono(request.getTelefono());

        return cliente;
    }

}
