package com.salesianos.dam.examtrack.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import com.salesianos.dam.examtrack.model.Examen;
import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.repository.ExamenRepositorio;
import com.salesianos.dam.examtrack.service.base.ServicioBaseImpl;

@Service

public class ExamenServicio extends ServicioBaseImpl <Examen, Long, ExamenRepositorio> {

    private final ExamenRepositorio examRepo;


    public ExamenServicio(ExamenRepositorio repositorio, ExamenRepositorio examRepo) {
        super(repositorio);
        this.examRepo = examRepo;
    }

    public boolean comprobarLimFecha (Long id) {
        
        if (LocalDateTime.now().isBefore(examRepo.extraerFechas(id)) ) {
            return true;
        } else {
            return false;
        }
    }

    public List<Examen> filtrarProximosExamenes (String dni) {

        LocalDateTime fechaActual = LocalDateTime.now();

        return examRepo.filtrarProximosExamenes(dni, fechaActual);

    }

    public List<Examen> filtrarProximosExamenesAdmin () {
        LocalDateTime fechaActual = LocalDateTime.now();
        return examRepo.filtrarProximosExamenesAdmin(fechaActual);
    }

    public Optional <List<Examen>> filtrarExamenesProfesor (String dni) {
        
        return examRepo.filtrarExamenesProfesor(dni);
    }

    public Optional <List<Examen>> filtrarExamenesMes (String dni, int numMes) {
        
        return examRepo.filtrarExamenesMes(dni, numMes);
    }

    public double contarExamenesMes () {
        LocalDate fecha = LocalDate.now();
        int numMes = fecha.getMonthValue();

        return examRepo.contarExamenesMes(numMes);
    }
    

}
