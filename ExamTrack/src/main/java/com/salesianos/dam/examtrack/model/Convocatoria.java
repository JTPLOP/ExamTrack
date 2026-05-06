package com.salesianos.dam.examtrack.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
