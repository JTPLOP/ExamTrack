package com.salesianos.dam.examtrack.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DocumentoInvalidoException extends RuntimeException {
    public DocumentoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
