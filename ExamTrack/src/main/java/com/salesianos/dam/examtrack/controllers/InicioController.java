package com.salesianos.dam.examtrack.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.service.InscripcionesServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InicioController {

    private final InscripcionesServicio inscripServicio;
    
    @GetMapping ("/inicio")
    public String inicioBase (Model model, @AuthenticationPrincipal Profesor profesor) {

        Map <Integer, Double> estadisticasBase = new HashMap<>();
        
        estadisticasBase.put(1, inscripServicio.contarAllAlumnos(profesor.getDni()));

        model.addAttribute("estadisticasBase", estadisticasBase);
        
        return "inicio";
    }

    
}
