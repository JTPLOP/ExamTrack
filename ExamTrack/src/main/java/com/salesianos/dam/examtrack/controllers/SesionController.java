package com.salesianos.dam.examtrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SesionController {
    
    /* ENDPOINTS PRINCIPALES */

    @GetMapping ("/logout")
    public String logoutBase (Model model) {

        return "logout";
    }
 
    /*LOGICA DEL LOGIN */
    @PostMapping ("/loginForm")
    public String formularioLogin (Model model) {


        System.out.println("Hemos entrado aqui");

        return "redirect:/inicio";
    }


}
