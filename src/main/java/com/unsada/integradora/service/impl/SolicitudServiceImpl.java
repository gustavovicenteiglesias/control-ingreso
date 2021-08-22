package com.unsada.integradora.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.unsada.integradora.model.entity.*;
import com.unsada.integradora.service.interfaces.SolicitudServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.SolicitudDao;

@Service
public class SolicitudServiceImpl implements SolicitudServiceApi {
	@Autowired
	SolicitudDao solicitudDao;



	@Override
	public Optional<Solicitud> findByQr(String qr_acceso) {
		return Optional.empty();
	}

	@Override
	public int countBySesionPresencialAndFechaCarga(SesionPresencial sesion, Date fecha) {
		return 0;
	}

	@Override
	public List<Integer> findSolicitudesPorPersona(int idpersona) {
		return null;
	}

	@Override
	public List<Solicitud> findSolicitudesInRange(Date fechainicio, Date fechafin) {
		return null;
	}

	@Override
	public <S extends Solicitud> S save(S entity) {
		return null;
	}

	@Override
	public <S extends Solicitud> Iterable<S> saveAll(Iterable<S> entities) {
		return null;
	}

	@Override
	public Optional<Solicitud> findById(Integer integer) {
		return Optional.empty();
	}

	@Override
	public boolean existsById(Integer integer) {
		return false;
	}

	@Override
	public Iterable<Solicitud> findAll() {
		return null;
	}

	@Override
	public Iterable<Solicitud> findAllById(Iterable<Integer> integers) {
		return null;
	}

	@Override
	public long count() {
		return 0;
	}

	@Override
	public void deleteById(Integer integer) {

	}

	@Override
	public void delete(Solicitud entity) {

	}

	@Override
	public void deleteAll(Iterable<? extends Solicitud> entities) {

	}


	@Override
	public void deleteAll() {

	}


}
