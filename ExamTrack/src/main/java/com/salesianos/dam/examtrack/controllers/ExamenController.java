package com.salesianos.dam.examtrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExamenController {
    
    @GetMapping ("/examenes")
    public String misExamenesBase (Model model) {
        
        return "examenes";
    }

    
}
