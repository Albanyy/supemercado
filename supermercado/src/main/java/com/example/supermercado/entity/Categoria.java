package com.example.supermercado.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la categoría no puede estar vacío")
    @Size(max = 100, message = "El nombre de la categoría no puede tener más de 100 caracteres")
    @Column(nullable = false, length = 100, name = "nombre")
    private String nombre;

    @Size (max = 255, message = "La descripción de la categoría no puede tener más de 255 caracteres")
    @Column(name = "descripcion", length = 255)
    private String descripcion;

}
