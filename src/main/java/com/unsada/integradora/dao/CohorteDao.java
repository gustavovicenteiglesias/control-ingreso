package com.unsada.integradora.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.Cohorte;
@Transactional
public interface CohorteDao extends CrudRepository<Cohorte, Integer> {
	Iterable<Cohorte> findByActividad(Integer id_actividad );
}
