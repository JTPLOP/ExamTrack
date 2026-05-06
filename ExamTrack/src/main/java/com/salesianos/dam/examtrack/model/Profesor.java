package com.salesianos.dam.examtrack.model;

import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @EqualsAndHashCode @ToString

public class Profesor extends Usuario {
    
    private String departamento;
    private String especialidad;
    
    @ManyToOne
    @JoinColumn (foreignKey = @ForeignKey (name = "fk_Profesor_Examen"))
    private Examen examen;

}
