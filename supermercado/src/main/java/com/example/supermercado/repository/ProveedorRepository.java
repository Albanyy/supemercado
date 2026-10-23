package com.example.supermercado.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.supermercado.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    
    boolean existsByNombre(String nombre);

    boolean existByNombreAndIdNot(String nombre, Long proveedorId);

}
