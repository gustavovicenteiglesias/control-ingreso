package com.unsada.integradora.dao;


import org.springframework.data.repository.CrudRepository;

import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.entity.Edificio;
@Transactional
public interface EdificioDao extends CrudRepository<Edificio, Integer> {
	Iterable<Edificio>	findBysede ( int idSede);
}
