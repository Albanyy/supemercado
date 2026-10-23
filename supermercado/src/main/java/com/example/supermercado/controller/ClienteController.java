package com.example.supermercado.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.supermercado.dto.Cliente.ClienteRequestDTO;
import com.example.supermercado.dto.Cliente.ClienteResponseDTO;
import com.example.supermercado.service.ClienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor 
@RequestMapping ("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    
    /*Crea una cliente */

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crearCliente(@Valid @RequestBody ClienteRequestDTO request) {
        ClienteResponseDTO response = clienteService.crearCliente(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }    

    /*Trae todos los cliente */

    @GetMapping 
    public ResponseEntity<List<ClienteResponseDTO>> getAllClientes() {
        List<ClienteResponseDTO> response = clienteService.getAllClientes();
        return ResponseEntity.ok(response);
        
    }

    /*Actualiza un cliente */

    @PutMapping ("/{id}")
    public ResponseEntity<ClienteResponseDTO> updateCliente(@PathVariable("id") Long clienteId, @Valid @RequestBody ClienteRequestDTO request) {
        ClienteResponseDTO response = clienteService.updateCliente(clienteId, request);
        return ResponseEntity.ok(response);
    }

    /*Trae un cliente */

    @GetMapping ("/{id}")
    public ResponseEntity<ClienteResponseDTO> getCliente(@PathVariable("id") Long clienteId) {
        ClienteResponseDTO response = clienteService.getCliente(clienteId);
        return ResponseEntity.ok(response);
    }

    /*Trae un cliente por documento */
    @GetMapping ("/documento/{documento}")
    public ResponseEntity<ClienteResponseDTO> getClienteDocumento(@PathVariable ("documento") String documento) {
        ClienteResponseDTO response = clienteService.getClienteDocumento(documento);
        return ResponseEntity.ok(response);
    }

    /*Elimina un cliente */

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable("id") Long clienteId){
        clienteService.deleteCliente(clienteId);
        return ResponseEntity.noContent().build();
    }
}
