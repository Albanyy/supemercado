package com.example.supermercado.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.supermercado.dto.Categoria.CategoriaRequestDTO;
import com.example.supermercado.dto.Categoria.CategoriaResponseDTO;
import com.example.supermercado.service.CategoriaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping("/api/categoria") 
public class CategoriaController {
    
    private final CategoriaService categoriaService;

    /*Crea una categoria */

    @PostMapping 
    public ResponseEntity<CategoriaResponseDTO> crearCategoria(@Valid @RequestBody CategoriaRequestDTO request) {
        CategoriaResponseDTO response = categoriaService.crearCategoria(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*Trae todas las categorias */

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> getAllCategorias(){
        List<CategoriaResponseDTO> response = categoriaService.getAllCategorias();
        return ResponseEntity.ok(response);
    }

    /*Actualiza una categoria */

    @PutMapping ("/{id}")
    public ResponseEntity<CategoriaResponseDTO> updateCategoria(@PathVariable ("id") Long categoriaId, @Valid @RequestBody CategoriaRequestDTO updatedCategoria) {
        CategoriaResponseDTO updateCategoria = categoriaService.updateCategoria(categoriaId, updatedCategoria);
        return ResponseEntity.ok(updateCategoria);
    }

    /*Trae una categoria por id */

    @GetMapping ("/{id}")
    public ResponseEntity<CategoriaResponseDTO> getCategoria(@PathVariable Long id) {

        return ResponseEntity.ok(categoriaService.getCategoria(id));
    }

    /*Elimina una categoria por id */

    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteCategoria(@PathVariable ("id") Long categoriaId) {
        categoriaService.deleteCategoria(categoriaId);
        return ResponseEntity.noContent().build();
    }


}
