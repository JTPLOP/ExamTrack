package com.salesianos.dam.examtrack.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.service.ExamenServicio;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class NotificacionesController {
    
    private final ExamenServicio examServicio;

    @GetMapping("/notificaciones")
    public String notificacionesBase(Model model, @AuthenticationPrincipal Profesor profesor) {

        model.addAttribute("proximosExamenes", examServicio.filtrarProximosExamenes(profesor.getDni()));
        model.addAttribute("profesor", profesor);
        

        return "notificaciones";
    }
    

}
