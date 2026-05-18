package com.salesianos.dam.examtrack.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.salesianos.dam.examtrack.repository.UsuarioRepositorio;

public class UserDetailsServiceImp implements UserDetailsService {

    UsuarioRepositorio repositorio;



    /*Metodo obligatorio al extender, se encarga de buscar un usuario donde esten
    almacenados en base al cammpo (Campo escogido: email) 
    
    Hemos incluido una excepcion que devuelve un "UsernameNotFoundException" en caso de que
    no se halle el usuario.*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repositorio.findFirstByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Error al buscar el usuario"));

    }


}
