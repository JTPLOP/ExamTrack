package com.salesianos.dam.examtrack.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianos.dam.examtrack.model.Examen;
import com.salesianos.dam.examtrack.service.AlumnoServicio;
import com.salesianos.dam.examtrack.service.ExamenServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InscripcionesController {

    private final ExamenServicio examService;
    private final AlumnoServicio alumServicio;

    @GetMapping("/examen/inscripciones/{id}")
    public String verInscripciones(@PathVariable("id") Long id, Model model) {
        Optional<Examen> examen = examService.filtrarPorId(id);
        
        if (examen.isPresent()) {
            model.addAttribute("examen", examen.get());
            model.addAttribute("alumno", alumServicio.filtrarTodos());

            return "inscripciones";
        } else {
            return "redirect:/examenes";
        }
    }

}
