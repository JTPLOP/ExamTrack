package com.salesianos.dam.examtrack.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.Query;

import com.salesianos.dam.examtrack.model.Alumno;
import com.salesianos.dam.examtrack.model.Examen;
import com.salesianos.dam.examtrack.model.Examen;

public interface AlumnoRepositorio extends JpaRepository<Alumno, String> {

    public List<Alumno> findByNombreIgnoreCase(String nombre);

    @Query("""
             select a
             from Alumno a join a.asignaturas asig
             where asig like :asignatura
            """)
    List<Alumno> filtrarPorAsignatura(String asignatura);
}
