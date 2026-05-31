package com.salesianos.dam.examtrack.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.salesianos.dam.examtrack.model.Alumno;
import com.salesianos.dam.examtrack.model.Departamento;
import com.salesianos.dam.examtrack.model.Especialidad;
import com.salesianos.dam.examtrack.model.Examen;
import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.model.Usuario;
import com.salesianos.dam.examtrack.model.UsuarioRol;
import com.salesianos.dam.examtrack.repository.DepartamentoRepositorio;
import com.salesianos.dam.examtrack.repository.EspecialidadRepositorio;
import com.salesianos.dam.examtrack.repository.ExamenRepositorio;
import com.salesianos.dam.examtrack.repository.UsuarioRepositorio;
import com.salesianos.dam.examtrack.security.PasswordEncoderConfig;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitData {

	private final UsuarioRepositorio repo;
	private final DepartamentoRepositorio depaRepo;
	private final EspecialidadRepositorio especiRepo;
	private final PasswordEncoder encoder;
	private final ExamenRepositorio examenRepo;

	@PostConstruct
	public void init() {


	Departamento depaMates = Departamento.builder().nombre("Matematicas").build();
    Departamento depaFisica = Departamento.builder().nombre("Fisica").build();
    Departamento depaIngles = Departamento.builder().nombre("Ingles").build();

    depaRepo.save(depaMates);
    depaRepo.save(depaFisica);
    depaRepo.save(depaIngles);

    Especialidad especiMates = Especialidad.builder().nombre("Matematicas").build();
    Especialidad especiFisica = Especialidad.builder().nombre("Fisica").build();
    Especialidad especiIngles = Especialidad.builder().nombre("Ingles").build();
    Especialidad especiProgramacion = Especialidad.builder().nombre("Programacion").build();

    especiRepo.save(especiMates);
    especiRepo.save(especiFisica);
    especiRepo.save(especiIngles);
    especiRepo.save(especiProgramacion);

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
        .build();

    especiProgramacion.addToProfesor(defecto);
    especiIngles.addToProfesor(defecto);
    defecto.addToDepartamento(depaMates); 
    
    repo.save(defecto);

    Usuario admin = Usuario.builder()
            .dni("1B")
            .email("admin@admin.com")
            .username("admin@admin.com")
            .password(encoder.encode("12345"))
            .rol(UsuarioRol.ADMIN)
            .build();

    repo.save(admin);

		Alumno alumnoCarlos = Alumno.builder()
				.dni("111A")
				.nombre("Carlos")
				.primerApellido("Gómez")
				.segundoApellido("Sánchez")
				.username("carlos@alumno.com")
				.password(encoder.encode("12345"))
				.email("carlos@alumno.com")
				.direccion("Calle Sierpes, 45")
				.fechaNacimiento(LocalDate.of(2005, 3, 15))
				.rol(UsuarioRol.ALUMNO)
				.fotoPerfil("https://api.dicebear.com/7.x/avataaars/svg?seed=Carlos")
				.grupo("1º DAM")
				.asignaturas(List.of("Programacion", "Ingles", "Sistemas Informáticos"))
				.build();
		repo.save(alumnoCarlos);

		Alumno alumnoAna = Alumno.builder()
				.dni("222B")
				.nombre("Ana")
				.primerApellido("Martínez")
				.segundoApellido("Castro")
				.username("ana@alumno.com")
				.password(encoder.encode("12345"))
				.email("ana@alumno.com")
				.direccion("Calle Betis, 8")
				.fechaNacimiento(LocalDate.of(2006, 7, 22))
				.rol(UsuarioRol.ALUMNO)
				.fotoPerfil("https://api.dicebear.com/7.x/avataaars/svg?seed=Ana")
				.grupo("1º DAM")
				.asignaturas(List.of("Programacion", "Ingles", "Entornos de Desarrollo"))
				.build();
		repo.save(alumnoAna);

		Alumno alumnoLucas = Alumno.builder()
				.dni("333C")
				.nombre("Lucas")
				.primerApellido("Fernández")
				.segundoApellido("Marín")
				.username("lucas@alumno.com")
				.password(encoder.encode("12345"))
				.email("lucas@alumno.com")
				.direccion("Calle Asunción, 19")
				.fechaNacimiento(LocalDate.of(2004, 12, 5))
				.rol(UsuarioRol.ALUMNO)
				.fotoPerfil("https://api.dicebear.com/7.x/avataaars/svg?seed=Lucas")
				.grupo("2º DAM")
				.asignaturas(List.of("Programacion", "Desarrollo Interfaces", "Sistemas de Gestión"))
				.build();
		repo.save(alumnoLucas);

		Examen examenJava = Examen.builder()
				.nombre("Parcial Tema 4: Colecciones")
				.descripcion("Evaluación práctica orientada al uso de Lists, Sets, Maps y ordenación con Listas en Java.")
				.fecha(LocalDateTime.now().plusDays(7).withHour(9).withMinute(0))
				.aula("Aula 202 (Planta 2)")
				.puntuacionMax(10.0)
				.duracionMinutos(120.0)
				.numPlazas(30)
				.asignatura("Programación")
				.build();
		
		examenJava.addToProfesor(defecto); 
		examenRepo.save(examenJava);

		Examen examenIngles = Examen.builder()
				.nombre("Listening & Tech Vocabulary")
				.descripcion("Examen de comprensión auditiva centrado en terminología técnica de desarrollo de software.")
				.fecha(LocalDateTime.now().plusDays(4).withHour(11).withMinute(30))
				.aula("Aula Temática de Idiomas")
				.puntuacionMax(5.0)
				.duracionMinutos(45.0)
				.numPlazas(25)
				.asignatura("Inglés Técnico")
				.build();
		
		examenIngles.addToProfesor(defecto);
		examenRepo.save(examenIngles);

		Examen examenFisica = Examen.builder()
				.nombre("Examen Final de Cinemática")
				.descripcion("Resolución de problemas complejos de MRU, MRUA y Dinámica Newtoniana.")
				.fecha(LocalDateTime.now().plusDays(12).withHour(10).withMinute(0))
				.aula("Laboratorio de Física General")
				.puntuacionMax(10.0)
				.duracionMinutos(90.0)
				.numPlazas(40)
				.asignatura("Física")
				.build();
		
		examenFisica.addToProfesor(defecto);
		examenRepo.save(examenFisica);

	}


}
