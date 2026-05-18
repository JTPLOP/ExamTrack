package com.salesianos.dam.examtrack.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;


@Embeddable

public class InscripcionPK implements Serializable{

    private static final long serialVersionUID = 1L;

    private String dni;
    private long idExamen;

    
}