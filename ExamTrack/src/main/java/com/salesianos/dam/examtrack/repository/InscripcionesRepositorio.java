package com.salesianos.dam.examtrack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianos.dam.examtrack.model.Inscripcion;
import com.salesianos.dam.examtrack.model.InscripcionPK;

public interface InscripcionesRepositorio extends JpaRepository <Inscripcion, InscripcionPK>{
    

    @Query("""
        select i
        from Inscripcion i
        where i.inscripcionPK.dni = :dni and i.inscripcionPK.idExamen = :idExamen
       """)
    Optional <Inscripcion> filtrarInscripcionPorDNI (@Param("dni") String dni, @Param("idExamen") Long idExamen);


    @Query("""
        select count(i.inscripcionPK.dni)
        from Inscripcion i
        where i.inscripcionPK.idExamen = :idExamen
       """)
    int contarAlumnosInscritos (@Param("idExamen") Long idExamen);

}
