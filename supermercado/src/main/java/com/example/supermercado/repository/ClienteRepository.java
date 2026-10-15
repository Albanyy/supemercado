package com.example.supermercado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.supermercado.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findFirstByNombre(String nombre);    
}
