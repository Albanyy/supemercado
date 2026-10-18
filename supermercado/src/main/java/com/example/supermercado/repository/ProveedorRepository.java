package com.example.supermercado.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.supermercado.entity.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    
    Optional<Proveedor> findFirstByNombre(String nombre);

}
