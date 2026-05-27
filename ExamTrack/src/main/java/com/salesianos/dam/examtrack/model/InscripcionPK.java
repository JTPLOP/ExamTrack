package com.salesianos.dam.examtrack.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor
@Embeddable
public class InscripcionPK implements Serializable{

    private static final long serialVersionUID = 1L;

    private String dni;
    private long idExamen;

    
}