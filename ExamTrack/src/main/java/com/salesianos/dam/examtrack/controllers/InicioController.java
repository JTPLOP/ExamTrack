package com.salesianos.dam.examtrack.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.service.ExamenServicio;
import com.salesianos.dam.examtrack.service.InscripcionesServicio;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InicioController {

    private final InscripcionesServicio inscripServicio;
    private final ExamenServicio examServicio;
    
    @GetMapping ("/inicio")
    public String inicioBase (Model model, @AuthenticationPrincipal Profesor profesor) {

        Map <Integer, Double> estadisticasBase = new HashMap<>();
        int mesSeleccionado;
        LocalDate fecha = LocalDate.now();
        String nombreMes [] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        mesSeleccionado = fecha.getMonthValue();

        
        /*Estadisticas basicas  */
        estadisticasBase.put(1, inscripServicio.contarAllAlumnos(profesor.getDni()));
        estadisticasBase.put(3, examServicio.contarExamenesMes());

        estadisticasBase.put(2, inscripServicio.porcentajeAsistenciaMes(mesSeleccionado));
        estadisticasBase.put(4, inscripServicio.porcentajeAprobadosMes(mesSeleccionado));


        model.addAttribute("nombreMes", nombreMes[mesSeleccionado-1]);

        model.addAttribute("estadisticasBase", estadisticasBase);
        
        return "inicio";
    }

    @GetMapping("/api/grafica/datos")
    @ResponseBody
    public Map<String, Object> obtenerDatosGrafica() {

        List<Double> aprobados = inscripServicio.porcentajeAprobadosPorMes();
        List<Double> suspensos = inscripServicio.porcentajeSuspensosPorMes();

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("aprobados", aprobados);
        resultado.put("suspensos", suspensos);

        return resultado;
    }

    
}

