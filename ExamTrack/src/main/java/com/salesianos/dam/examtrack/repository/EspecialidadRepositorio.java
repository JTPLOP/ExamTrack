package com.salesianos.dam.examtrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianos.dam.examtrack.model.Especialidad;

public interface EspecialidadRepositorio extends JpaRepository<Especialidad, Long> {
    
}
