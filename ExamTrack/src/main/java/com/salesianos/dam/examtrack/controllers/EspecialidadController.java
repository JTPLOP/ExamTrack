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

    @GetMapping("/admin/especialidades")
    public String especialidadesBase(Model model) {
        model.addAttribute("especialidad", servicio.filtrarTodos());
        return "admin/especialidades";
    }

    @GetMapping("/admin/formEspecialidad")
    public String formularioEspecialidad(Model model) {
        model.addAttribute("especialidad", new Especialidad());
        return "admin/formEspecialidad";
    }

    @PostMapping("/admin/crearEspecialidad")
    public String creadorEspecialidad(@Valid @ModelAttribute("especialidad") Especialidad especialidad, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "admin/formEspecialidad";
        }

        servicio.agregar(especialidad);

        return "redirect:/admin/especialidades";
    }

    @GetMapping("/admin/editar/especialidad/{id}")
    public String modificarEspecialidad(@PathVariable("id") Long id, Model model) {

        Optional<Especialidad> especialidad = servicio.filtrarPorId(id);

        if (especialidad.isPresent()) {
            model.addAttribute("especialidad", especialidad.get());
            return "admin/formEspecialidad";
        } else {
            return "redirect:/admin/especialidades";
        }
    }

    @PostMapping("/admin/editEspecialidad")
    public String editorEspecialidad(@Valid @ModelAttribute("especialidad") Especialidad especialidad, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "admin/formEspecialidad";
        }

        servicio.modificar(especialidad);

        return "redirect:/admin/especialidades";
    }

    @GetMapping("/admin/eliminar/especialidad/{id}")
    public String eliminarEspecialidad(@PathVariable("id") Long id) {
        Optional<Especialidad> especialidadOpt = servicio.filtrarPorId(id);
        if (especialidadOpt.isPresent()) {
            Especialidad especialidad = especialidadOpt.get();
            if (!especialidad.getProfesores().isEmpty()) {
                throw new IllegalArgumentException("No se puede eliminar la especialidad porque tiene profesores asociados.");
            }
            servicio.eliminarPorId(id);
        }
        return "redirect:/admin/especialidades";
    }

}
