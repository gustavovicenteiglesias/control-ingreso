package com.unsada.integradora.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.entity.Pregunta;
@Transactional
public interface PreguntaDao extends CrudRepository<Pregunta, Integer> {

}
