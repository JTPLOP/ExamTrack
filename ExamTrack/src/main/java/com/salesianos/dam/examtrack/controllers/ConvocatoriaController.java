package com.salesianos.dam.examtrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConvocatoriaController {
    
    @GetMapping ("/convocatorias")
    public String convocatoriasBase (Model model) {
        
        return "convocatorias";
    }


}
