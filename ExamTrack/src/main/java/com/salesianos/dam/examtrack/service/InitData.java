package com.salesianos.dam.examtrack.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.salesianos.dam.examtrack.model.Alumno;
import com.salesianos.dam.examtrack.model.Departamento;
import com.salesianos.dam.examtrack.model.Especialidad;
import com.salesianos.dam.examtrack.model.Profesor;
import com.salesianos.dam.examtrack.model.UsuarioRol;
import com.salesianos.dam.examtrack.repository.DepartamentoRepositorio;
import com.salesianos.dam.examtrack.repository.EspecialidadRepositorio;
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

	@PostConstruct
	public void init() {

		// 1. CREACIÓN DE 10 DEPARTAMENTOS GENÉRICOS
		Departamento depaInformatica = Departamento.builder().nombre("Informática y Comunicaciones").build();
		Departamento depaMates = Departamento.builder().nombre("Matemáticas").build();
		Departamento depaFisica = Departamento.builder().nombre("Física y Química").build();
		Departamento depaIngles = Departamento.builder().nombre("Inglés").build();
		Departamento depaLengua = Departamento.builder().nombre("Lengua Castellana").build();
		Departamento depaHistoria = Departamento.builder().nombre("Geografía e Historia").build();
		Departamento depaBiologia = Departamento.builder().nombre("Biología y Geología").build();
		Departamento depaFilosofia = Departamento.builder().nombre("Filosofía").build();
		Departamento depaEconomia = Departamento.builder().nombre("Economía").build();
		Departamento depaDibujo = Departamento.builder().nombre("Dibujo").build();

		depaRepo.save(depaInformatica);
		depaRepo.save(depaMates);
		depaRepo.save(depaFisica);
		depaRepo.save(depaIngles);
		depaRepo.save(depaLengua);
		depaRepo.save(depaHistoria);
		depaRepo.save(depaBiologia);
		depaRepo.save(depaFilosofia);
		depaRepo.save(depaEconomia);
		depaRepo.save(depaDibujo);

		// 2. CREACIÓN DE 10 ESPECIALIDADES GENÉRICAS
		Especialidad especiProgramacion = Especialidad.builder().nombre("Programación").build();
		Especialidad especiBBDD = Especialidad.builder().nombre("Bases de Datos").build();
		Especialidad especiSistemas = Especialidad.builder().nombre("Sistemas Informáticos").build();
		Especialidad especiRedes = Especialidad.builder().nombre("Redes Locales").build();
		Especialidad especiInterfaces = Especialidad.builder().nombre("Desarrollo de Interfaces").build();
		Especialidad especiInglesTecnico = Especialidad.builder().nombre("Inglés Técnico").build();
		Especialidad especiAlgebra = Especialidad.builder().nombre("Álgebra Lineal").build();
		Especialidad especiHardware = Especialidad.builder().nombre("Hardware").build();
		Especialidad especiSeguridad = Especialidad.builder().nombre("Seguridad Informática").build();
		Especialidad especiEmprendimiento = Especialidad.builder().nombre("Empresa e Iniciativa Emprendedora").build();

		especiRepo.save(especiProgramacion);
		especiRepo.save(especiBBDD);
		especiRepo.save(especiSistemas);
		especiRepo.save(especiRedes);
		especiRepo.save(especiInterfaces);
		especiRepo.save(especiInglesTecnico);
		especiRepo.save(especiAlgebra);
		especiRepo.save(especiHardware);
		especiRepo.save(especiSeguridad);
		especiRepo.save(especiEmprendimiento);

		// 3. CREACIÓN DEL PROFESOR (Manuel Fernández López)
		Profesor manuel = Profesor.builder()
				.dni("22222222B")
				.nombre("Manuel")
				.primerApellido("Fernández")
				.segundoApellido("López")
				.username("user@user.com")
				.password(encoder.encode("12345"))
				.email("user@user.com")
				.direccion("Avenida de la Constitución, 15")
				.fechaNacimiento(LocalDate.of(1982, 6, 20))
				.rol(UsuarioRol.PROFESOR)
				.fotoPerfil("https://randomuser.me/api/portraits/men/32.jpg")
				.build();

		especiProgramacion.addToProfesor(manuel);
		especiBBDD.addToProfesor(manuel);
		manuel.addToDepartamento(depaInformatica);

		repo.save(manuel);

		// 4. CREACIÓN DEL ADMINISTRADOR
		Profesor admin = Profesor.builder()
				.dni("11111111A")
				.nombre("Admin")
				.primerApellido("Admin")
				.segundoApellido("")
				.username("admin@admin.com")
				.email("admin@admin.com")
				.password(encoder.encode("12345"))
				.direccion("Calle Salesianos, 1")
				.fechaNacimiento(LocalDate.of(1985, 1, 1))
				.rol(UsuarioRol.ADMIN)
				.fotoPerfil("https://randomuser.me/api/portraits/men/75.jpg")
				.build();
        
        // Se añade el administrador al departamento de Informática como solicitaste
        admin.addToDepartamento(depaInformatica);
		repo.save(admin);

		// 5. CREACIÓN DE 10 ALUMNOS (Con datos y fotos reales)
		Alumno alumno1 = Alumno.builder()
				.dni("33333333C")
				.nombre("Carlos")
				.primerApellido("Gómez")
				.segundoApellido("Sánchez")
				.username("carlos.gomez@alumno.com")
				.password(encoder.encode("12345"))
				.email("carlos.gomez@alumno.com")
				.direccion("Calle Sierpes, 45")
				.fechaNacimiento(LocalDate.of(2005, 3, 15))
				.rol(UsuarioRol.ALUMNO)
				.fotoPerfil("https://randomuser.me/api/portraits/men/10.jpg")
				.grupo("1º DAM")
				.asignaturas(List.of("Programación", "Bases de Datos", "Sistemas Informáticos"))
				.build();
		repo.save(alumno1);

		Alumno alumno2 = Alumno.builder()
				.dni("44444444D")
				.nombre("Ana María")
				.primerApellido("Martínez")
				.segundoApellido("Castro")
				.username("ana.martinez@alumno.com")
				.password(encoder.encode("12345"))
				.email("ana.martinez@alumno.com")
				.direccion("Calle Betis, 8")
				.fechaNacimiento(LocalDate.of(2006, 7, 22))
				.rol(UsuarioRol.ALUMNO)
				.fotoPerfil("https://randomuser.me/api/portraits/women/11.jpg")
				.grupo("1º DAM")
				.asignaturas(List.of("Programación", "Entornos de Desarrollo"))
				.build();
		repo.save(alumno2);

		Alumno alumno3 = Alumno.builder()
				.dni("55555555E")
				.nombre("Lucas")
				.primerApellido("Fernández")
				.segundoApellido("Marín")
				.username("lucas.fernandez@alumno.com")
				.password(encoder.encode("12345"))
				.email("lucas.fernandez@alumno.com")
				.direccion("Calle Asunción, 19")
				.fechaNacimiento(LocalDate.of(2004, 12, 5))
				.rol(UsuarioRol.ALUMNO)
				.fotoPerfil("https://randomuser.me/api/portraits/men/12.jpg")
				.grupo("2º DAM")
				.asignaturas(List.of("Desarrollo de Interfaces", "Acceso a Datos"))
				.build();
		repo.save(alumno3);

		Alumno alumno4 = Alumno.builder()
				.dni("66666666F")
				.nombre("Laura")
				.primerApellido("Pérez")
				.segundoApellido("Ruiz")
				.username("laura.perez@alumno.com")
				.password(encoder.encode("12345"))
				.email("laura.perez@alumno.com")
				.direccion("Plaza Nueva, 2")
				.fechaNacimiento(LocalDate.of(2005, 8, 10))
				.rol(UsuarioRol.ALUMNO)
				.fotoPerfil("https://randomuser.me/api/portraits/women/13.jpg")
				.grupo("1º DAM")
				.asignaturas(List.of("Bases de Datos", "Lenguaje de Marcas"))
				.build();
		repo.save(alumno4);

		Alumno alumno5 = Alumno.builder()
				.dni("77777777G")
				.nombre("David")
				.primerApellido("García")
				.segundoApellido("López")
				.username("david.garcia@alumno.com")
				.password(encoder.encode("12345"))
				.email("david.garcia@alumno.com")
				.direccion("Calle Feria, 50")
				.fechaNacimiento(LocalDate.of(2003, 11, 25))
				.rol(UsuarioRol.ALUMNO)
				.fotoPerfil("https://randomuser.me/api/portraits/men/14.jpg")
				.grupo("2º DAM")
				.asignaturas(List.of("Sistemas de Gestión Empresarial", "Programación Multimedia"))
				.build();
		repo.save(alumno5);

		Alumno alumno6 = Alumno.builder()
				.dni("88888888H")
				.nombre("Elena")
				.primerApellido("Navarro")
				.segundoApellido("Gómez")
				.username("elena.navarro@alumno.com")
				.password(encoder.encode("12345"))
				.email("elena.navarro@alumno.com")
				.direccion("Avenida de la Palmera, 33")
				.fechaNacimiento(LocalDate.of(2006, 2, 14))
				.rol(UsuarioRol.ALUMNO)
				.fotoPerfil("https://randomuser.me/api/portraits/women/15.jpg")
				.grupo("1º DAM")
				.asignaturas(List.of("Programación", "Sistemas Informáticos"))
				.build();
		repo.save(alumno6);

		Alumno alumno7 = Alumno.builder()
				.dni("99999999J")
				.nombre("Javier")
				.primerApellido("Torres")
				.segundoApellido("Díaz")
				.username("javier.torres@alumno.com")
				.password(encoder.encode("12345"))
				.email("javier.torres@alumno.com")
				.direccion("Calle Recaredo, 11")
				.fechaNacimiento(LocalDate.of(2004, 5, 30))
				.rol(UsuarioRol.ALUMNO)
				.fotoPerfil("https://randomuser.me/api/portraits/men/16.jpg")
				.grupo("2º DAM")
				.asignaturas(List.of("Acceso a Datos", "Desarrollo de Interfaces"))
				.build();
		repo.save(alumno7);

		Alumno alumno8 = Alumno.builder()
				.dni("10101010K")
				.nombre("Carmen")
				.primerApellido("Gil")
				.segundoApellido("Vargas")
				.username("carmen.gil@alumno.com")
				.password(encoder.encode("12345"))
				.email("carmen.gil@alumno.com")
				.direccion("Barrio de Triana, 7")
				.fechaNacimiento(LocalDate.of(2005, 9, 18))
				.rol(UsuarioRol.ALUMNO)
				.fotoPerfil("https://randomuser.me/api/portraits/women/17.jpg")
				.grupo("1º DAM")
				.asignaturas(List.of("Lenguaje de Marcas", "Entornos de Desarrollo"))
				.build();
		repo.save(alumno8);

		Alumno alumno9 = Alumno.builder()
				.dni("12121212L")
				.nombre("Alejandro")
				.primerApellido("Morales")
				.segundoApellido("Ortiz")
				.username("alejandro.morales@alumno.com")
				.password(encoder.encode("12345"))
				.email("alejandro.morales@alumno.com")
				.direccion("Ronda de Capuchinos, 22")
				.fechaNacimiento(LocalDate.of(2003, 1, 10))
				.rol(UsuarioRol.ALUMNO)
				.fotoPerfil("https://randomuser.me/api/portraits/men/18.jpg")
				.grupo("2º DAM")
				.asignaturas(List.of("Empresa e Iniciativa Emprendedora", "Programación Multimedia"))
				.build();
		repo.save(alumno9);

		Alumno alumno10 = Alumno.builder()
				.dni("13131313M")
				.nombre("Sofía")
				.primerApellido("Delgado")
				.segundoApellido("Romero")
				.username("sofia.delgado@alumno.com")
				.password(encoder.encode("12345"))
				.email("sofia.delgado@alumno.com")
				.direccion("Calle San Luis, 88")
				.fechaNacimiento(LocalDate.of(2006, 10, 4))
				.rol(UsuarioRol.ALUMNO)
				.fotoPerfil("https://randomuser.me/api/portraits/women/19.jpg")
				.grupo("1º DAM")
				.asignaturas(List.of("Programación", "Bases de Datos"))
				.build();
		repo.save(alumno10);
	}
}