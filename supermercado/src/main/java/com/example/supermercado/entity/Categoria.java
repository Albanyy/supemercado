package com.example.supermercado.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Entity 
@Table (name = "Categoria")
public class Categoria {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long  id;

    @Column (name = "nombre")
    private String nombre;

    @Column (name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos; 

}
