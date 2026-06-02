package com.salesianos.dam.examtrack.controllers;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.salesianos.dam.examtrack.model.Profesor;

@ControllerAdvice
public class SesionController {
    
    @ModelAttribute("profesorLogueado")
    public Profesor aportarProfesorAlModelo(@AuthenticationPrincipal Profesor profesor) {
        return profesor; 
    }

}
