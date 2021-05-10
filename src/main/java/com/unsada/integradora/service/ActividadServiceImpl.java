package com.unsada.integradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.ActividadDao;
import com.unsada.integradora.model.Actividad;
@Service
public class ActividadServiceImpl implements ActividadServiceApi {
	@Autowired
	ActividadDao actividadDao;
	@Override
	public <S extends Actividad> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Actividad> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Actividad> findById(Integer id) {
		// TODO Auto-generated method stub
		return actividadDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return actividadDao.existsById(id);
	}

	@Override
	public Iterable<Actividad> findAll() {
		// TODO Auto-generated method stub
		return actividadDao.findAll();
	}

	@Override
	public Iterable<Actividad> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return actividadDao.findAllById(ids);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Actividad entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Actividad> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<Actividad> findByPropuesta(int idpropuesta) {
		// TODO Auto-generated method stub
		return actividadDao.findByPropuesta(idpropuesta);
	}

}
