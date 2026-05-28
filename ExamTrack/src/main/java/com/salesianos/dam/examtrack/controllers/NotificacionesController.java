package com.salesianos.dam.examtrack.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianos.dam.examtrack.model.Examen;
import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.service.ExamenServicio;
import com.salesianos.dam.examtrack.service.InscripcionesServicio;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class NotificacionesController {
    
    private final ExamenServicio examServicio;
    private final InscripcionesServicio inscrServicio;

    @GetMapping("/notificaciones")
    public String notificacionesBase(Model model, @AuthenticationPrincipal Profesor profesor) {

        Map <Long, Integer> contadorNumAlumnos = new HashMap<>();

        List <Examen> proxExamenes = examServicio.filtrarProximosExamenes(profesor.getDni());

        model.addAttribute("proximosExamenes", proxExamenes);
        model.addAttribute("profesor", profesor);

        for (Examen examen : proxExamenes) {
            contadorNumAlumnos.put(examen.getIdExamen(), inscrServicio.contarAlumnosInscritos(examen.getIdExamen()));
        }
        
        model.addAttribute("contadorNumAlumnos", contadorNumAlumnos);
        
        return "notificaciones";
    }
    

}
