package com.example.supermercado.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.supermercado.dto.Proveedor.ProveedorRequestDTO;
import com.example.supermercado.dto.Proveedor.ProveedorResponseDTO;
import com.example.supermercado.entity.Proveedor;
import com.example.supermercado.excepciones.RelatedResourceException;
import com.example.supermercado.excepciones.ResourceNotFoundException;
import com.example.supermercado.mapper.ProveedorMapper;
import com.example.supermercado.repository.ProductoRepository;
import com.example.supermercado.repository.ProveedorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;
    private final ProductoRepository productoRepository;

    /* Crea un Proveedor */

    public ProveedorResponseDTO crearProveedor(ProveedorRequestDTO request) {
        Optional<Proveedor> proveedorFound = proveedorRepository.findFirstByNombre(request.getNombre());

        if (proveedorFound.isPresent()) {
            throw new IllegalStateException("Ya existe un proveedor con ese nombre");
        }
        Proveedor proveedor = ProveedorMapper.toEntity(request);

        Proveedor proveedorGuardado = proveedorRepository.save(proveedor);

        return ProveedorMapper.toResponseDTO(proveedorGuardado);

    }

    /* Trae todos los proveedores */

    public List<ProveedorResponseDTO> getAllProveedores() {

        return proveedorRepository.findAll().stream().map(proveedor -> ProveedorMapper.toResponseDTO(proveedor))
                .collect(Collectors.toList());

    }

    /* Actualiza a proveedor */

    public ProveedorResponseDTO updateProveedor(Long proveedorId, ProveedorRequestDTO request) {

        Proveedor proveedorFound = proveedorRepository.findById(proveedorId)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el proveedor"));

        proveedorFound.setNombre(request.getNombre());
        proveedorFound.setTelefono(request.getTelefono());
        proveedorFound.setCorreo(request.getCorreo());

        Proveedor proveedorGuardado = proveedorRepository.save(proveedorFound);

        return ProveedorMapper.toResponseDTO(proveedorGuardado);

    }

    /* Trae a un proveedor por id */

    public ProveedorResponseDTO getProveedor(Long proveedorId) {
        Proveedor proveedorFound = proveedorRepository.findById(proveedorId)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el proveedor"));

        return ProveedorMapper.toResponseDTO(proveedorFound);
    }

    /* Elimina a un proveedor */

    public void deleteProveedor(Long proveedorId) {
        if (!proveedorRepository.existsById(proveedorId)) {
            throw new ResourceNotFoundException("No existe proveedor");
        }

        if (productoRepository.existsByProveedorId(proveedorId)) {
            throw new RelatedResourceException("No se puede eliminar el proveedor porque tiene productos relacionados");
        }

        proveedorRepository.deleteById(proveedorId);
    }

}
