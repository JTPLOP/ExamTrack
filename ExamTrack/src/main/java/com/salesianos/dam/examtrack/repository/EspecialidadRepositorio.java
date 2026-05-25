package com.salesianos.dam.examtrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianos.dam.examtrack.model.Especialidad;
import com.salesianos.dam.examtrack.model.Profesor;

public interface EspecialidadRepositorio extends JpaRepository<Especialidad, Long> {

}
