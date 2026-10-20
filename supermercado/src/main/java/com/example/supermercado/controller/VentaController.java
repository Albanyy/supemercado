package com.example.supermercado.controller;

import java.util.List;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.example.supermercado.dto.Venta.VentaRequestDTO;
import com.example.supermercado.dto.Venta.VentaResponseDTO;
import com.example.supermercado.service.VentaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/venta")
public class VentaController {

    private final VentaService ventaService;

    /* Crea una venta */

    @PostMapping
    public ResponseEntity<VentaResponseDTO> crearVenta(@Valid @RequestBody VentaRequestDTO request) {
        VentaResponseDTO response = ventaService.crearVenta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /* Trae todas las ventas */

    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> getAllVentas() {
        List<VentaResponseDTO> response = ventaService.getAllVentas();
        return ResponseEntity.ok(response);
    }

    /* Actualiza una venta */

    @PutMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> updateVenta(@PathVariable("id") Long ventaId,
            @Valid @RequestBody VentaRequestDTO request) {
        VentaResponseDTO response = ventaService.updateVenta(ventaId, request);
        return ResponseEntity.ok(response);
    }

    /* Trae una venta por id */

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> getVenta(@PathVariable("id") Long ventaId) {
        VentaResponseDTO response = ventaService.getVenta(ventaId);
        return ResponseEntity.ok(response);
    }

    /* Trae ventas de un cliente */

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<VentaResponseDTO>> getVentasCliente(@PathVariable("clienteId") Long clienteId) {
        List<VentaResponseDTO> response = ventaService.getVentasCliente(clienteId);
        return ResponseEntity.ok(response);
    }

    /* Trae ventas entre dos fechas */

    @GetMapping("/fecha")
    public ResponseEntity<List<VentaResponseDTO>> getVentasEntreFechas(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        List<VentaResponseDTO> response = ventaService.getVentasEntreFechas(fechaInicio, fechaFin);
        return ResponseEntity.ok(response);
    }

    /* Elimina una venta */

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVenta(@PathVariable("id") Long ventaId) {
        ventaService.deleteVenta(ventaId);
        return ResponseEntity.noContent().build();
    }

}