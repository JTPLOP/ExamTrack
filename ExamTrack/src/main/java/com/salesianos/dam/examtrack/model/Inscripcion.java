package com.salesianos.dam.examtrack.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inscripcion {
    
    @EmbeddedId
    private InscripcionPK inscripcionPK = new InscripcionPK();

    @ManyToOne
    @MapsId("idExamen")
    @JoinColumn(name = "id_examen")
    private Examen examen;

    @ManyToOne
    @MapsId ("dni")
    @JoinColumn (name = "dni_Alumno")
    private Alumno alumno;

    private double calificacion;
    private String estado;
    private String observaciones;

}
