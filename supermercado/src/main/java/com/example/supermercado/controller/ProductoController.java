package com.example.supermercado.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.supermercado.dto.Producto.ProductoRequestDTO;
import com.example.supermercado.dto.Producto.ProductoResponseDTO;
import com.example.supermercado.service.ProductoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/producto")
public class ProductoController {
    private final ProductoService productoService;

    /* Crea un producto */

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody ProductoRequestDTO request) {
        ProductoResponseDTO response = productoService.crearProducto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /* Trae todos los productos */

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> getAllProductos() {
        List<ProductoResponseDTO> response = productoService.getAllProductos();
        return ResponseEntity.ok(response);
    }

    /* Actualiza a un producto */

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> updateProducto(@PathVariable("id") Long productoId,
            @Valid @RequestBody ProductoRequestDTO request) {
        ProductoResponseDTO response = productoService.updateProducto(productoId, request);
        return ResponseEntity.ok(response);
    }

    /* Trae un producto por id */

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> getProducto(@PathVariable("id") Long productoId) {
        ProductoResponseDTO response = productoService.getProducto(productoId);
        return ResponseEntity.ok(response);
    }

    /* Lista los productos de una categoria */

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoResponseDTO>> getProductoCategoria(@PathVariable("categoriaId") Long categoriaId) {

        List<ProductoResponseDTO> response = productoService.getProductoCategoria(categoriaId);
        return ResponseEntity.ok(response);

    }

    /* Trae un producto por el nombre */

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<ProductoResponseDTO>> getProductoNombre(@PathVariable("nombre") String nombre) {
        List<ProductoResponseDTO> response = productoService.getProductoNombre(nombre);
        return ResponseEntity.ok(response);
    }

    /* Lista los productos con menor o igual al limite indicado */

    @GetMapping("/stock")
    public ResponseEntity<List<ProductoResponseDTO>> getProductoStock(@RequestParam @Min(value = 0, message = "El límite de stock no puede ser negativo") Integer stock) {
        List<ProductoResponseDTO> response = productoService.getProductoStock(stock);
        return ResponseEntity.ok(response);
    }

    /* Eliminar un producto */

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable("id") Long productoId) {
        productoService.deleteProducto(productoId);
        return ResponseEntity.ok("Se elimino correctamente");
    }

}
