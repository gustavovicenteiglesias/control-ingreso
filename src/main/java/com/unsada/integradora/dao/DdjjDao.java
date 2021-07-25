package com.unsada.integradora.dao;

import com.unsada.integradora.model.entity.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.entity.Ddjj;

import java.util.Date;
import java.util.Optional;

@Transactional
public interface DdjjDao extends CrudRepository<Ddjj, Integer> {
}
