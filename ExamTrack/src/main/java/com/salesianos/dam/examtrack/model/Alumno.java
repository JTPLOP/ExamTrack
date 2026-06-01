package com.salesianos.dam.examtrack.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter @Setter @ToString
public class Alumno extends Usuario {
    
    private String grupo;

    @Builder.Default
    @ElementCollection
    private List <String> asignaturas = new ArrayList<>();


    // Relacion tabla Intermedia
    @OneToMany (mappedBy = "alumno", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Inscripcion> inscripcion = new ArrayList<>();

    
     @Override
     public void depurarDatos() {
         super.depurarDatos();
         System.out.println("Datos Alumno");
         System.out.println("Grupo:"+this.grupo);
         System.out.println("Asignaturas:"+this.asignaturas);


     }
    
}
  