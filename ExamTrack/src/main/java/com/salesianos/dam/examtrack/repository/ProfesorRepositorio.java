package com.salesianos.dam.examtrack.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianos.dam.examtrack.model.Profesor;

public interface ProfesorRepositorio extends JpaRepository<Profesor,String> {
    
    @Query("""
        select e.nombre
        from Profesor p join p.especialidades e
        where p.dni = :dni
       """)
	List<String> filtrarEspecialidades(String dni);
       
}
