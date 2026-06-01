package com.salesianos.dam.examtrack.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Size;
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
public class Usuario implements UserDetails {

    @Id
    @Column(nullable = false, unique = true)
    private String dni;

    @Size(min = 4, max = 75)
    @Column(nullable = false, length = 75)
    private String nombre;

    @Size(min = 4, max = 75)
    @Column(nullable = false, length = 75)
    private String primerApellido;

    @Size(min = 4, max = 75)
    @Column(nullable = true, length = 75)
    private String segundoApellido;

    @Column(unique = true)
    private String username; // sera el mismo que email

    @Size(min = 15, max = 355)
    @Column(nullable = false, length = 355, unique = true)
    private String email;

    @Size(min = 4, max = 100)
    @Column(nullable = true, length = 100)
    private String direccion;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private UsuarioRol rol;

    private String password;

    @ColumnDefault("https://imgs.search.brave.com/9I0CGTppo0pN5og4ky1_K-gQciWInSwyyDoKxhCoxGA/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9iLnRo/dW1icy5yZWRkaXRt/ZWRpYS5jb20vY1ho/bHhHTWNQVklmOGxz/V1NGQmpXeUhka3NI/ejV1enNCc1k4anpm/S1ZnZy5qcGc")
    private String fotoPerfil;

    public void depurarDatos() {
        System.out.println("Usuario Creado:");
        System.out.println("DNI:" + this.dni);
        System.out.println("nombre:" + this.nombre);
        System.out.println("Username: " + this.username);
        System.out.println("Primer apellido:" + this.primerApellido);
        System.out.println("segundo Apellido:" + this.segundoApellido);
        System.out.println("email:" + this.email);
        System.out.println("direccion:" + this.direccion);
        System.out.println("Fecha Nacimiento" + this.fechaNacimiento);
        System.out.println("Rol:" + this.rol);
        System.out.println("Pass:" + this.password);
        System.out.println("URL Foto:" + this.fotoPerfil);
    }

    public Collection<? extends SimpleGrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /* AJUSTES DE LA CREACION */

    public String creadorUsername() {

        /*
         * Este metodo tendra como finalidad crear un username a partir de un email, la
         * idea
         * es partir el string por la mitad (de momento por hacer pero tengo este metodo
         * para pruebas.)
         */

        this.username = this.email;

        return this.username;
    }

}
