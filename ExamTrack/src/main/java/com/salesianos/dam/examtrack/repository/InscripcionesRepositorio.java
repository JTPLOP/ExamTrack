package com.salesianos.dam.examtrack.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianos.dam.examtrack.model.Alumno;
import com.salesianos.dam.examtrack.model.Examen;
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
        int contarAlumnosInscritoMesPasado(LocalDateTime actualidad, String dni); // filtrar fecha 30 dias

        @Query("""
                         select count(a.dni)
                         from Inscripcion i join i.alumno a join i.examen e
                         where e.profesor.dni = :dni and e.fecha > :actualidad
                        """)
        int contarAlumnosPresentadoMesPasado(LocalDateTime actualidad, String dni, InscripcionEstados presente);

        /*
         * Cuenta cuantas inscripciones de un mes concreto tienen ese estado (APROBADO o
         * SUSPENDIDO)
         */
        @Query("""
                         select count(i)
                         from Inscripcion i join i.examen e join i.estados est
                         where est = :estado and MONTH(e.fecha) = :numMes
                        """)
        int contarPorEstadoYMes(@Param("estado") InscripcionEstados estado, @Param("numMes") int numMes);

        /* Cuenta el total de inscripciones de un mes concreto */
        @Query("""
                         select count(i)
                         from Inscripcion i join i.examen e
                         where MONTH(e.fecha) = :numMes
                        """)
        int contarTotalPorMes(@Param("numMes") int numMes);

        @Query("""
                         select e
                         from Inscripcion i join i.examen e
                         where e.profesor.dni = :dni
                         group by e
                         order by count(i) desc
                         limit 3
                        """)
        List<Examen> filtrarExamenesMasInscripciones(String dni);

        @Query("""
                         select e.asignatura
                         from Inscripcion i join i.examen e
                         where e.profesor.dni = :dni
                         group by e.asignatura
                         order by count(i) desc
                         limit 3
                        """)
        List<String> filtrarAsignaturasMasInscripciones(String dni);

        @Query("""
                         select a
                         from Inscripcion i join i.examen e join i.alumno a
                         where e.profesor.dni = :dni
                         group by a
                         order by count(i) desc
                         limit 3
                        """)
        List<Alumno> filtrarAlumnosConMasInscripciones(String dni);

        @Query("""
                         select e
                         from Inscripcion i join i.examen e
                         group by e
                         order by count(i) desc
                         limit 3
                        """)
        List<Examen> filtrarExamenesMasInscripcionesAdmin();

        @Query("""
                         select e.asignatura
                         from Inscripcion i join i.examen e
                         group by e.asignatura
                         order by count(i) desc
                         limit 3
                        """)
        List<String> filtrarAsignaturasMasInscripcionesAdmin();

        @Query("""
                         select a
                         from Inscripcion i join i.examen e join i.alumno a
                         group by a
                         order by count(i) desc
                         limit 3
                        """)
        List<Alumno> filtrarAlumnosConMasInscripcionesAdmin();

        @Query("""
                         select count(a.dni)
                         from Inscripcion i join i.alumno a join i.examen e
                         where e.fecha > :actualidad
                        """)
        int contarAllAlumnosAdmin(LocalDateTime actualidad);

        @Query("""
                         select count(i)
                         from Inscripcion i join i.examen e
                         where e.profesor.dni = :dni and i.observaciones is not null
                        """)
        int contarAlumnosEvaluados(String dni);
        
        @Query("""
             select count(e.idExamen)
             from Inscripcion i join i.examen e
             where MONTH(e.fecha) = :numMes 
            """)
    int contarExamenesMes(int numMes);

     @Query("""
             select count(e.idExamen)
             from Inscripcion i join i.examen e
             where MONTH(e.fecha) = :numMes and e.profesor.dni = :dni
            """)
    int contarExamenesMesMaestro(int numMes, String dni);

}
