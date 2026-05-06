package com.salesianos.dam.examtrack.model;

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
public class Examen {
    
    private Long id;
    private String nombre;
    private String descripcion;
    private String asignatura;
    private double duracionMinutos;
    private double puntuacionMax;

}
