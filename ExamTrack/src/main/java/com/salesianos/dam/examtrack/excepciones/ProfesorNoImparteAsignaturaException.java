package com.salesianos.dam.examtrack.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ProfesorNoImparteAsignaturaException extends RuntimeException {
    public ProfesorNoImparteAsignaturaException(String mensaje) {
        super(mensaje);
    }
}
