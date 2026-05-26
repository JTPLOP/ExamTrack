package com.salesianos.dam.examtrack.repository;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianos.dam.examtrack.model.Examen;

public interface ExamenRepositorio extends JpaRepository<Examen,Long>  {
    
    @Query("""
        select e.fecha
        from Examen e 
        where e.idExamen = ?1
       """)
	LocalDateTime extraerFechas(Long idExamen);


}
