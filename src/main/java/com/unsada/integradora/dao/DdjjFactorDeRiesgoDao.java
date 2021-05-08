package com.unsada.integradora.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.DdjjFactorDeRiesgo;
@Transactional
public interface DdjjFactorDeRiesgoDao extends CrudRepository<DdjjFactorDeRiesgo, Integer> {

}
