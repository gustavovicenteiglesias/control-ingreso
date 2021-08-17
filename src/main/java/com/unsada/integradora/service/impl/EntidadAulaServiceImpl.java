package com.unsada.integradora.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.unsada.integradora.model.entity.Actividad;
import com.unsada.integradora.model.entity.Cohorte;
import com.unsada.integradora.model.entity.SesionPresencial;
import com.unsada.integradora.service.interfaces.ActividadServiceApi;
import com.unsada.integradora.service.interfaces.AsignarAulaInterface;
import com.unsada.integradora.service.interfaces.EntidadAulaServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.EntidadAulaDao;
import com.unsada.integradora.model.entity.EntidadAula;

import javax.swing.text.html.Option;

@Service
public class EntidadAulaServiceImpl implements EntidadAulaServiceApi {
	@Autowired
	EntidadAulaDao	entidadAulaDao;

	@Autowired
	ActividadServiceApi actividadService;
	@Override
	public Iterable<EntidadAula> findByEdificio(int idEdificio) {
		return null;
	}


	@Override
	public Optional<EntidadAula> findAulaSesion(Integer sesion_presencial) {
		return Optional.empty();
	}

	@Override
	public <S extends EntidadAula> S save(S entity) {
		return entidadAulaDao.save(entity);
	}

	@Override
	public <S extends EntidadAula> Iterable<S> saveAll(Iterable<S> entities) {
		return null;
	}

	@Override
	public Optional<EntidadAula> findById(Integer integer) {
		return Optional.empty();
	}

	@Override
	public boolean existsById(Integer integer) {
		return false;
	}

	@Override
	public Iterable<EntidadAula> findAll() {
		return null;
	}

	@Override
	public Iterable<EntidadAula> findAllById(Iterable<Integer> integers) {
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
	public void delete(EntidadAula entity) {

	}

	@Override
	public void deleteAll(Iterable<? extends EntidadAula> entities) {

	}

	@Override
	public void deleteAll() {

	}


}
