package com.unsada.integradora.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.SolicitudPK;
@Transactional
public interface SolicitudPKDao extends CrudRepository<SolicitudPK, Integer> {

}
