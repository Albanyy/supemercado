package com.example.supermercado.mapper;

import com.example.supermercado.dto.Proveedor.ProveedorRequestDTO;
import com.example.supermercado.dto.Proveedor.ProveedorResponseDTO;
import com.example.supermercado.entity.Proveedor;

public class ProveedorMapper {

    /*Convierte a Proveedor a ProveedorResponseDTO */
    public static ProveedorResponseDTO toResponseDTO(Proveedor proveedor){
        if(proveedor == null){
            return null;
        }
        ProveedorResponseDTO response = new ProveedorResponseDTO();
        response.setId(proveedor.getId());
        response.setNombre(proveedor.getNombre());
        response.setTelefono(proveedor.getTelefono());
        response.setCorreo(proveedor.getCorreo());

        return response;
    }

    /*ProveedorRequestDTO pasa a Proveedor */
    public static Proveedor toEntity(ProveedorRequestDTO request){
        if (request == null) {
            return null;
        }

        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(request.getNombre());
        proveedor.setTelefono(request.getTelefono());
        proveedor.setCorreo(request.getCorreo());

        return proveedor;

    }
}
