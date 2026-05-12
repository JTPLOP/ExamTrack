package com.salesianos.dam.examtrack.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianos.dam.examtrack.model.Convocatoria;

public interface ConvocatoriaRepositorio extends JpaRepository<Convocatoria,Long>{
    
}
