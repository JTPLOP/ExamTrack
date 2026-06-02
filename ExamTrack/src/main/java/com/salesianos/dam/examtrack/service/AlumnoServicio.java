package com.salesianos.dam.examtrack.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.salesianos.dam.examtrack.model.Alumno;
import com.salesianos.dam.examtrack.model.Examen;
import com.salesianos.dam.examtrack.model.InscripcionEstados;
import com.salesianos.dam.examtrack.repository.AlumnoRepositorio;
import com.salesianos.dam.examtrack.repository.InscripcionesRepositorio;
import com.salesianos.dam.examtrack.service.base.ServicioBaseImpl;

@Service
public class AlumnoServicio extends ServicioBaseImpl <Alumno, String, AlumnoRepositorio> {

    private final AlumnoRepositorio alumRepo;

    public AlumnoServicio(AlumnoRepositorio repositorio, AlumnoRepositorio alumRepo) {
        super(repositorio);
        this.alumRepo = alumRepo;
    }

    public List <Alumno> filtrarPorAsignatura (String asignatura) {

        return alumRepo.filtrarPorAsignatura("%" + asignatura + "%");
        
    }

    public List<Examen> filtrarProximosExamenes(String dni) {
        return alumRepo.filtrarProximosExamenes(dni, LocalDateTime.now());
    }

    public double porcentajeAprobado(String dni) {
        int aprobados = alumRepo.contarExamenesPorEstadoAlumno(dni, InscripcionEstados.APROBADO);
        int suspensos = alumRepo.contarExamenesPorEstadoAlumno(dni, InscripcionEstados.SUSPENDIDO);
        int total = aprobados + suspensos;

        if (total == 0) {
            return 0.0;
        }

        return (aprobados * 100.0) / total;
    }

}
