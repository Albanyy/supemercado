package com.example.supermercado.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@Entity 
@Table (name = "Cliente")
public class Cliente {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @NotBlank (message = "El nombre del cliente no puede estar vacío")
    @Size (max = 100, message = "El nombre del cliente no puede tener más de 100 caracteres")
    @Column (nullable = false, length = 100, name = "nombre")
    private String nombre;

    @NotBlank (message = "El documento del cliente no puede estar vacío")
    @Size (max = 20, message = "El documento del cliente no puede tener más de 20 caracteres")
    @Column (nullable = false, length = 20, name = "documento")
    private String documento;

    @Size (max = 15, message = "El teléfono no puede tener más de 15 caracteres")
    @Column (name = "telefono", length = 15)
    private String telefono;
}
