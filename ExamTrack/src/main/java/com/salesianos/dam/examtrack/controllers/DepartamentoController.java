package com.salesianos.dam.examtrack.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianos.dam.examtrack.model.Departamento;
import com.salesianos.dam.examtrack.service.DepartamentoServicio;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DepartamentoController {

    private final DepartamentoServicio servicio;

    @GetMapping("/admin/departamentos")
    public String departamentosBase(Model model) {
        model.addAttribute("departamento", servicio.filtrarTodos());
        return "admin/departamentos";
    }

    @GetMapping("/admin/formDepartamento")
    public String formularioDepartamento(Model model) {
        model.addAttribute("departamento", new Departamento());
        return "admin/formDepartamento";
    }

    @PostMapping("/admin/crearDepartamento")
    public String creadorDepartamento(@Valid @ModelAttribute("departamento") Departamento departamento, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "admin/formDepartamento";
        }

        servicio.agregar(departamento);

        return "redirect:/admin/departamentos";
    }

    @GetMapping("/admin/editar/departamento/{id}")
    public String modificarDepartamento(@PathVariable("id") Long id, Model model) {

        Optional<Departamento> departamento = servicio.filtrarPorId(id);

        if (departamento.isPresent()) {
            model.addAttribute("departamento", departamento.get());
            return "admin/formDepartamento";
        } else {
            return "redirect:/admin/departamentos";
        }
    }

    @PostMapping("/admin/editDepartamento")
    public String editorDepartamento(@Valid @ModelAttribute("departamento") Departamento departamento, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "admin/formDepartamento";
        }

        servicio.modificar(departamento);

        return "redirect:/admin/departamentos";
    }

    @GetMapping("/admin/eliminar/departamento/{id}")
    public String eliminarDepartamento(@PathVariable("id") Long id) {
        Optional<Departamento> departamentoOpt = servicio.filtrarPorId(id);
        if (departamentoOpt.isPresent()) {
            Departamento departamento = departamentoOpt.get();
            if (!departamento.getListaProfesores().isEmpty()) {
                throw new IllegalArgumentException("No se puede eliminar el departamento porque tiene profesores asociados.");
            }
            servicio.eliminarPorId(id);
        }
        return "redirect:/admin/departamentos";
    }

}
