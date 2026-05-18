package com.salesianos.dam.examtrack.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianos.dam.examtrack.model.Alumno;
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

    /* Crear Entidad */


    @GetMapping ("/formExamen") 
    public String formularioExamen (Model model) {

        model.addAttribute("examen", new Examen());

        return "formExamenes";
    }

    @PostMapping ("/crearExamen") 
    public String creadorExamen (@ModelAttribute("examen") Examen examen, Model model) {

        servicio.agregar(examen);

        /*Comprobacion de creacion objeto */
        System.out.println("EXAMEN CREADO");
        System.out.println(examen.toString());

        return "redirect:/examenes";
    }

    /* Editar Entidad */

    @GetMapping("/editar/examen/{id}")
	public String modificarExamen(@PathVariable("id") Long id, Model model) {

		Optional <Examen> examen = servicio.filtrarPorId(id);

		if (examen.isPresent()) {
            model.addAttribute("examen", examen.get());

			return "formExamenes";
		} else {
			return "redirect:/examenes";
		}

	}

    @PostMapping ("/editExamen")
    public String editorExamen (@ModelAttribute("examen") Examen examen) {
        
        servicio.modificar(examen);

        return "redirect:/examenes";

    }
}
