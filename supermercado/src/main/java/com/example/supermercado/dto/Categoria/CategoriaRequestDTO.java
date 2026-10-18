package com.example.supermercado.dto.Categoria;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data 
public class CategoriaRequestDTO {
    
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    private String descripcion;
}
