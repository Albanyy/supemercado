package com.example.supermercado.service;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.supermercado.dto.Cliente.ClienteRequestDTO;
import com.example.supermercado.dto.Cliente.ClienteResponseDTO;
import com.example.supermercado.entity.Cliente;
import com.example.supermercado.excepciones.DuplicateResourceException;
import com.example.supermercado.excepciones.ResourceNotFoundException;
import com.example.supermercado.mapper.ClienteMapper;
import com.example.supermercado.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class ClienteService {
    private final ClienteRepository clienteRepository;

    /*Crea cliente */

    public ClienteResponseDTO crearCliente(ClienteRequestDTO request){
        Optional<Cliente> clienteFound = clienteRepository.findFirstByNombre(request.getNombre());

        if (clienteFound.isPresent()) {
            throw new DuplicateResourceException("Ya existe");
        }

        Cliente cliente = ClienteMapper.toEntity(request);

        Cliente clienteGuardado = clienteRepository.save(cliente);

        return ClienteMapper.toResponseDTO(clienteGuardado);

    }

    /*Trae todos los clientes */

    public List<ClienteResponseDTO> getAllClientes() {

        return clienteRepository.findAll().stream().map(cliente -> ClienteMapper.toResponseDTO(cliente)).collect(Collectors.toList());

    }

    /*Actualiza cliente */

    public ClienteResponseDTO updateCliente(Long id, ClienteRequestDTO request) {
        Cliente clienteFound = clienteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No existe"));

        clienteFound.setNombre(request.getNombre());
        clienteFound.setDocumento(request.getDocumento());
        clienteFound.setTelefono(request.getTelefono());

        Cliente clienteActualizado = clienteRepository.save(clienteFound);

        return ClienteMapper.toResponseDTO(clienteActualizado);
    }

    /*Trae un cliente */

    public ClienteResponseDTO getCliente(Long id) {
        Cliente clienteFound = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe"));

        return ClienteMapper.toResponseDTO(clienteFound);
    }

    /*Elimina un cliente */

    public void deleteCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("No existe");
        }

        clienteRepository.deleteById(id);

    }


}
