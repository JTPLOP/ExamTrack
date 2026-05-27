package com.salesianos.dam.examtrack.service;

import org.springframework.stereotype.Service;

import com.salesianos.dam.examtrack.model.Alumno;
import com.salesianos.dam.examtrack.model.Examen;
import com.salesianos.dam.examtrack.model.Inscripcion;
import com.salesianos.dam.examtrack.model.InscripcionPK;
import com.salesianos.dam.examtrack.repository.InscripcionesRepositorio;
import com.salesianos.dam.examtrack.service.base.ServicioBaseImpl;

@Service
public class InscripcionesServicio extends ServicioBaseImpl<Inscripcion, InscripcionPK, InscripcionesRepositorio> {

    private final InscripcionesRepositorio inscripcionRepo;

    public InscripcionesServicio(InscripcionesRepositorio repositorio, InscripcionesRepositorio inscripcionRepo) {
        super(repositorio);
        this.inscripcionRepo = inscripcionRepo;
    }

    public boolean gestionInscripcion(Alumno alumno, Examen examen) {

        if (inscripcionRepo.filtrarInscripcionPorDNI(alumno.getDni(), examen.getIdExamen()).isEmpty()) {
            InscripcionPK inscripcionPK = new InscripcionPK();
            inscripcionPK.setDni(alumno.getDni());
            inscripcionPK.setIdExamen(examen.getIdExamen());

            Inscripcion nuevaInscripcion = new Inscripcion();
            nuevaInscripcion.addToExamen(examen);
            nuevaInscripcion.addToAlumno(alumno);

            inscripcionRepo.save(nuevaInscripcion);

            System.out.println("Agregado excelente");
            nuevaInscripcion.toString();
            
            return true; 
        }else {
            Inscripcion inscripcion = inscripcionRepo.filtrarInscripcionPorDNI(alumno.getDni(), examen.getIdExamen()).orElseThrow();

            inscripcion.removeToAlumno(alumno);
            inscripcion.removeToExamen(examen);

            inscripcionRepo.delete(inscripcion);

            System.out.println("Borrado Correcto");

            return false;

            /*A mejorar para el futuro podriamos poner que se pueda desasignar un alumno pero que el examen siga registrado y mediante
            un filtro si hay una inscripcion sin alumno pero si con examen que se le asigne */
        }

    }

}
