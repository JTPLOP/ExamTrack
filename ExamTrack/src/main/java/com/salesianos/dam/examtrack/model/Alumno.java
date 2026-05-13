package com.salesianos.dam.examtrack.model;

import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString

public class Alumno extends Usuario {
    
    private String grupo;
    private List <String> asignaturas;

     
}
 