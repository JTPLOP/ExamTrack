package com.salesianos.dam.examtrack.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
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
@Getter @Setter @EqualsAndHashCode @ToString

public class Profesor extends Usuario {
    

    private String especialidad;


    @OneToMany (mappedBy = "profesor", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List <Examen> examen = new ArrayList<>();


    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "id_departamento",
        foreignKey = @ForeignKey (name = "fk_departamento_profesor"))
    private Departamento departamento;

    /*Metodos helpers para la asociacion bidireccional */
    public void addToDepartamento (Departamento departamento) {

        this.departamento = departamento;
        departamento.getListaProfesores().add(this);
    }

    public void removeToProfesor (Profesor profesor) {
        departamento.getListaProfesores().remove(this);
        this.departamento = null;
    }


    @Override
    public void depurarDatos() {
        super.depurarDatos();
        System.out.println("PROFESOR\n===============");
        System.out.println("Especialidad: "+this.especialidad);
        System.out.println("Examenes: "+this.examen);
    }

}
