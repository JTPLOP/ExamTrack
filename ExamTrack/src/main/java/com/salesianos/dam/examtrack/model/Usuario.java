package com.salesianos.dam.examtrack.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Usuario {
    
	@Column (columnDefinition = "unique" , nullable = false)
    private String dni;
	
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String email;
    private String direccion;
    private LocalDate fechaNacimiento;
    private String rol;
    private String password;
    private String fotoPerfil;


}
