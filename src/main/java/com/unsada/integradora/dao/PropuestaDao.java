package com.unsada.integradora.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.entity.Propuesta;
@Transactional
public interface PropuestaDao extends CrudRepository<Propuesta, Integer>{
	Iterable<Propuesta> findByDependencia (int idDependencia);

}
