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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Column(nullable = false, name = "especialidad_id") 
    private Long idEspecialidad;
    
    @NotBlank(message = "El nombre de la especialidad es obligatorio")
    @Size(min = 4, max = 100)
    @Column(nullable = true, length = 100)
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
