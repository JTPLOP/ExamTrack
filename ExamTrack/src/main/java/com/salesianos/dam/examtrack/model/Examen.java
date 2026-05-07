package com.salesianos.dam.examtrack.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType; 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Examen {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;
    private String asignatura;
    private double duracionMinutos;
    private double puntuacionMax;


    @OneToMany (mappedBy = "examen" , fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    List <Convocatoria> convocatoria = new ArrayList<>();

}
