
package com.example.supermercado.excepciones;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

        /* Traductor de errores */

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> manejarValidacion(MethodArgumentNotValidException excepcion) {

                String mensaje = excepcion.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("mensaje", mensaje));
        }

        /* Traductor de no encontrado */

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<Map<String, String>> manejarResourceNotFound(ResourceNotFoundException excepcion) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", excepcion.getMessage()));
        }

        /* Traductor de ya existe */

        @ExceptionHandler(DuplicateResourceException.class)
        public ResponseEntity<Map<String, String>> manejarDuplicateResource(DuplicateResourceException excepcion) {

                return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("mensaje", excepcion.getMessage()));
        }

        /*Traductor de no borra si hay datos */

        @ExceptionHandler(DataRelationException.class)
        public ResponseEntity<Map<String, String>> manejarDataRelation(DataRelationException excepcion) {

                return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("mensaje", excepcion.getMessage()));
        }

}
