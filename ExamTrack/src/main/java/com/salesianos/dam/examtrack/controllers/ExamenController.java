package com.salesianos.dam.examtrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianos.dam.examtrack.model.Examen;
import com.salesianos.dam.examtrack.service.ExamenServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ExamenController {

    private final ExamenServicio servicio;
    
    @GetMapping ("/examenes")
    public String misExamenesBase (Model model) {
        
        return "examenes";
    }


    @GetMapping ("/formExamen") 
    public String formularioExamen () {

        return "formExamenes";
    }

    @PostMapping ("/crearExamen") 
    public String creadorExamen (@ModelAttribute("examen") Examen examen, Model model) {

        servicio.agregar(examen);

        /*Comprobacion de creacion objeto */
        System.out.println("EXAMEN CREADO");
        System.out.println(examen.toString());

        return "redirect:/formExamen";
    }
    
}
