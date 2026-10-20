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

import com.example.supermercado.dto.Proveedor.ProveedorRequestDTO;
import com.example.supermercado.dto.Proveedor.ProveedorResponseDTO;
import com.example.supermercado.service.ProveedorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api/proveedor")
public class ProveedorController {
    private final ProveedorService proveedorService;

    /*Crea un proveedor */
    @PostMapping 
    public ResponseEntity<ProveedorResponseDTO> crearProveedor(@Valid @RequestBody ProveedorRequestDTO request){
        ProveedorResponseDTO response = proveedorService.crearProveedor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*Trae a todos los proveedores */
    @GetMapping 
    public ResponseEntity<List<ProveedorResponseDTO>> getAllProveedores() {
        List<ProveedorResponseDTO> response = proveedorService.getAllProveedores();
        return ResponseEntity.ok(response);
    }

    /*Actualiza a proveedor */
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponseDTO> updateProveedor(@PathVariable("id") Long proveedorId, @RequestBody ProveedorRequestDTO request){
        ProveedorResponseDTO response = proveedorService.updateProveedor(proveedorId, request);
        return ResponseEntity.ok(response);
    }

    /*Trae un proveedor por id */
    @GetMapping ("/{id}")
    public ResponseEntity<ProveedorResponseDTO> getProveedor(@PathVariable("id") Long proveedorId){
        ProveedorResponseDTO response = proveedorService.getProveedor(proveedorId);
        return ResponseEntity.ok(response);
    }

    /*Elimina el proveedor */
    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteProveedor(@PathVariable("id")  Long proveedorId){
        proveedorService.deleteProveedor(proveedorId);
        return ResponseEntity.noContent().build();
    }

}
