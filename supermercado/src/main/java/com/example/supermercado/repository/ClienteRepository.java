package com.example.supermercado.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.supermercado.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByDocumento(String documento);

    boolean existsByDocumentoAndIdNot(String documento, Long clienteId);

    Optional<Cliente> findByDocumento(String documento);

}
