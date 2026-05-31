package com.salesianos.dam.examtrack.controllers;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CalendarioController {
    

    @GetMapping("/calendario")
    public String calendarioBase(Model model) {
        
        int numDias, mesSeleccionado;
        LocalDate fecha = LocalDate.now();
        String nombreMes [] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        numDias = fecha.getDayOfMonth();
        mesSeleccionado = fecha.getMonthValue();
        

        model.addAttribute("diasMes", numDias);
        model.addAttribute("nombreMes", nombreMes[mesSeleccionado-1]);

        return "calendario";
    }
    
}
