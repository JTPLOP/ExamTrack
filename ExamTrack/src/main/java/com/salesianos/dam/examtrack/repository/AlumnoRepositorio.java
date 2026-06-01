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

    @Query("""
             select i.examen
             from Alumno a join a.inscripcion i
             where a.dni = :dni and i.examen.fecha > :actualidad
             order by i.examen.fecha asc
             limit 5
            """)
    List<Examen> filtrarProximosExamenes(String dni, LocalDateTime actualidad);

    @Query("""
             select count(i)
             from Alumno a join a.inscripcion i join i.estados est
             where a.dni = :dni and est = :estado
            """)
    int contarExamenesPorEstadoAlumno(String dni, com.salesianos.dam.examtrack.model.InscripcionEstados estado);
    
}
