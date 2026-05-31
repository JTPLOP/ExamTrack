package com.salesianos.dam.examtrack.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import com.salesianos.dam.examtrack.model.Examen;
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

    public List<Examen> filtradorExamenes (String filtroBusqueda, int filtroEstado, String filtroAsignatura, String filtroFecha) {

        return examRepo.filtradorExamen(filtroBusqueda, filtroEstado, filtroAsignatura, filtroFecha);

    }

    

}
