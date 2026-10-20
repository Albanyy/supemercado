package com.example.supermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.supermercado.entity.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long>{

    
} 
