package com.unsada.integradora.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.Respuesta;
@Transactional
public interface RespuestaDao extends CrudRepository<Respuesta, Integer> {

}
