package com.unsada.integradora.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.unsada.integradora.model.SesionPresencial;
import com.unsada.integradora.model.Solicitud;

public interface SolicitudServiceApi extends CrudRepository<Solicitud, Integer> {
	@Query(value= "SELECT * FROM `solicitud` WHERE qr_acceso=?", nativeQuery = true)
	  Optional<Solicitud> findByQr (String qr_acceso);
	public int countBySesionPresencialAndFechaCarga(SesionPresencial sesion, Date fecha);
}
