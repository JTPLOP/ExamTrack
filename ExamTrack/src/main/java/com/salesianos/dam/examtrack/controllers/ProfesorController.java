package com.salesianos.dam.examtrack.controllers;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import com.salesianos.dam.examtrack.model.Alumno;
import com.salesianos.dam.examtrack.model.Departamento;
import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.model.UsuarioRol;
import com.salesianos.dam.examtrack.service.DepartamentoServicio;
import com.salesianos.dam.examtrack.service.ProfesorServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProfesorController {

    private final ProfesorServicio servicio;
    private final DepartamentoServicio depaServicio;

    @GetMapping ("/profesor")
    public String ProfesoresBase (Model model) {
        model.addAttribute("profesor", servicio.filtrarTodos());
        
        return "profesores";
    }

    @GetMapping ("/formProfesor") 
    public String formularioProfesor(Model model) {
        
        model.addAttribute("profesor", new Profesor());
        model.addAttribute("departamento", depaServicio.filtrarTodos());

        return "formProfesor"; 
    }

    @PostMapping ("/crearProfesor") 
    public String creadorProfesor (@ModelAttribute("datosForm") Profesor datosForm, Model model) {

        servicio.agregar(datosForm);

        /*Comprobacion de creacion objeto */
        datosForm.depurarDatos();

        return "redirect:/profesor";
    }


    @GetMapping("/editar/profesor/{dni}")
	public String modificarAlumno(@PathVariable("dni") String dni, Model model) {

		Optional <Profesor> profesor = servicio.filtrarPorId(dni); // aprender a filtrar por dni (como hacer consultas)

		if (profesor.isPresent()) {
            model.addAttribute("profesor", profesor.get());

			return "formProfesor";
		} else {
			return "redirect:/profesor";
		} 

	}

    @PostMapping ("/editProfesor")
    public String editorAlumno (@ModelAttribute("profesor") Profesor profesor) {
        
        servicio.modificar(profesor);

        return "redirect:/profesor";
    }
    
    @GetMapping ("/eliminar/profesor/{dni}")
    public String borradorExamen (@PathVariable ("dni") String dni) {

        Optional <Profesor> profesor = servicio.filtrarPorId(dni);

        if (profesor.isPresent()) 
            servicio.eliminar(profesor.get());

        return "redirect:/profesor";
    }
 



}
