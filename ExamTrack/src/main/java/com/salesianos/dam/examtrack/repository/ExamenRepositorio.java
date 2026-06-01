package com.salesianos.dam.examtrack.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.salesianos.dam.examtrack.model.Alumno;
import com.salesianos.dam.examtrack.model.Examen;
import com.salesianos.dam.examtrack.model.Profesor;

public interface ExamenRepositorio extends JpaRepository<Examen, Long> {

    @Query("""
             select e.fecha
             from Examen e
             where e.idExamen = ?1
            """)
    LocalDateTime extraerFechas(Long idExamen);

    @Query("""
             select e
             from Examen e join e.profesor p
             where p.dni = :dni and e.fecha > :actualidad
            """)
    List<Examen> filtrarProximosExamenes(String dni, LocalDateTime actualidad);

    @Query("""
             select e
             from Examen e
             where e.nombre = :nombre
            """)
    Optional < List<Examen> >  filtrarPorNombres(String nombre);

    @Query(
        value = """
        select *
        from Examen 
        where ((:filtroBusqueda = '0' or nombre = :filtroBusqueda)
                and (:filtroEstado = 0 or (CASE WHEN :filtroEstado = 1 THEN fecha >= CURRENT_DATE  ELSE fecha < CURRENT_DATE END ))
                and (:filtroAsignatura = '0' or asignatura = :filtroAsignatura)
                and (:filtroFecha = '0' or fecha = :filtroFecha));
        """, 
        nativeQuery = true
        )
    List <Examen> filtradorExamen(String filtroBusqueda, int filtroEstado, String filtroAsignatura, String filtroFecha );

    @Query("""
             select e
             from Examen e
             where e.profesor.dni = :dni
            """)
    Optional <List<Examen>>  filtrarExamenesProfesor(String dni);

    @Query("""
             select e
             from Examen e
             where e.profesor.dni = :dni and MONTH(e.fecha) = :numMes
            """)
    Optional <List<Examen>>  filtrarExamenesMes(String dni, int numMes);

    @Query("""
             select count(e.idExamen)
             from Examen e
             where MONTH(e.fecha) = :numMes
            """)
    int contarExamenesMes(int numMes);

}
