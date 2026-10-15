package com.example.supermercado.mapper;


import com.example.supermercado.dto.Categoria.CategoriaRequestDTO;
import com.example.supermercado.dto.Categoria.CategoriaResponseDTO;
import com.example.supermercado.entity.Categoria;


public class CategoriaMapper {
    
    /*Convierte a Categoria a CategoriaResponseDTO */
    public static CategoriaResponseDTO toResponseDTO(Categoria categoria){
        if (categoria == null) {
            return null;
        }
        CategoriaResponseDTO response = new CategoriaResponseDTO();
        response.setId(categoria.getId());
        response.setNombre(categoria.getNombre());
        response.setDescripcion(categoria.getDescripcion());
        
        return response;

    }

    /*CategoriaRequesDTO pasa Categoria */
    public static Categoria toEntity(CategoriaRequestDTO request){
        if (request == null) {
            return null;
        }

        Categoria categoria = new Categoria();
        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());

        return categoria;

    }



}
