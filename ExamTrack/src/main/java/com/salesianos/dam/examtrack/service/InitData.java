package com.salesianos.dam.examtrack.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.salesianos.dam.examtrack.model.Alumno;
import com.salesianos.dam.examtrack.model.Departamento;
import com.salesianos.dam.examtrack.model.Especialidad;
import com.salesianos.dam.examtrack.model.Examen;
import com.salesianos.dam.examtrack.model.Inscripcion;
import com.salesianos.dam.examtrack.model.InscripcionEstados;
import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.model.Usuario;
import com.salesianos.dam.examtrack.model.UsuarioRol;
import com.salesianos.dam.examtrack.repository.DepartamentoRepositorio;
import com.salesianos.dam.examtrack.repository.EspecialidadRepositorio;
import com.salesianos.dam.examtrack.repository.ExamenRepositorio;
import com.salesianos.dam.examtrack.repository.InscripcionesRepositorio;
import com.salesianos.dam.examtrack.repository.UsuarioRepositorio;

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
	private final InscripcionesRepositorio inscripcionRepo; // Inyectado para las pruebas asociadas

	@PostConstruct
	public void init() {

		// 1. CREACIÓN DE DEPARTAMENTOS
		Departamento depaMates = Departamento.builder().nombre("Matematicas").build();
		Departamento depaFisica = Departamento.builder().nombre("Fisica").build();
		Departamento depaIngles = Departamento.builder().nombre("Ingles").build();

		depaRepo.save(depaMates);
		depaRepo.save(depaFisica);
		depaRepo.save(depaIngles);

		// 2. CREACIÓN DE ESPECIALIDADES
		Especialidad especiMates = Especialidad.builder().nombre("Matematicas").build();
		Especialidad especiFisica = Especialidad.builder().nombre("Fisica").build();
		Especialidad especiIngles = Especialidad.builder().nombre("Ingles").build();
		Especialidad especiProgramacion = Especialidad.builder().nombre("Programacion").build();

		especiRepo.save(especiMates);
		especiRepo.save(especiFisica);
		especiRepo.save(especiIngles);
		especiRepo.save(especiProgramacion);

		// 3. CREACIÓN DE PROFESOR (DNI de 15 caracteres para cumplir @Size)
		Profesor defecto = Profesor.builder()
				.dni("11111111Y")
				.nombre("Jose Antonio")
				.primerApellido("Tapia")
				.segundoApellido("Lopez")
				.username("user@user.com")
				.password(encoder.encode("12345"))
				.email("jose.tapia@salesianos.com")
				.direccion("Calle San Juan Bosco")
				.fechaNacimiento(LocalDate.of(1988, 4, 15)) // Fecha en el pasado válida
				.rol(UsuarioRol.PROFESOR)
				.fotoPerfil("https://i.redd.it/sby4ealaiaoe1.png")
				.build();

		especiProgramacion.addToProfesor(defecto);
		especiIngles.addToProfesor(defecto);
		defecto.addToDepartamento(depaMates);

		repo.save(defecto);

		// 4. CREACIÓN DE ADMINISTRADOR (Con campos obligatorios rellenados)
		Usuario admin = Usuario.builder()
				.dni("11111111A")
				.nombre("Administrador")
				.primerApellido("Del Sistema")
				.username("admin@admin.com")
				.email("admin@admin.com")
				.password(encoder.encode("12345"))
				.direccion("Calle Salesianos, 1")
				.fechaNacimiento(LocalDate.of(1985, 1, 1))
				.rol(UsuarioRol.ADMIN)
				.build();

		repo.save(admin);

		// 5. CREACIÓN DE ALUMNOS (DNI >= 15 caracteres y Nombres >= 4 caracteres)
		Alumno alumnoCarlos = Alumno.builder()
				.dni("11111311G")
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
				.dni("11111311T")
				.nombre("Ana María") // Evita la restricción de tamaño mínimo de 4 caracteres
				.primerApellido("Martínez")
				.segundoApellido("Castro")
				.username("anamaria@alumno.com")
				.password(encoder.encode("12345"))
				.email("anamaria@alumno.com")
				.direccion("Calle Betis, 8")
				.fechaNacimiento(LocalDate.of(2006, 7, 22))
				.rol(UsuarioRol.ALUMNO)
				.fotoPerfil("https://api.dicebear.com/7.x/avataaars/svg?seed=Ana")
				.grupo("1º DAM")
				.asignaturas(List.of("Programacion", "Ingles", "Entornos de Desarrollo"))
				.build();
		repo.save(alumnoAna);

		Alumno alumnoLucas = Alumno.builder()
				.dni("11111311E")
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

		// 6. CREACIÓN DE EXÁMENES (Futuros e Históricos)
		Examen examenJava = Examen.builder()
				.nombre("Parcial Tema 4: Colecciones")
				.descripcion("Evaluación práctica orientada al uso de Lists, Sets, Maps y ordenación con Listas en Java.")
				.fecha(LocalDateTime.now().plusDays(7).withHour(9).withMinute(0))
				.aula("Aula 202 (Planta 2)")
				.puntuacionMax(10.0)
				.duracionMinutos(120.0)
				.numPlazas(30)
				.asignatura("Programacion")
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
				.asignatura("Ingles")
				.build();
		examenIngles.addToProfesor(defecto);
		examenRepo.save(examenIngles);

		// Examen simulado del mes pasado (Muy útil para probar tus consultas personalizadas del repositorio)
		Examen examenMesPasado = Examen.builder()
				.nombre("Examen Primer Trimestre Java")
				.descripcion("Examen de fundamentos históricos del mes anterior.")
				.fecha(LocalDateTime.now().minusDays(15).withHour(10).withMinute(0))
				.aula("Aula 201")
				.puntuacionMax(10.0)
				.duracionMinutos(90.0)
				.numPlazas(30)
				.asignatura("Programacion")
				.build();
		examenMesPasado.addToProfesor(defecto);
		examenRepo.save(examenMesPasado);

		// 7. ASOCIACIÓN MEDIANTE INSCRIPCIONES (Uso de los Helper Methods de tu entidad)
		
		// Carlos: Se presentó al examen de Java y aprobó con un 8.5
		Inscripcion inscripcionCarlosJava = Inscripcion.builder()
				.calificacion(8.5)
				.estados(new ArrayList<>(List.of(InscripcionEstados.PRESENTADO, InscripcionEstados.APROBADO)))
				.observaciones("Excelente resolución estructurada.")
				.build();
		inscripcionCarlosJava.addToAlumno(alumnoCarlos);
		inscripcionCarlosJava.addToExamen(examenJava);
		inscripcionRepo.save(inscripcionCarlosJava);

		// Ana María: Se presentó al examen de Java pero suspendió con un 4.0
		Inscripcion inscripcionAnaJava = Inscripcion.builder()
				.calificacion(4.0)
				.estados(new ArrayList<>(List.of(InscripcionEstados.PRESENTADO, InscripcionEstados.SUSPENDIDO)))
				.observaciones("Falta profundizar en el uso de mapas complejos.")
				.build();
		inscripcionAnaJava.addToAlumno(alumnoAna);
		inscripcionAnaJava.addToExamen(examenJava);
		inscripcionRepo.save(inscripcionAnaJava);

		// Lucas: Solo está Inscrito al examen de inglés (no tiene nota aún)
		Inscripcion inscripcionLucasIngles = Inscripcion.builder()
				.estados(new ArrayList<>(List.of(InscripcionEstados.INSCRITO)))
				.observaciones("Inscripción por asignación ordinaria.")
				.build();
		inscripcionLucasIngles.addToAlumno(alumnoLucas);
		inscripcionLucasIngles.addToExamen(examenIngles);
		inscripcionRepo.save(inscripcionLucasIngles);

		// Carlos: Estuvo inscrito en el examen de hace 15 días y se presentó
		Inscripcion inscripcionCarlosPasada = Inscripcion.builder()
				.calificacion(7.2)
				.estados(new ArrayList<>(List.of(InscripcionEstados.PRESENTADO, InscripcionEstados.APROBADO)))
				.observaciones("Examen guardado en el histórico mensual.")
				.build();
		inscripcionCarlosPasada.addToAlumno(alumnoCarlos);
		inscripcionCarlosPasada.addToExamen(examenMesPasado);
		inscripcionRepo.save(inscripcionCarlosPasada);
	}
}