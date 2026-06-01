package com.salesianos.dam.examtrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianos.dam.examtrack.model.Departamento;
import com.salesianos.dam.examtrack.service.DepartamentoServicio;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DepartamentoController {

    private final DepartamentoServicio servicio;

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

        return "redirect:/formDepartamento";
    }

}
