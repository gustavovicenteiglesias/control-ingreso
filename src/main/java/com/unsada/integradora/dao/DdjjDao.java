package com.unsada.integradora.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.Ddjj;
@Transactional
public interface DdjjDao extends CrudRepository<Ddjj, Integer> {

}
