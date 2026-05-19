package com.salesianos.dam.examtrack.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.salesianos.dam.examtrack.model.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {

    	Optional<Usuario>  findFirstByUsername(String username);

}
