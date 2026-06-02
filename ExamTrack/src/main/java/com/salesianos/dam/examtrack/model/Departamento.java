package com.salesianos.dam.examtrack.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class Departamento {
    
    @Id
    @GeneratedValue
    @Column(nullable = false) 
    private Long idDepartamento;

    @Size(min = 4, max = 100)
    @Column(nullable = false, length = 100)
    private String nombre;

    @OneToMany (mappedBy = "departamento", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @ToString.Exclude
    private List <Profesor> listaProfesores = new ArrayList<>();

    
}
