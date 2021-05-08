package com.unsada.integradora.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.Horario;
@Transactional
public interface HorarioDao extends CrudRepository<Horario, Integer> {

}
