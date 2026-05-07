package com.salesianos.dam.examtrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianos.dam.examtrack.model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    
}
