package com.salesianos.dam.examtrack.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.salesianos.dam.examtrack.model.Departamento;

public interface DepartamentoRepositorio extends JpaRepository<Departamento, Long> {
    
}
