package com.salesianos.dam.examtrack.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.salesianos.dam.examtrack.model.Departamento;
import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.model.Usuario;
import com.salesianos.dam.examtrack.model.UsuarioRol;
import com.salesianos.dam.examtrack.repository.DepartamentoRepositorio;
import com.salesianos.dam.examtrack.repository.UsuarioRepositorio;
import com.salesianos.dam.examtrack.security.PasswordEncoderConfig;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitData {

	private final UsuarioRepositorio repo;
	private final DepartamentoRepositorio depaRepo;
	private final PasswordEncoder encoder;

	@PostConstruct
	public void init() {

		Profesor profesor = new Profesor();

		Profesor defecto = Profesor.builder()
			.dni("AA1")
			.nombre("Jose")
			.primerApellido("Tapia")
			.segundoApellido("Lopez")
			.username("user@user.com")
			.password(encoder.encode("12345"))
			.email("user@user.com")
			.direccion("Bosco")
			.fechaNacimiento(LocalDate.now())
			.rol(UsuarioRol.PROFESOR)
			.fotoPerfil("https://i.redd.it/sby4ealaiaoe1.png")
			.especialidad("Ingles")
			.examen(profesor.getExamen())
			.build();

		repo.save(defecto);

		Usuario admin = Usuario.builder()
				.dni("1B")
				.email("admin@admin.com")
				.username("admin@admin.com")
				.password(encoder.encode("12345"))
				.rol(UsuarioRol.ADMIN)
				.build();

		repo.save(admin);

		Departamento depaMates = Departamento.builder()
		.nombre("Matematicas")
		.build();

		Departamento depaFisica = Departamento.builder()
		.nombre("Fisica")
		.build();

		Departamento depaIngles = Departamento.builder()
		.nombre("Ingles")
		.build();

		depaRepo.save(depaMates);
		depaRepo.save(depaFisica);
		depaRepo.save(depaIngles);

	}

}
