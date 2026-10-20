package com.example.supermercado.excepciones;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void resourceNotFoundReturnsNotFound() {
        ResponseEntity<Map<String, Object>> response = handler.manejarNoEncontrado(
                new ResourceNotFoundException("No existe el recurso"));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).containsEntry("mensaje", "No existe el recurso");
    }

    @Test
    void relatedResourceReturnsConflict() {
        ResponseEntity<Map<String, Object>> response = handler.manejarConflicto(
                new RelatedResourceException("El recurso tiene dependencias"));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody()).containsEntry("mensaje", "El recurso tiene dependencias");
    }

    @Test
    void duplicateConflictStillReturnsConflict() {
        ResponseEntity<Map<String, Object>> response = handler.manejarConflicto(
                new IllegalStateException("El registro ya existe"));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody()).containsEntry("mensaje", "El registro ya existe");
    }
}
