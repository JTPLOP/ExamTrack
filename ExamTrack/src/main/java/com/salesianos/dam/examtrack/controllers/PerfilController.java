package com.salesianos.dam.examtrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    
    @GetMapping ("/perfil/{dni}")
    public String userPerfilBase (Model model, @PathVariable("dni") String dni) {

        UsuarioRol rolExtraido = userServicio.filtrarRol(dni);

        switch (rolExtraido) {
            case ADMIN:
                model.addAttribute("usuario", userServicio.filtrarPorId(dni));
                break;
            case PROFESOR:
                model.addAttribute("usuario", profeServicio.filtrarPorId(dni));
                break;

            case ALUMNO:
                    model.addAttribute("usuario", alumService.filtrarPorId(dni));
                break;

            default:
                    return "redirect:/inicio";
                
        }
        
        return "userPerfil";
    }

}
