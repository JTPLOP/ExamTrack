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

    @GetMapping("/departamentos")
    public String departamentosBase(Model model) {
        model.addAttribute("departamento", servicio.filtrarTodos());
        return "departamentos";
    }

    @GetMapping("/formDepartamento")
    public String formularioDepartamento(Model model) {
        model.addAttribute("departamento", new Departamento());
        return "formDepartamento";
    }

    @PostMapping("/crearDepartamento")
    public String creadorDepartamento(@Valid @ModelAttribute("departamento") Departamento departamento, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "formDepartamento";
        }

        servicio.agregar(departamento);

        return "redirect:/departamentos";
    }

    @GetMapping("/editar/departamento/{id}")
    public String modificarDepartamento(@PathVariable("id") Long id, Model model) {

        Optional<Departamento> departamento = servicio.filtrarPorId(id);

        if (departamento.isPresent()) {
            model.addAttribute("departamento", departamento.get());
            return "formDepartamento";
        } else {
            return "redirect:/departamentos";
        }
    }

    @PostMapping("/editDepartamento")
    public String editorDepartamento(@Valid @ModelAttribute("departamento") Departamento departamento, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "formDepartamento";
        }

        servicio.modificar(departamento);

        return "redirect:/departamentos";
    }

    @GetMapping("/eliminar/departamento/{id}")
    public String eliminarDepartamento(@PathVariable("id") Long id) {
        servicio.eliminarPorId(id);
        return "redirect:/departamentos";
    }

}
