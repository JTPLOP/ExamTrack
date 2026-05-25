package com.salesianos.dam.examtrack.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Especialidad {

    @Id
    @GeneratedValue
    @Column(name = "especialidad_id") 
    private Long idEspecialidad;
    private String nombre;

    @ManyToMany (mappedBy = "especialidades",fetch = FetchType.EAGER)
    @Builder.Default
    private List <Profesor> profesores = new ArrayList<>();

    /*Metodos helpers para la asociacion bidireccional */
    public void addToProfesor (Profesor profesor) {

        this.profesores.add(profesor);
        profesor.getEspecialidades().add(this);
    }

    public void removeToProfesor (Profesor profesor) {
        profesor.getEspecialidades().remove(this);
        this.profesores.remove(profesor);
    }
    
}
