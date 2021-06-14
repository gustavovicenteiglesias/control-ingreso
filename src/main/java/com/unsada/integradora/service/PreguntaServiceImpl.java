package com.unsada.integradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.PreguntaDao;
import com.unsada.integradora.model.entity.Pregunta;
@Service
public class PreguntaServiceImpl implements PreguntaDao {
	@Autowired
	PreguntaDao preguntaDao;
	@Override
	public <S extends Pregunta> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Pregunta> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Pregunta> findById(Integer id) {
		// TODO Auto-generated method stub
		return preguntaDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return preguntaDao.existsById(id);
	}

	@Override
	public Iterable<Pregunta> findAll() {
		// TODO Auto-generated method stub
		return preguntaDao.findAll();
	}

	@Override
	public Iterable<Pregunta> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return preguntaDao.findAllById(ids);
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
	public void delete(Pregunta entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Pregunta> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
