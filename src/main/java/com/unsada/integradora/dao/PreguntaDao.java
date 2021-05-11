package com.unsada.integradora.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.Pregunta;
@Transactional
public interface PreguntaDao extends CrudRepository<Pregunta, Integer> {

}
