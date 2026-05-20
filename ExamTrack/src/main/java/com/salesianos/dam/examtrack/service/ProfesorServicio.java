package com.salesianos.dam.examtrack.service;

import org.springframework.stereotype.Service;

import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.repository.ProfesorRepositorio;
import com.salesianos.dam.examtrack.service.base.ServicioBaseImpl;

@Service
public class ProfesorServicio extends ServicioBaseImpl <Profesor, String, ProfesorRepositorio> {

    public ProfesorServicio(ProfesorRepositorio repositorio) {
        super(repositorio);
    }

    

    
}
