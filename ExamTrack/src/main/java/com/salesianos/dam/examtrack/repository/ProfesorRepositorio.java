package com.salesianos.dam.examtrack.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.salesianos.dam.examtrack.model.Profesor;

public interface ProfesorRepositorio extends JpaRepository<Profesor,Long> {
    

}
