package com.salesianos.dam.examtrack.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cglib.core.Local;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.model.UsuarioRol;
import com.salesianos.dam.examtrack.service.ExamenServicio;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class CalendarioController {

    private final ExamenServicio examServicio;

    @GetMapping("/calendario")
    public String calendarioBase(Model model, @AuthenticationPrincipal Profesor profesor) {
        
        int numDias, mesSeleccionado;
        LocalDate fecha = LocalDate.now();
        String nombreMes [] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        numDias = fecha.lengthOfMonth();
        mesSeleccionado = fecha.getMonthValue();
        
        model.addAttribute("diasMes", numDias);
        model.addAttribute("nombreMes", nombreMes[mesSeleccionado-1]);

        UsuarioRol rol = profesor.getRol();

        switch (rol) {
            case ADMIN:
                model.addAttribute("examenes", examServicio.filtrarExamenesMesAdmin(mesSeleccionado).orElse(List.of()));
                break;
            case PROFESOR:
                model.addAttribute("examenes", examServicio.filtrarExamenesMes(profesor.getDni(), mesSeleccionado).orElse(List.of()));
                break;
            default:
                break;
        }

        
        return "calendario";
    }
    
}
