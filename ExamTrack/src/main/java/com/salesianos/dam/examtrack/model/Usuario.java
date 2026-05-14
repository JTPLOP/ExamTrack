package com.salesianos.dam.examtrack.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@ToString
public class Usuario {
    
    @Id
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

    public void depurarDatos() {
        System.out.println("Usuario Creado:");
        System.out.println("DNI:"+this.dni);
        System.out.println("nombre:"+this.nombre);
        System.out.println("Primer apellido:"+this.primerApellido);
        System.out.println("segundo Apellido:"+this.segundoApellido);
        System.out.println("email:"+this.email);
        System.out.println("direccion:"+this.direccion);
        System.out.println("Fecha Nacimiento"+this.fechaNacimiento);
        System.out.println("Rol:"+this.rol);
        System.out.println("Pass:"+this.password);
        System.out.println("URL Foto:"+this.fotoPerfil);

    }



} 
 