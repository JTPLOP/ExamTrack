package com.salesianos.dam.examtrack.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.repository.ProfesorRepositorio;
import com.salesianos.dam.examtrack.service.base.ServicioBaseImpl;


@Service

public class ProfesorServicio extends ServicioBaseImpl <Profesor, String, ProfesorRepositorio> {

    private final ProfesorRepositorio profesorRepositorio;

    public ProfesorServicio(ProfesorRepositorio repositorio, ProfesorRepositorio profesorRepositorio) {
        super(repositorio);
        this.profesorRepositorio = profesorRepositorio;
    }


    public List<String> filtrarEspecialidades (String dni) {
        return this.profesorRepositorio.filtrarEspecialidades(dni);
    } 

    public List<String> filtrarTodasEspecialidades () {
        return this.profesorRepositorio.filtrarTodasEspecialidades();
    } 

}
