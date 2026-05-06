package com.salesianos.dam.examtrack.model;

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

}
