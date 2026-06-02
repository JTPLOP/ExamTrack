package com.salesianos.dam.examtrack.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianos.dam.examtrack.model.Alumno;
import com.salesianos.dam.examtrack.model.Examen;
import com.salesianos.dam.examtrack.model.Inscripcion;
import com.salesianos.dam.examtrack.service.AlumnoServicio;
import com.salesianos.dam.examtrack.service.ExamenServicio;
import com.salesianos.dam.examtrack.service.InscripcionesServicio;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class InscripcionesController {

    private final ExamenServicio examService;
    private final AlumnoServicio alumServicio;
    private final InscripcionesServicio inscripServicio;

    @GetMapping("/examen/inscripciones/{id}")
    public String verInscripciones(@PathVariable("id") Long id, Model model) {
        Optional<Examen> examen = examService.filtrarPorId(id);

        if (examen.isPresent()) {
            model.addAttribute("examen", examen.get());

            model.addAttribute("alumno", alumServicio.filtrarPorAsignatura(examen.get().getAsignatura()));
            model.addAttribute("validacion", examService.comprobarLimFecha(id));

            List<String> dniInscritos = examen.get().getInscripcion().stream()
                    .map(inscripcion -> inscripcion.getAlumno().getDni())
                    .toList();

            model.addAttribute("dniInscritos", dniInscritos);

            return "inscripciones";
        } else {
            return "redirect:/examenes";
        }
    }

    @PostMapping("/examen/inscripciones/{idInscripcion}/{idAlumno}")
    public String asignacionInscripcion(@PathVariable("idInscripcion") Long idInscripcion,
            @PathVariable("idAlumno") String idAlumno, Model model) {

        Examen examen = examService.filtrarPorId(idInscripcion).orElseThrow();
        Alumno alumno = alumServicio.filtrarPorId(idAlumno).orElseThrow();

        inscripServicio.gestionInscripcion(alumno, examen);

        return "redirect:/examen/inscripciones/" + idInscripcion;
    }

    @GetMapping("/examen/inscripciones/{idInscripcion}/evaluar/{idAlumno}")
    public String evaluarAlumno(@PathVariable("idInscripcion") Long idInscripcion,
            @PathVariable("idAlumno") String idAlumno, Model model, @ModelAttribute("inscripcion") Inscripcion inscripcion,
            @RequestParam(value = "nota", required = false, defaultValue = "0") Double nota, @RequestParam(value = "observacion", required = false, defaultValue = "0") String observacion,
            @RequestParam(value = "accion", required = true) int accion) {

           if (inscripServicio.filtrarInscripcionAlumno(idAlumno, idInscripcion).isEmpty()) {
                return "redirect:/examen/inscripciones/" + idInscripcion;
           }else {
                Inscripcion inscripAlumno = inscripServicio.filtrarInscripcionAlumno(idAlumno, idInscripcion).orElseThrow();
                Examen examen = examService.filtrarPorId(idInscripcion).orElseThrow();


                if (accion == 1) {
                    inscripServicio.inscripcionAusencia(inscripAlumno);
                    inscripServicio.modificar(inscripAlumno);
                    return "redirect:/examen/inscripciones/" + idInscripcion;

                }else if (accion == 2) {
                    inscripServicio.inscripcionPresente(inscripAlumno, nota, observacion, examen.getPuntuacionMax());
                    inscripServicio.modificar(inscripAlumno);
                    return "redirect:/examen/inscripciones/" + idInscripcion;

                }
                return "redirect:/examen/inscripciones/" + idInscripcion;
           }
        
    }

}
