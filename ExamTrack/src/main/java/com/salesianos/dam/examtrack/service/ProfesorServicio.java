package com.salesianos.dam.examtrack.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.repository.ProfesorRepositorio;
import com.salesianos.dam.examtrack.excepciones.UsuarioDuplicadoException;
import com.salesianos.dam.examtrack.repository.UsuarioRepositorio;
import com.salesianos.dam.examtrack.service.base.ServicioBaseImpl;


@Service

public class ProfesorServicio extends ServicioBaseImpl <Profesor, String, ProfesorRepositorio> {

    private final ProfesorRepositorio profesorRepositorio;
    private final UsuarioRepositorio usuRepo;

    public ProfesorServicio(ProfesorRepositorio repositorio, ProfesorRepositorio profesorRepositorio, UsuarioRepositorio usuRepo) {
        super(repositorio);
        this.profesorRepositorio = profesorRepositorio;
        this.usuRepo = usuRepo;
    }

    @Override
    public Profesor agregar(Profesor profesor) {
        if (usuRepo.existsById(profesor.getDni())) {
            throw new UsuarioDuplicadoException("El DNI introducido ya está registrado en el sistema.");
        }
        if (usuRepo.existsByEmail(profesor.getEmail())) {
            throw new UsuarioDuplicadoException("El correo electrónico introducido ya está registrado en el sistema.");
        }
        return super.agregar(profesor);
    }


    public List<String> filtrarEspecialidades (String dni) {
        return this.profesorRepositorio.filtrarEspecialidades(dni);
    } 

    public List<String> filtrarTodasEspecialidades () {
        return this.profesorRepositorio.filtrarTodasEspecialidades();
    } 

}
