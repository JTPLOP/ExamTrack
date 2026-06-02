package com.salesianos.dam.examtrack.excepciones;

public class UsuarioDuplicadoException extends RuntimeException {
    
    public UsuarioDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
