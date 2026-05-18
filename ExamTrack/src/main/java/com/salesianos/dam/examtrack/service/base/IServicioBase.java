package com.salesianos.dam.examtrack.service.base;

import java.util.List;
import java.util.Optional;

public interface IServicioBase <T,ID> {

    List<T> filtrarTodos();
	
	Optional<T> filtrarPorId(ID id);
	
	T agregar(T t);
	
	T modificar(T t);
	
	void eliminar(T t);
	
	void eliminarPorId(ID id);
}
