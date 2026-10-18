package com.example.supermercado.excepciones;




public class DuplicateResourceException extends RuntimeException {
    
    public DuplicateResourceException(String mensaje){
        super(mensaje);
    }

}
