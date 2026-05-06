package com.salesianos.dam.examtrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {

    /*Esta clase se encargara de testear los diferentes metodos que no tienen relacion directa con las entidades o bloques del proyecto. */

    @GetMapping ("/")
    public String testController1 (Model model) {

        return "home";
    }

    @GetMapping ("/hola")
    public String testController2 (Model model) {

        model.addAttribute("nombre", "hola");

        return "login";
    }

}