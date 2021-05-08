package com.unsada.integradora.service;

import org.springframework.data.repository.CrudRepository;

import com.unsada.integradora.model.Persona;

public interface PersonaServiceApi extends CrudRepository<Persona, Integer> {

}
