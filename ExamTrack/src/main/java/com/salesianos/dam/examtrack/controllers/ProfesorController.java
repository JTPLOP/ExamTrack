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
import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.model.UsuarioRol;
import com.salesianos.dam.examtrack.service.ProfesorServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProfesorController {

    private final ProfesorServicio servicio;
    private final PasswordEncoder encoder;

    @GetMapping ("/profesor")
    public String ProfesoresBase (Model model) {
        model.addAttribute("profesor", servicio.filtrarTodos());
        
        return "profesores";
    }

    @GetMapping ("/formProfesor") 
    public String formularioProfesor(Model model) {
        model.addAttribute("profesor", new Profesor());

        return "formProfesor";
    }

    @PostMapping ("/crearProfesor") 
    public String creadorProfesor (@ModelAttribute("datosForm") Profesor datosForm, Model model) {

        /*Estretegia de creacion de Profesor: es una estrategia de creacion de nuevo profesor aunque bastante
        pobre, de momneto al ser funcional lo voy a dejar como forma temporal aunque se tiene que cambiar la logica. */

        Profesor profesor = Profesor.builder()
        .dni(datosForm.getDni())
        .nombre(datosForm.getNombre())
        .primerApellido(datosForm.getPrimerApellido())
        .segundoApellido(datosForm.getSegundoApellido())
        .username(datosForm.creadorUsername())
        .password(encoder.encode(datosForm.creadorPassword()))
        .email(datosForm.getEmail())
        .direccion(datosForm.getDireccion())
        .fechaNacimiento(datosForm.getFechaNacimiento())
        .rol(UsuarioRol.PROFESOR)
        .fotoPerfil(datosForm.getFotoPerfil())
        .especialidad(datosForm.getEspecialidad())
        .examen(datosForm.getExamen())
        .build();

        servicio.agregar(profesor);

        /*Comprobacion de creacion objeto */
        profesor.depurarDatos();

        return "redirect:/formProfesor";
    }


    @GetMapping("/editar/profesor/{dni}")
	public String modificarAlumno(@PathVariable("dni") String dni, Model model) {

		Optional <Profesor> profesor = servicio.filtrarPorId(dni); // aprender a filtrar por dni (como hacer consultas)

		if (profesor.isPresent()) {
            model.addAttribute("profesor", profesor.get());

			return "formProfesor";
		} else {
			return "redirect:/profesores";
		} 

	}

    @PostMapping ("/editProfesor")
    public String editorAlumno (@ModelAttribute("profesor") Profesor profesor) {
        
        servicio.modificar(profesor);

        return "redirect:/profesores";
    }
    





}
