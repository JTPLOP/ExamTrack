package com.salesianos.dam.examtrack.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SobreNosotrosController {

    @GetMapping("/sobreNosotros")
    public String sobreNosotrosBase() {
        return "sobreNosotros"; // Thymeleaf will resolve to sobreNosotros.html
    }
}
