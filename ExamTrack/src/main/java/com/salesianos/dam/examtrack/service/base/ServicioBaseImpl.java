package com.salesianos.dam.examtrack.service.base;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import lombok.RequiredArgsConstructor;

/*Metodos genericos utilizados por todas las entidades del programa, extendido con JPARepository para disponer de todos sus metodos
y poder reutilizarlos en el codigo 
*/

@RequiredArgsConstructor
public abstract class ServicioBaseImpl <T, ID, R extends JpaRepository<T, ID>> implements IServicioBase<T, ID> {
    
    private final R repositorio;

    /*Metodos Cruds del sistema */

    @Override
    public T agregar(T t) {
        return repositorio.save(t);
    }

    @Override
    public List<T> filtrarTodos() {
        return repositorio.findAll();
    }

    @Override
    public Optional<T> filtrarPorId(ID id) {
        return repositorio.findById(id);
    }

    @Override
    public T modificar(T t) {
        return repositorio.save(t);
    }

    @Override
    public void eliminar(T t) {
        repositorio.delete(t);        
    }
    
    @Override
    public void eliminarPorId(ID id) {
        repositorio.deleteById(id);      
    }

    
}
