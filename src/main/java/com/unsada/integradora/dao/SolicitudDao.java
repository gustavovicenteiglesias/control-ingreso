package com.unsada.integradora.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.Solicitud;
@Transactional
public interface SolicitudDao extends CrudRepository<Solicitud, Integer> {

}
