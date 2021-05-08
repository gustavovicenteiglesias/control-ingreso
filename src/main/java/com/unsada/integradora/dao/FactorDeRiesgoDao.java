package com.unsada.integradora.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.FactorDeRiesgo;
@Transactional
public interface FactorDeRiesgoDao extends CrudRepository<FactorDeRiesgo, Integer> {

}
