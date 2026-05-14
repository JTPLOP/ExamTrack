package com.salesianos.dam.examtrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.salesianos.dam.examtrack.model.Alumno;
import com.salesianos.dam.examtrack.service.AlumnoServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AlumnoController {

    private final AlumnoServicio servicio;
    
    @GetMapping ("/alumnos")
    public String alumnosBase (Model model) {

        
        return "alumnos";
    }

    @GetMapping ("/formAlumno") 
    public String formularioAlumno () {

        return "formAlumnos";
    }

    @PostMapping ("/crearAlumno") 
    public String creadorAlumno (@ModelAttribute("alumno") Alumno alumno, Model model) {

        servicio.agregar(alumno);

        /*Comprobacion de creacion objeto */
        System.out.println("hola");
        alumno.depurarDatos();

        return "redirect:/formAlumno";
    }


}
