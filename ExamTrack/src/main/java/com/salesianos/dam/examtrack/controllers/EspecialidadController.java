package com.salesianos.dam.examtrack.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianos.dam.examtrack.model.Especialidad;
import com.salesianos.dam.examtrack.service.EspecialidadServicio;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EspecialidadController {

    private final EspecialidadServicio servicio;

    @GetMapping("/especialidades")
    public String especialidadesBase(Model model) {
        model.addAttribute("especialidad", servicio.filtrarTodos());
        return "especialidades";
    }

    @GetMapping("/formEspecialidad")
    public String formularioEspecialidad(Model model) {
        model.addAttribute("especialidad", new Especialidad());
        return "formEspecialidad";
    }

    @PostMapping("/crearEspecialidad")
    public String creadorEspecialidad(@Valid @ModelAttribute("especialidad") Especialidad especialidad, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "formEspecialidad";
        }

        servicio.agregar(especialidad);

        return "redirect:/especialidades";
    }

    @GetMapping("/editar/especialidad/{id}")
    public String modificarEspecialidad(@PathVariable("id") Long id, Model model) {

        Optional<Especialidad> especialidad = servicio.filtrarPorId(id);

        if (especialidad.isPresent()) {
            model.addAttribute("especialidad", especialidad.get());
            return "formEspecialidad";
        } else {
            return "redirect:/especialidades";
        }
    }

    @PostMapping("/editEspecialidad")
    public String editorEspecialidad(@Valid @ModelAttribute("especialidad") Especialidad especialidad, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "formEspecialidad";
        }

        servicio.modificar(especialidad);

        return "redirect:/especialidades";
    }

    

}
