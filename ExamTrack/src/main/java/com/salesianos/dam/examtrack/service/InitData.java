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
	private final PasswordEncoder encoder;

	@PostConstruct
	public void init() {

		Usuario profesor = Usuario.builder()
				.dni("1A")
				.email("user@user.com")
				.username("user@user.com")
				.password(encoder.encode("12345"))
				.rol(UsuarioRol.PROFESOR)
				.build();

		repo.save(profesor);

		Usuario admin = Usuario.builder()
				.dni("1B")
				.email("admin@admin.com")
				.username("admin@admin.com")
				.password(encoder.encode("12345"))
				.rol(UsuarioRol.ADMIN)
				.build();

		repo.save(admin);

	}

}
