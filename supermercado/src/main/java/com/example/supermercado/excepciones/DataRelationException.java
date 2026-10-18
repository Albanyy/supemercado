package com.example.supermercado.excepciones;

public class DataRelationException extends RuntimeException {
    public DataRelationException(String mensaje) {
        super(mensaje);
    }
}