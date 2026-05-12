package com.salesianos.dam.examtrack.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.salesianos.dam.examtrack.model.Alumno;

public interface AlumnoRepositorio extends JpaRepository<Alumno, String> {
    
}
