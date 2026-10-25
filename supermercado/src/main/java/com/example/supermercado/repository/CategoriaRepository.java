package com.example.supermercado.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.supermercado.entity.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsByNombre(String nombre);

    boolean existsByNombreAndIdNot(String nombre, Long id);

}

