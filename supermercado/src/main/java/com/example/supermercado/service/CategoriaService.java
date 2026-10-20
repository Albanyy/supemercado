package com.example.supermercado.service;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.supermercado.dto.Categoria.CategoriaRequestDTO;
import com.example.supermercado.dto.Categoria.CategoriaResponseDTO;
import com.example.supermercado.entity.Categoria;
import com.example.supermercado.excepciones.RelatedResourceException;
import com.example.supermercado.excepciones.ResourceNotFoundException;
import com.example.supermercado.mapper.CategoriaMapper;
import com.example.supermercado.repository.CategoriaRepository;
import com.example.supermercado.repository.ProductoRepository;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    /* Crea categoria */
    public CategoriaResponseDTO crearCategoria(CategoriaRequestDTO request) {
        Optional<Categoria> categoriaFound = categoriaRepository.findFirstByNombre(request.getNombre());

        if (categoriaFound.isPresent()) {
            throw new IllegalStateException("Ya existe una categoría con ese nombre");
        }

        Categoria categoria = CategoriaMapper.toEntity(request);

        Categoria categoriaGuardada = categoriaRepository.save(categoria);

        return CategoriaMapper.toResponseDTO(categoriaGuardada);

    }

    /* Trae todas las categorias */

    public List<CategoriaResponseDTO> getAllCategorias() {

        return categoriaRepository.findAll().stream().map(categoria -> CategoriaMapper.toResponseDTO(categoria))
                .collect(Collectors.toList());
    }

    /* Actualiza las categorias */

    public CategoriaResponseDTO updateCategoria(Long id, CategoriaRequestDTO request) {
        Categoria categoriaFound = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la categoría"));

        categoriaFound.setNombre(request.getNombre());
        categoriaFound.setDescripcion(request.getDescripcion());

        Categoria categoriaActualizada = categoriaRepository.save(categoriaFound);

        return CategoriaMapper.toResponseDTO(categoriaActualizada);
    }

    /* Trae una categoria */

    public CategoriaResponseDTO getCategoria(Long id) {
        Categoria categoriaFound = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la categoría"));

        return CategoriaMapper.toResponseDTO(categoriaFound);
    }

    /* Elimina categoria */

    public void deleteCategoria(Long categoriaId) {
        if (!categoriaRepository.existsById(categoriaId)) {
            throw new ResourceNotFoundException("No existe la categoría");
        }

        if (productoRepository.existsByCategoriaId(categoriaId)) {
            throw new RelatedResourceException("No se puede eliminar la categoría porque tiene productos relacionados");
        }

        categoriaRepository.deleteById(categoriaId);
    }

}
