package com.salesianos.dam.examtrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExamenController {
    
    @GetMapping ("/examenes")
    public String misExamenesBase (Model model) {
        
        return "examenes";
    }


    @GetMapping ("/formExamen") 
    public String formularioExamen () {

        return "formExamenes";
    }

    @PostMapping ("/crearExamen") 
    public String creadorExamen (Model model) {



        System.out.println("Hola mundo");
        return "redirect:/formExamen";
    }
    
}
