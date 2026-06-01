package com.salesianos.dam.examtrack.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Inscripcion {
    
    @EmbeddedId
    private InscripcionPK inscripcionPK = new InscripcionPK();

    @ManyToOne
    @MapsId("idExamen")
    @JoinColumn(name = "id_examen")
    private Examen examen;

    /*Metodos helpers para la asociacion bidireccional */

    public void addToExamen (Examen examen) {
        this.examen = examen;
        examen.getInscripcion().add(this);
    }

    public void removeToExamen (Examen examen) {
        examen.getInscripcion().remove(this);
        this.examen = null;
    }

    @ManyToOne
    @MapsId ("dni")
    @JoinColumn (name = "dni_Alumno")
    private Alumno alumno;

    /*Metodos helpers para la asociacion bidireccional */

    public void addToAlumno (Alumno alumno) {
        this.alumno = alumno;
        alumno.getInscripcion().add(this);
    }

    public void removeToAlumno (Alumno alumno) {
        alumno.getInscripcion().remove(this);
        this.alumno = null;
    }

    private Double calificacion;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private List <InscripcionEstados> estados = new ArrayList<>();
    
    private String observaciones;

    
}
