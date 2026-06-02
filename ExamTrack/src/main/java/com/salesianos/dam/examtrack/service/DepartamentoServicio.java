package com.salesianos.dam.examtrack.service;

import org.springframework.stereotype.Service;

import com.salesianos.dam.examtrack.model.Departamento;
import com.salesianos.dam.examtrack.repository.DepartamentoRepositorio;
import com.salesianos.dam.examtrack.service.base.ServicioBaseImpl;

@Service
public class DepartamentoServicio extends ServicioBaseImpl <Departamento, Long, DepartamentoRepositorio>{

    public DepartamentoServicio(DepartamentoRepositorio repositorio) {
        super(repositorio);
    }
    
    
    
}
