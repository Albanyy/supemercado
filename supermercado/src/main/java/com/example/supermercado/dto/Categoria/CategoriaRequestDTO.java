package com.example.supermercado.dto.Categoria;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data 
public class CategoriaRequestDTO {
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size (max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;
    
    @Size (max = 255, message = "La descripción no puede tener más de 255 caracteres")
    private String descripcion;
}
