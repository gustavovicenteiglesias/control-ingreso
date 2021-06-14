package com.unsada.integradora.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.entity.Dependencia;
@Transactional
public interface DependenciaDao extends CrudRepository<Dependencia, Integer> {

}
