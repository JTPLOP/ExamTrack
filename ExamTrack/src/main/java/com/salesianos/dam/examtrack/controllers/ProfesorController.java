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

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.salesianos.dam.examtrack.model.Usuario;
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

    @GetMapping("/admin/profesor")
    public String ProfesoresBase(Model model) {
        List<Profesor> todos = servicio.filtrarTodos();
        List<Profesor> sinAdmin = new ArrayList<>();
        for (Profesor p : todos) {
            if (p.getRol() != UsuarioRol.ADMIN) {
                sinAdmin.add(p);
            }
        }
        model.addAttribute("profesor", sinAdmin);

        return "admin/profesores";
    }

    @GetMapping("/admin/formProfesor")
    public String formularioProfesor(Model model) {

        model.addAttribute("profesor", new Profesor());
        model.addAttribute("listaDepartamento", depaServicio.filtrarTodos());
        model.addAttribute("listaEspecialidad", especiServicio.filtrarTodos());

        return "admin/formProfesor";
    }

    @PostMapping("/admin/crearProfesor")
    public String creadorProfesor(@ModelAttribute("datosForm") Profesor datosForm, Model model) {

        datosForm.setPassword(userServicio.encriptadorPasswords(datosForm.getPassword()));
        datosForm.setRol(UsuarioRol.PROFESOR);
        datosForm.creadorUsername();

        servicio.agregar(datosForm);
        datosForm.depurarDatos();

        return "redirect:/admin/formProfesor";
    }

    @GetMapping("/admin/editar/profesor/{dni}")
    public String modificarAlumno(@PathVariable("dni") String dni, Model model) {

        Optional<Profesor> profesor = servicio.filtrarPorId(dni);

        if (profesor.isPresent()) {
            model.addAttribute("profesor", profesor.get());
            model.addAttribute("listaDepartamento", depaServicio.filtrarTodos());
            model.addAttribute("listaEspecialidad", especiServicio.filtrarTodos());

            return "admin/formProfesor";
        } else {
            return "redirect:/admin/profesor";
        }

    }

    @PostMapping("/admin/editProfesor")
    public String editorAlumno(@ModelAttribute("profesor") Profesor profesor, @AuthenticationPrincipal Usuario currentUser) {

        if (currentUser != null && currentUser.getDni().equals(profesor.getDni())) {
            return "redirect:/inicio?errorAccion=true";
        }

        
        servicio.modificar(profesor);
        profesor.creadorUsername();

        return "redirect:/admin/profesor";
    }

    @GetMapping("/admin/eliminar/profesor/{dni}")
    public String borradorExamen(@PathVariable("dni") String dni, @AuthenticationPrincipal Usuario currentUser) {

        if (currentUser != null && currentUser.getDni().equals(dni)) {
            return "redirect:/inicio?errorAccion=true";
        }

        Optional<Profesor> profesor = servicio.filtrarPorId(dni);

        if (profesor.isPresent()) {
            if (!profesor.get().getExamen().isEmpty()) {
                throw new IllegalArgumentException("No se puede eliminar el profesor porque tiene exámenes asociados.");
            }
            servicio.eliminar(profesor.get());
        }

        return "redirect:/admin/profesor";
    }

}
