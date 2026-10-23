
package com.example.supermercado.excepciones;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

        /*Recurso no encotrado */
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<Map<String, Object>> manejarNoEncontrado(ResourceNotFoundException ex) {
                return crearRespuesta(HttpStatus.NOT_FOUND, ex.getMessage());
        }

        /*datos invalidos */
        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<Map<String, Object>> manejarDatosInvalidos(IllegalArgumentException ex) {
                return crearRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

        /* Conflictos de negocio como duplicados o recursos relacionados */
        @ExceptionHandler({ RelatedResourceException.class, IllegalStateException.class })
        public ResponseEntity<Map<String, Object>> manejarConflicto(RuntimeException ex) {
                return crearRespuesta(HttpStatus.CONFLICT, ex.getMessage());
        }

        /* base de datos rechaza una operacion por una restriccion como la primary key o foreign key*/
        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<Map<String, Object>> manejarIntegridad(DataIntegrityViolationException ex) {
                return crearRespuesta(HttpStatus.CONFLICT,
                                "No se puede realizar la operacion porque hay datos relacionados o duplicados");
        }

        /* falla @Valid en el body */
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, Object>> manejarValidacion(MethodArgumentNotValidException ex) {

                String mensaje = "";

                for (FieldError error : ex.getBindingResult().getFieldErrors()) {
                        mensaje = mensaje + error.getField() + ": " + error.getDefaultMessage() + ". ";
                }

                return crearRespuesta(HttpStatus.BAD_REQUEST, mensaje.trim());
        }

        /* falla @Validated en @RequestParam o @PathVariable */
        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<Map<String, Object>> manejarParametros(ConstraintViolationException ex) {
                return crearRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

        /* Arma el JSON de respuesta */
        private ResponseEntity<Map<String, Object>> crearRespuesta(HttpStatus estado, String mensaje) {

                Map<String, Object> cuerpo = new LinkedHashMap<>();
                cuerpo.put("timestamp", LocalDateTime.now());
                cuerpo.put("status", estado.value());
                cuerpo.put("error", estado.getReasonPhrase());
                cuerpo.put("mensaje", mensaje);

                return ResponseEntity.status(estado).body(cuerpo);
        }
}