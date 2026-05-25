package com.salesianos.dam.examtrack.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.salesianos.dam.examtrack.model.Especialidad;
import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.model.UsuarioRol;
import com.salesianos.dam.examtrack.service.DepartamentoServicio;
import com.salesianos.dam.examtrack.service.EspecialidadServicio;
import com.salesianos.dam.examtrack.service.ProfesorServicio;
import com.salesianos.dam.examtrack.service.UsuarioServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProfesorController {

    private final ProfesorServicio servicio;
    private final DepartamentoServicio depaServicio;
    private final EspecialidadServicio especiServicio;
    private final UsuarioServicio userServicio;

    @GetMapping("/profesor")
    public String ProfesoresBase(Model model) {
        model.addAttribute("profesor", servicio.filtrarTodos());

        return "profesores";
    }

    @GetMapping("/formProfesor")
    public String formularioProfesor(Model model) {

        model.addAttribute("profesor", new Profesor());
        model.addAttribute("listaDepartamento", depaServicio.filtrarTodos());
        model.addAttribute("listaEspecialidad", especiServicio.filtrarTodos());

        return "formProfesor";
    }

    @PostMapping("/crearProfesor")
    public String creadorProfesor(@ModelAttribute("datosForm") Profesor datosForm, Model model) {

        datosForm.setPassword(userServicio.encriptadorPasswords(datosForm.getPassword()));
        datosForm.setRol(UsuarioRol.PROFESOR);
        datosForm.creadorUsername();

        servicio.agregar(datosForm);
        datosForm.depurarDatos();

        return "redirect:/formProfesor";
    }

    @GetMapping("/editar/profesor/{dni}")
    public String modificarAlumno(@PathVariable("dni") String dni, Model model) {

        Optional<Profesor> profesor = servicio.filtrarPorId(dni);

        if (profesor.isPresent()) {
            model.addAttribute("profesor", profesor.get());

            return "formProfesor";
        } else {
            return "redirect:/profesor";
        }

    }

    @PostMapping("/editProfesor")
    public String editorAlumno(@ModelAttribute("profesor") Profesor profesor) {

        servicio.modificar(profesor);

        return "redirect:/profesor";
    }

    @GetMapping("/eliminar/profesor/{dni}")
    public String borradorExamen(@PathVariable("dni") String dni) {

        Optional<Profesor> profesor = servicio.filtrarPorId(dni);

        if (profesor.isPresent())
            servicio.eliminar(profesor.get());

        return "redirect:/profesor";
    }

}
