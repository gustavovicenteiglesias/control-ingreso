package com.unsada.integradora.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.entity.Cohorte;

@Transactional
public interface CohorteDao extends CrudRepository<Cohorte, Integer> {
	@Query(value= "SELECT * FROM cohorte WHERE id_actividad=?", nativeQuery = true)
	  Iterable<Cohorte> findByActivity (Integer id_actividad);
}
