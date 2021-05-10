package com.unsada.integradora.dao;

import org.springframework.data.repository.CrudRepository;

import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.Actividad;
@Transactional
public interface ActividadDao extends CrudRepository<Actividad, Integer> {
	Iterable<Actividad> findByPropuesta (int idpropuesta);
}
