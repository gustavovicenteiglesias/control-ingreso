package com.unsada.integradora.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.entity.Sede;
@Transactional
public interface SedeDao extends CrudRepository<Sede, Integer> {

}
