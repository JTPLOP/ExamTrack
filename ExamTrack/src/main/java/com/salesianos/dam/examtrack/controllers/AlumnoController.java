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
import com.salesianos.dam.examtrack.service.AlumnoServicio;

import lombok.RequiredArgsConstructor;
 
@Controller
@RequiredArgsConstructor
public class AlumnoController {

    private final AlumnoServicio servicio;
    
    @GetMapping ("/alumnos")
    public String alumnosBase (Model model) {
        model.addAttribute("alumno", servicio.filtrarTodos());
        
        return "alumnos";
    }

    @GetMapping ("/formAlumno") 
    public String formularioAlumno (Model model) {
        model.addAttribute("alumno", new Alumno());

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

    @GetMapping("/editar/alumno/{dni}")
	public String modificarAlumno(@PathVariable("dni") String dni, Model model) {

		Optional <Alumno> alumno = servicio.filtrarPorId(dni); // aprender a filtrar por dni (como hacer consultas)

		if (alumno.isPresent()) {
            model.addAttribute("alumno", alumno.get());

			return "formAlumnos";
		} else {
			return "redirect:/alumnos";
		} 

	}

    @PostMapping ("/editAlumno")
    public String editorAlumno (@ModelAttribute("examen") Alumno alumno) {
        
        servicio.modificar(alumno);

        return "redirect:/alumnos";
    }


}
