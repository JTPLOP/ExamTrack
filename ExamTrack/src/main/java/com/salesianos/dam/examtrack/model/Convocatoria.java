package com.salesianos.dam.examtrack.model;

import java.time.LocalDateTime;

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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Convocatoria {
    
    @Id
	@GeneratedValue
	@Column (nullable = false)
    private Long id;

    private int numAula;
    private LocalDateTime fecha;
    private String estado;
    private double puntuacion;
    
    @Column (columnDefinition = "TEXT")
    private String observaciones;

    @ManyToOne
    @JoinColumn (name="id_convocatoria",
        foreignKey = @ForeignKey (name = "fk_convocatoria_examen"))
    private Examen examen;
 
    /*Metodos Helper */

    public void addToExamen (Examen examen) {
        this.examen = examen;
        examen.getConvocatoria().add(this);
    }

    public void removeToExamen (Examen examen) {
        examen.getConvocatoria().remove(this);
        this.examen = null;
    }

}
