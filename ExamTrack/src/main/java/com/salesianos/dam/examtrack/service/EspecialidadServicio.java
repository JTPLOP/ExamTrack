package com.salesianos.dam.examtrack.service;


import org.springframework.stereotype.Service;
import com.salesianos.dam.examtrack.model.Especialidad;
import com.salesianos.dam.examtrack.repository.EspecialidadRepositorio;
import com.salesianos.dam.examtrack.service.base.ServicioBaseImpl;

@Service
public class EspecialidadServicio extends ServicioBaseImpl <Especialidad, Long, EspecialidadRepositorio> {

    private final EspecialidadRepositorio especialidadRepositorio;

    public EspecialidadServicio(EspecialidadRepositorio repositorio, EspecialidadRepositorio especialidadRepositorio) {
        super(repositorio);
        this.especialidadRepositorio = especialidadRepositorio;
    }

    
    
}
