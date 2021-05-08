package com.unsada.integradora.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.SesionPresencial;
@Transactional
public interface SesionPresencialDao extends CrudRepository<SesionPresencial, Integer> {

}
