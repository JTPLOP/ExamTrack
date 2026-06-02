package com.salesianos.dam.examtrack.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.salesianos.dam.examtrack.model.Alumno;
import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.service.AlumnoServicio;
import com.salesianos.dam.examtrack.service.ProfesorServicio;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AlumnoController {

    private final AlumnoServicio servicio;
    private final ProfesorServicio profeServicio;

    @GetMapping("/alumnos")
    public String alumnosBase(Model model, @AuthenticationPrincipal Profesor profesores) {
        model.addAttribute("alumno", servicio.filtrarTodos());
        model.addAttribute("especialidades", profeServicio.filtrarEspecialidades(profesores.getDni()));

        return "alumnos";
    }

    @GetMapping("/formAlumno")
    public String formularioAlumno(Model model) {
        model.addAttribute("alumno", new Alumno());

        return "formAlumnos";
    }

    @PostMapping("/crearAlumno")
    public String creadorAlumno(@Valid @ModelAttribute("alumno") Alumno alumno, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "formAlumnos";
        }

        servicio.agregar(alumno);
        alumno.depurarDatos();

        return "redirect:/alumnos";

    }

    @GetMapping("/editar/alumno/{dni}")
    public String modificarAlumno(@PathVariable("dni") String dni, Model model) {

        Optional<Alumno> alumno = servicio.filtrarPorId(dni); // aprender a filtrar por dni (como hacer consultas)

        if (alumno.isPresent()) {
            model.addAttribute("alumno", alumno.get());

            return "formAlumnos";
        } else {
            return "redirect:/alumnos";
        }

    }

    @PostMapping("/editAlumno")
    public String editorAlumno(@ModelAttribute("alumno") Alumno alumno) {

        servicio.modificar(alumno);

        return "redirect:/alumnos";
    }

    @GetMapping("/eliminar/alumno/{dni}")
    public String borradorAlumnos(@PathVariable("dni") String dni) {

        Optional<Alumno> alumno = servicio.filtrarPorId(dni);

        if (alumno.isPresent())
            servicio.eliminar(alumno.get());

        return "redirect:/alumnos";
    }

    @GetMapping("/alumno/asignatura/add/{dni}")
    public String agregarAsignatura(@PathVariable("dni") String dni,
            @RequestParam(name = "nuevasAsignaturas", required = false, defaultValue = "0") List<String> nuevasAsignaturas,
            HttpServletRequest request) {

        System.out.println("AQUI RESULTADO DE NUEVAS ASIGNATURAS:\n\n\n" + nuevasAsignaturas.toString());

        if (nuevasAsignaturas.contains("0")) {
            return "redirect:/alumnos";
        } else {
            Optional<Alumno> alumBuscado = servicio.filtrarPorId(dni);

            if (!alumBuscado.isPresent()) {
                System.out.println("Me fui");
                return "redirect:/alumnos";
            }

            Alumno alumno = alumBuscado.get();

            System.out.println("Agregando asignaturas");
            for (String asignatura : nuevasAsignaturas) {
                alumno.getAsignaturas().add(asignatura);

                System.out.println("MOSTRAR ASIGNATURA\n\n\n" + asignatura);
                alumno.getAsignaturas().toString();
            }

            servicio.modificar(alumno);

            return "redirect:" + request.getHeader("Referer");
        }

    }

    @GetMapping ("/alumno/asignatura/remove/{dni}")
    public String eliminarAsignatura (@PathVariable ("dni") String dni , 
                                      @RequestParam(name="asignaturasActuales", required=false) List<String> asignaturasActuales,
                                      @RequestParam(name="asignaturasMantenidas", required=false) List<String> asignaturasMantenidas, 
                                      HttpServletRequest request) {
        
        Optional <Alumno> alumBuscado = servicio.filtrarPorId(dni);

        if (!alumBuscado.isPresent()) {
            return "redirect:/alumnos";
        }

        Alumno alumno = alumBuscado.get();

        if (asignaturasActuales != null) {
            if (asignaturasMantenidas == null) {
                asignaturasMantenidas = new java.util.ArrayList<>();
            }
            
            for (String actual : asignaturasActuales) {
                if (!asignaturasMantenidas.contains(actual)) {
                    alumno.getAsignaturas().remove(actual);
                }
            }
            
            servicio.modificar(alumno);
        }

        return "redirect:" + request.getHeader("Referer");
    }

}
