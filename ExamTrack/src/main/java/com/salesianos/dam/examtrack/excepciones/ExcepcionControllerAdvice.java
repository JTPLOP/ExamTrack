package com.salesianos.dam.examtrack.excepciones;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.FlashMap;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExcepcionControllerAdvice {

    private String redirigirConError(Exception ex, HttpServletRequest request) {
        FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
        if (flashMap != null) {
            flashMap.put("errorPersonalizado", ex.getMessage());
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null && !referer.isEmpty() ? referer : "/inicio");
    }

    @ExceptionHandler(NotaFueraDeRangoException.class)
    public String handleNotaFueraDeRango(NotaFueraDeRangoException ex, HttpServletRequest request) {
        return redirigirConError(ex, request);
    }

    @ExceptionHandler(InscripcionDuplicadaException.class)
    public String handleInscripcionDuplicada(InscripcionDuplicadaException ex, HttpServletRequest request) {
        return redirigirConError(ex, request);
    }

    @ExceptionHandler(DocumentoInvalidoException.class)
    public String handleDocumentoInvalido(DocumentoInvalidoException ex, HttpServletRequest request) {
        return redirigirConError(ex, request);
    }

    @ExceptionHandler(ProfesorNoImparteAsignaturaException.class)
    public String handleProfesorNoImparteAsignatura(ProfesorNoImparteAsignaturaException ex, HttpServletRequest request) {
        return redirigirConError(ex, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
        return redirigirConError(ex, request);
    }
}
