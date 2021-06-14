package com.unsada.integradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.CohorteDao;
import com.unsada.integradora.model.entity.Cohorte;
@Service
public class CohorteServiceImpl implements CohorteDao {
	@Autowired
	CohorteDao cohorteDao;
	@Override
	public <S extends Cohorte> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Cohorte> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Cohorte> findById(Integer id) {
		// TODO Auto-generated method stub
		return cohorteDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return cohorteDao.existsById(id);
	}

	@Override
	public Iterable<Cohorte> findAll() {
		// TODO Auto-generated method stub
		return cohorteDao.findAll();
	}

	@Override
	public Iterable<Cohorte> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return cohorteDao.findAllById(ids);
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
	public void delete(Cohorte entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Cohorte> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<Cohorte> findByActivity(Integer id_actividad) {
		// TODO Auto-generated method stub
		return cohorteDao.findByActivity(id_actividad);
	}

	

}
