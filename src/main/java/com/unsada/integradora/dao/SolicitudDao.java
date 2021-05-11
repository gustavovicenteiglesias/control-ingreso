package com.unsada.integradora.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.Solicitud;

@Transactional
public interface SolicitudDao extends CrudRepository<Solicitud, Integer> {
	@Query(value= "SELECT * FROM `solicitud` WHERE qr_acceso=?", nativeQuery = true)
	  Optional<Solicitud> findByQr (String qr_acceso);
}
