package com.salesianos.dam.examtrack.controllers;

import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.model.UsuarioRol;
import com.salesianos.dam.examtrack.service.AlumnoServicio;
import com.salesianos.dam.examtrack.service.ProfesorServicio;
import com.salesianos.dam.examtrack.service.UsuarioServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PerfilController {

    private final AlumnoServicio alumService;
    private final UsuarioServicio userServicio;
    private final ProfesorServicio profeServicio;

    @GetMapping("/perfil/{dni}")
    public String userPerfilBase(Model model, @PathVariable("dni") String dni, @AuthenticationPrincipal Profesor profesor) {

        Optional<UsuarioRol> rol = userServicio.filtrarRol(dni);

        if (rol.isEmpty())
            return "redirect:/inicio";

        UsuarioRol rolExtraido = rol.get();

        switch (rolExtraido) {
            case ADMIN:
                model.addAttribute("usuario", userServicio.filtrarPorId(dni).get());
                break;
            case PROFESOR:
                model.addAttribute("usuario", profeServicio.filtrarPorId(dni).get());
                
                break;
            case ALUMNO:
                model.addAttribute("usuario", alumService.filtrarPorId(dni).get());
                model.addAttribute("especialidades", profeServicio.filtrarEspecialidades(profesor.getDni()));
                break;
            default:
                return "redirect:/inicio";
        }

        return "userPerfil";
    }

}
