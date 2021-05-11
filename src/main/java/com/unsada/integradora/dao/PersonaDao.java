package com.unsada.integradora.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.Persona;
@Transactional
public interface PersonaDao extends CrudRepository<Persona, Integer> {
	Optional<Persona> findByDni (String dni);
}
