package com.salesianos.dam.examtrack.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
    private UsuarioRol rol;
    private String password;
    private String fotoPerfil;


    public Collection<? extends SimpleGrantedAuthority> getAuthorities () {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

}
 