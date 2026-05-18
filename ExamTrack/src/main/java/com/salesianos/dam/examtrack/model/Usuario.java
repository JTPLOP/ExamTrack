package com.salesianos.dam.examtrack.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Usuario  implements UserDetails{
    
    @Id
    private String dni;
	
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String username; // sera el mismo que email
    private String email;
    private String direccion;
    private LocalDate fechaNacimiento;
    private UsuarioRol rol;
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

    public Collection<? extends SimpleGrantedAuthority> getAuthorities () {
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

}
 