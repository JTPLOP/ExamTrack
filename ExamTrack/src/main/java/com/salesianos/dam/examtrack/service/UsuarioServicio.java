package com.salesianos.dam.examtrack.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.salesianos.dam.examtrack.model.Usuario;
import com.salesianos.dam.examtrack.model.UsuarioRol;
import com.salesianos.dam.examtrack.repository.UsuarioRepositorio;
import com.salesianos.dam.examtrack.service.base.ServicioBaseImpl;

@Service

public class UsuarioServicio extends ServicioBaseImpl <Usuario, String, UsuarioRepositorio> {
    
    private final UsuarioRepositorio userRepo;
    private final PasswordEncoder encoder;
    
    public UsuarioServicio(UsuarioRepositorio repositorio, UsuarioRepositorio userRepo, PasswordEncoder encoder) {
        super(repositorio);
        this.userRepo = userRepo;
        this.encoder = encoder;
    }


    public String encriptadorPasswords (String password) {

        if (password == null) 
            password = "Default";
            
        return encoder.encode(password);
        
    }

    public UsuarioRol filtrarRol (String dni) {
        
        Usuario user = userRepo.extraerUsuario(dni).orElseThrow();
        return user.getRol();
    }

}
