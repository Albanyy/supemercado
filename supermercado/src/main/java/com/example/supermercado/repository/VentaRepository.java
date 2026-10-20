package com.example.supermercado.repository;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.supermercado.entity.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByClienteId(Long clienteId);

    List<Venta> findByFechaVentaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
