package com.salesianos.dam.examtrack.service;

import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.salesianos.dam.examtrack.model.Alumno;
import com.salesianos.dam.examtrack.model.Examen;
import com.salesianos.dam.examtrack.model.Inscripcion;
import com.salesianos.dam.examtrack.model.InscripcionEstados;
import com.salesianos.dam.examtrack.model.InscripcionPK;
import com.salesianos.dam.examtrack.repository.InscripcionesRepositorio;
import com.salesianos.dam.examtrack.service.base.ServicioBaseImpl;

@Service
public class InscripcionesServicio extends ServicioBaseImpl<Inscripcion, InscripcionPK, InscripcionesRepositorio> {

    private final InscripcionesRepositorio inscripcionRepo;

    public InscripcionesServicio(InscripcionesRepositorio repositorio, InscripcionesRepositorio inscripcionRepo) {
        super(repositorio);
        this.inscripcionRepo = inscripcionRepo;
    }

    public boolean gestionInscripcion(Alumno alumno, Examen examen) {

        if (inscripcionRepo.filtrarInscripcionPorDNI(alumno.getDni(), examen.getIdExamen()).isEmpty()) {
            InscripcionPK inscripcionPK = new InscripcionPK();
            inscripcionPK.setDni(alumno.getDni());
            inscripcionPK.setIdExamen(examen.getIdExamen());

            Inscripcion nuevaInscripcion = new Inscripcion();
            nuevaInscripcion.addToExamen(examen);
            nuevaInscripcion.addToAlumno(alumno);

            nuevaInscripcion.getEstados().add(InscripcionEstados.INSCRITO);

            inscripcionRepo.save(nuevaInscripcion);

            System.out.println("Agregado excelente");
            nuevaInscripcion.toString();
            
            return true; 
        }else {
            Inscripcion inscripcion = inscripcionRepo.filtrarInscripcionPorDNI(alumno.getDni(), examen.getIdExamen()).orElseThrow();

            inscripcion.removeToAlumno(alumno);
            inscripcion.removeToExamen(examen);

            inscripcionRepo.delete(inscripcion);

            System.out.println("Borrado Correcto");

            return false;

            /*A mejorar para el futuro podriamos poner que se pueda desasignar un alumno pero que el examen siga registrado y mediante
            un filtro si hay una inscripcion sin alumno pero si con examen que se le asigne */
        }

    }

     public int contarAlumnosInscritos (Long idExamen) {

        return inscripcionRepo.contarAlumnosInscritos(idExamen);
    }

    public Optional <Inscripcion> filtrarInscripcionAlumno (String dni, Long idExamen) {

        return inscripcionRepo.filtrarInscripcion(dni, idExamen);

    }

    public void inscripcionAusencia (Inscripcion inscripcion) {

        /* Valores por Default para cuando no se presenten */
        inscripcion.setCalificacion(0.0);
        inscripcion.setObservaciones("No se presento.");
        inscripcion.getEstados().set(0, InscripcionEstados.AUSENTADO);
        inscripcion.getEstados().add(InscripcionEstados.SUSPENDIDO);

        
    }

    public void inscripcionPresente (Inscripcion inscripcion, Double nota, String observacion, double calificacionMax) {

        inscripcion.setCalificacion(nota);
        inscripcion.setObservaciones(observacion);
        inscripcion.getEstados().set(0, InscripcionEstados.PRESENTADO);

        if (nota > (calificacionMax/2)) {
            inscripcion.getEstados().add(InscripcionEstados.APROBADO);
        }else {
            inscripcion.getEstados().add(InscripcionEstados.SUSPENDIDO);
        }

    }


    public List <Alumno> filtrarAlumnosSinNota () {

        LocalDateTime actualidad = LocalDateTime.now();

        return inscripcionRepo.filtrarAlumnosSinNotas(actualidad);
    }

    public Map<Examen, List<Alumno>> filtrarAlumnosInscritos () {
        
        List <Inscripcion> lista = filtrarTodos();
        
        return lista.stream()
                .collect(Collectors.groupingBy(Inscripcion::getExamen,
                    Collectors.collectingAndThen(Collectors.toList(), list -> list.stream().map(Inscripcion::getAlumno).toList())));

    }

    public double contarAllAlumnos (String dni) {
        LocalDateTime actualidad = LocalDateTime.now();

        return (double)inscripcionRepo.contarAllAlumnos(actualidad, dni);
    }

    public double extraerPorcentajePresentados (String dni) {
        int valor1, valor2;
        double resultado;
        
        InscripcionEstados estado = InscripcionEstados.PRESENTADO;
        LocalDateTime fecha = LocalDateTime.now();

        valor1 = inscripcionRepo.contarAlumnosInscritoMesPasado(fecha, dni);
        valor2 = inscripcionRepo.contarAlumnosPresentadoMesPasado(fecha,dni,estado);

        /*Extraer Porcentaje */
        
        resultado = (valor2/valor1 *100);

        return (double)resultado;
    }
    

}
