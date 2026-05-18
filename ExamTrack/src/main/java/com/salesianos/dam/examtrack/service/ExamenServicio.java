package com.salesianos.dam.examtrack.service;

import org.springframework.stereotype.Service;
import com.salesianos.dam.examtrack.model.Examen;
import com.salesianos.dam.examtrack.repository.ExamenRepositorio;
import com.salesianos.dam.examtrack.service.base.ServicioBaseImpl;

@Service
public class ExamenServicio extends ServicioBaseImpl <Examen, Long, ExamenRepositorio> {

    public ExamenServicio(ExamenRepositorio repositorio) {
        super(repositorio);
    }

    


}
