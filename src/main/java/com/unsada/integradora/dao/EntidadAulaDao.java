package com.unsada.integradora.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.EntidadAula;
@Transactional
public interface EntidadAulaDao extends CrudRepository<EntidadAula, Integer> {

}
