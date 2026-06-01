package com.salesianos.dam.examtrack.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Examen {

    @Id
    @GeneratedValue
    @Column(nullable = false) 
    private Long idExamen;

    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private LocalDateTime fecha;
    private String aula;
    private double puntuacionMax;
    private double duracionMinutos;
    private int numPlazas;
    private String asignatura;
    
    // Relacion Tabla intermedia
    @OneToMany (mappedBy = "examen", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Inscripcion> inscripcion = new ArrayList<>();


    // Relacion con Profesor
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "id_profesor",
        foreignKey = @ForeignKey (name = "fk_examen_profesor"))
    private Profesor profesor;

    /*Metodos helpers para la asociacion bidireccional */

    public void addToProfesor (Profesor profesor) {

        this.profesor = profesor;
        profesor.getExamen().add(this);
    }

    public void removeToProfesor (Profesor profesor) {
        profesor.getExamen().remove(this);
        this.profesor = null;
    }

}
