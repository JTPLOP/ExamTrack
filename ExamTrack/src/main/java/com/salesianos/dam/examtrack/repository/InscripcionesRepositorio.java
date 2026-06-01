package com.salesianos.dam.examtrack.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianos.dam.examtrack.model.Alumno;
import com.salesianos.dam.examtrack.model.Inscripcion;
import com.salesianos.dam.examtrack.model.InscripcionEstados;
import com.salesianos.dam.examtrack.model.InscripcionPK;

public interface InscripcionesRepositorio extends JpaRepository<Inscripcion, InscripcionPK> {

    @Query("""
             select i
             from Inscripcion i
             where i.inscripcionPK.dni = :dni and i.inscripcionPK.idExamen = :idExamen
            """)
    Optional<Inscripcion> filtrarInscripcionPorDNI(@Param("dni") String dni, @Param("idExamen") Long idExamen);

    @Query("""
             select count(i.inscripcionPK.dni)
             from Inscripcion i
             where i.inscripcionPK.idExamen = :idExamen
            """)
    int contarAlumnosInscritos(@Param("idExamen") Long idExamen);

    @Query("""
             select i
             from Inscripcion i
             where i.inscripcionPK.idExamen = :idExamen and i.inscripcionPK.dni = :dni
            """)
    Optional<Inscripcion> filtrarInscripcion(String dni, Long idExamen);

    @Query("""
             select a
             from Inscripcion i join i.alumno a join i.examen e
             where i.calificacion is null and e.fecha < :actualidad
            """)
    List<Alumno> filtrarAlumnosSinNotas(LocalDateTime actualidad);

    @Query("""
             select count(a.dni)
             from Inscripcion i join i.alumno a join i.examen e
             where e.profesor.dni = :dni and e.fecha > :actualidad
            """)
    int contarAllAlumnos(LocalDateTime actualidad, String dni);

    @Query("""
             select count(a.dni)
             from Inscripcion i join i.alumno a join i.examen e
             where e.profesor.dni = :dni and e.fecha > :actualidad
            """)
    int contarAlumnosInscritoMesPasado(LocalDateTime actualidad, String dni); //filtrar fecha 30 dias

    @Query("""
             select count(a.dni)
             from Inscripcion i join i.alumno a join i.examen e
             where e.profesor.dni = :dni and e.fecha > :actualidad 
            """)
    int contarAlumnosPresentadoMesPasado(LocalDateTime actualidad, String dni, InscripcionEstados presente);
    
    
}
