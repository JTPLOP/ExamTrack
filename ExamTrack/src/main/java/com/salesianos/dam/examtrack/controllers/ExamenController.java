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

import java.util.ArrayList;
import java.util.List;

import com.salesianos.dam.examtrack.model.Examen;
import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.model.UsuarioRol;
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
        model.addAttribute("alumnosInscritos", inscripcionesServicio.filtrarAlumnosInscritos());

        UsuarioRol rol = profesores.getRol();
        
        switch (rol) {
            case ADMIN:
                model.addAttribute("examen", servicio.filtrarTodos());
                break;
            case PROFESOR:
                model.addAttribute("examen", servicio.filtrarExamenesProfesor(profesores.getDni()).orElse(new ArrayList<>()));
                model.addAttribute("especialidades", profeServicio.filtrarEspecialidades(profesores.getDni()));
                break;
            default:
                break;
        }

        return "examenes";
    } 

    /* Crear Entidad */


    @GetMapping ("/formExamen") 
    public String formularioExamen (Model model, @AuthenticationPrincipal Profesor profesores) {

        UsuarioRol rol = profesores.getRol();
        boolean isAdmin = (rol == UsuarioRol.ADMIN);
        
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("examen", new Examen());

        if (isAdmin) {
            model.addAttribute("especialidades", profeServicio.filtrarTodasEspecialidades());
            

            /*Hacer que no aparezca el admin como profesor para asignar */
            List<Profesor> listaProfesores = profeServicio.filtrarTodos().stream()
                .filter(p -> !p.getDni().equals(profesores.getDni()))
                .toList();
            model.addAttribute("listaProfesores", listaProfesores);
        } else {
            model.addAttribute("especialidades", profeServicio.filtrarEspecialidades(profesores.getDni()));
        }

        return "formExamenes";
    }

    @PostMapping ("/crearExamen") 
    public String creadorExamen (@ModelAttribute("examen") Examen examen, 
                                 @RequestParam(value = "profesorDni", required = false) String profesorDni,
                                 Model model, @AuthenticationPrincipal Profesor profesores) {

        if (profesores.getRol() == UsuarioRol.ADMIN && profesorDni != null && !profesorDni.isEmpty()) {
            Optional<Profesor> profSeleccionado = profeServicio.filtrarPorId(profesorDni);
            if (profSeleccionado.isPresent()) {
                examen.setProfesor(profSeleccionado.get());
            } else {
                examen.setProfesor(profesores);
            }
        } else {
            examen.setProfesor(profesores);
        }

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
            UsuarioRol rol = profesores.getRol();
            boolean isAdmin = (rol == UsuarioRol.ADMIN);
            
            model.addAttribute("isAdmin", isAdmin);
            model.addAttribute("examen", examen.get());

            if (isAdmin) {
                model.addAttribute("especialidades", profeServicio.filtrarTodasEspecialidades());
                List<Profesor> listaProfesores = profeServicio.filtrarTodos().stream()
                    .filter(p -> !p.getDni().equals(profesores.getDni()))
                    .toList();
                model.addAttribute("listaProfesores", listaProfesores);
            } else {
                model.addAttribute("especialidades", profeServicio.filtrarEspecialidades(profesores.getDni()));
            }

			return "formExamenes";
		} else {
			return "redirect:/examenes";
		}

	}

    @PostMapping ("/editExamen")
    public String editorExamen (@ModelAttribute("examen") Examen examen,
                                @RequestParam(value = "profesorDni", required = false) String profesorDni,
                                @AuthenticationPrincipal Profesor profesores) {
        
        if (profesores.getRol() == UsuarioRol.ADMIN && profesorDni != null && !profesorDni.isEmpty()) {
            Optional<Profesor> profSeleccionado = profeServicio.filtrarPorId(profesorDni);
            if (profSeleccionado.isPresent()) {
                examen.setProfesor(profSeleccionado.get());
            } else {
                examen.setProfesor(profesores);
            }
        } else {
            
            examen.setProfesor(profesores);
        }

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
