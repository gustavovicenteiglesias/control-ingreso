package com.unsada.integradora.service;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.unsada.integradora.model.Persona;

public interface PersonaServiceApi extends CrudRepository<Persona, Integer> {
	Optional<Persona> findByDni (String dni);
	
}
