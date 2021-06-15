package com.unsada.integradora.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.CohorteHorarioDao;
import com.unsada.integradora.model.entity.CohorteHorario;
@Service
public class CohorteHorarioServiceImpl implements CohorteHorarioDao {
	@Autowired
	CohorteHorarioDao cohorteHorarioDao;
	@Override
	public <S extends CohorteHorario> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends CohorteHorario> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CohorteHorario> findById(Integer id) {
		// TODO Auto-generated method stub
		return cohorteHorarioDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return cohorteHorarioDao.existsById(id);
	}

	@Override
	public Iterable<CohorteHorario> findAll() {
		// TODO Auto-generated method stub
		return cohorteHorarioDao.findAll();
	}

	@Override
	public Iterable<CohorteHorario> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return cohorteHorarioDao.findAllById(ids);
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
	public void delete(CohorteHorario entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends CohorteHorario> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
