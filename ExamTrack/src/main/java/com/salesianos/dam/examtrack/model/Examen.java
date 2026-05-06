package com.salesianos.dam.examtrack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Examen {
    
	@Id 
	@GeneratedValue
	@Column (nullable = false)
    private Long id;
    private String nombre;
    
    @Column (columnDefinition = "TEXT")
    private String descripcion;
    private String asignatura;
    private double duracionMinutos;
    private double puntuacionMax;
    
    
    @ManyToOne
    @JoinColumn (foreignKey = @ForeignKey (name = "fk_Examen_convocatoria"))
    private Convocatoria convocatoria;

}
