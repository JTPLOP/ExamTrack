package com.salesianos.dam.examtrack.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import com.salesianos.dam.examtrack.model.Alumno;
import com.salesianos.dam.examtrack.repository.AlumnoRepositorio;
import com.salesianos.dam.examtrack.repository.InscripcionesRepositorio;
import com.salesianos.dam.examtrack.service.base.ServicioBaseImpl;

@Service
public class AlumnoServicio extends ServicioBaseImpl <Alumno, String, AlumnoRepositorio> {

    public AlumnoServicio(AlumnoRepositorio repositorio) {
        super(repositorio);
    }
    
    

    

}
