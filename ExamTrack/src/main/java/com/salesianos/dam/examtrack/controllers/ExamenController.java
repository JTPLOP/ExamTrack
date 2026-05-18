package com.salesianos.dam.examtrack.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianos.dam.examtrack.model.Examen;
import com.salesianos.dam.examtrack.service.ExamenServicio;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class ExamenController {

    private final ExamenServicio servicio;
    
    @GetMapping ("/examenes")
    public String misExamenesBase (Model model) {
        model.addAttribute("examen", servicio.filtrarTodos());

        return "examenes";
    } 


    @GetMapping ("/formExamen") 
    public String formularioExamen (Model model) {

        return "formExamenes";
    }

    @PostMapping ("/examenSubmit") 
    public String creadorExamen (@ModelAttribute("examen") Examen examen, Model model) {

        
        servicio.agregar(examen);

        /*Comprobacion de creacion objeto */
        System.out.println("EXAMEN CREADO");
        System.out.println(examen.toString());

        return "redirect:/examenes";
    }

    @GetMapping("/editar/examen/{id}")
	public String modificarExamen(@PathVariable("id") Long id, Model model) {

		Optional <Examen> examen = servicio.filtrarPorId(id);

		if (examen.isPresent()) {
            model.addAttribute("examen", servicio.filtrarPorId(id));

			return "formExamenes";
		} else {
			return "redirect:/examenes";
		}

	}



    
    
    
}
