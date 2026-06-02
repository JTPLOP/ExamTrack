package com.salesianos.dam.examtrack.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InscripcionDuplicadaException extends RuntimeException {
    public InscripcionDuplicadaException(String mensaje) {
        super(mensaje);
    }
}
