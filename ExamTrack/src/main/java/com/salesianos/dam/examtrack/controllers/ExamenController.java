package com.salesianos.dam.examtrack.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.cglib.core.Local;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianos.dam.examtrack.model.Examen;
import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.service.ExamenServicio;
import com.salesianos.dam.examtrack.service.InscripcionesServicio;
import com.salesianos.dam.examtrack.service.ProfesorServicio;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@Controller
@RequiredArgsConstructor
public class ExamenController {

    private final ExamenServicio servicio;
    private final ProfesorServicio profeServicio;
    private final InscripcionesServicio inscripcionesServicio;
    
    @GetMapping ("/examenes")
    public String misExamenesBase (Model model, @AuthenticationPrincipal Profesor profesores) {

        LocalDateTime actualidad = LocalDateTime.now();

        model.addAttribute("actualidad", actualidad);
        
        model.addAttribute("especialidades", profeServicio.filtrarEspecialidades(profesores.getDni()));
        model.addAttribute("alumnosInscritos", inscripcionesServicio.filtrarAlumnosInscritos());

        model.addAttribute("examen", servicio.filtrarTodos());

        return "examenes";
    } 

    /* Crear Entidad */


    @GetMapping ("/formExamen") 
    public String formularioExamen (Model model, @AuthenticationPrincipal Profesor profesores) {

        model.addAttribute("examen", new Examen());
        model.addAttribute("especialidades", profeServicio.filtrarEspecialidades(profesores.getDni()));

        return "formExamenes";
    }

    @PostMapping ("/crearExamen") 
    public String creadorExamen (@ModelAttribute("examen") Examen examen, Model model, @AuthenticationPrincipal Profesor profesores) {

        examen.setProfesor(profesores);
        servicio.agregar(examen);

        /*Comprobacion de creacion objeto */
        System.out.println("EXAMEN CREADO");
        System.out.println(examen.toString());

        return "redirect:/examenes";
    }

    /* Editar Entidad */

    @GetMapping("/editar/examen/{id}")
	public String modificarExamen(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal Profesor profesores) {

		Optional <Examen> examen = servicio.filtrarPorId(id);

		if (examen.isPresent()) {
            model.addAttribute("examen", examen.get());
            model.addAttribute("especialidades", profeServicio.filtrarEspecialidades(profesores.getDni()));

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

    /*Borrar Entidad (falta por definir politica de borrado, por lo que actualmente es un borrado básico) */

    @GetMapping ("/eliminar/examen/{id}")
    public String borradorExamen (@PathVariable ("id") long id) {

        Optional <Examen> examEliminar = servicio.filtrarPorId(id);

        if (examEliminar.isPresent()) 
            servicio.eliminar(examEliminar.get());

        return "redirect:/examenes";
    }

}
