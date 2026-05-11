package com.salesianos.dam.examtrack.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
    


    @OneToMany (mappedBy = "profesor" , fetch = FetchType.EAGER)
    private List<Examen> examen = new ArrayList<>();



}
