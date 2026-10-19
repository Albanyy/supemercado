package com.example.supermercado.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.supermercado.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findFirstByCodigoBarras(String codigoBarras);

    List<Producto> findByCategoriaId(Long categoriaId);

    List<Producto> findByStockLessThanEqual(Integer stock);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}
