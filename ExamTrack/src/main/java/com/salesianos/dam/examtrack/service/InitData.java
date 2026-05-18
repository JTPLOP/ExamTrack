package com.salesianos.dam.examtrack.service;

import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.salesianos.dam.examtrack.model.Usuario;
import com.salesianos.dam.examtrack.model.UsuarioRol;
import com.salesianos.dam.examtrack.repository.UsuarioRepositorio;
import com.salesianos.dam.examtrack.security.PasswordEncoderConfig;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitData {
	
	private final UsuarioRepositorio repo;
	private final PasswordEncoderConfig encoder;
	
	@PostConstruct
	public void init() {
		
		Usuario alumno = Usuario.builder()
				.email("user@user.com")
				.username("user")
				.password(encoder.encode("123"))
				.role(UsuarioRol.ALUMNO)
				.build();
		
		repo.save(alumno);
		
		
		Usuario admin = Usuario.builder()
				.email("admin@admin.com")
				.username("admin")
				.password(encoder.encode("admin"))
				.rol(UsuarioRol.ADMIN)
				.build();
	
		repo.save(admin);
		
	}
	

}
