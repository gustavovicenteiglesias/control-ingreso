package com.unsada.integradora.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.FactorDeRiesgoDao;
import com.unsada.integradora.model.entity.FactorDeRiesgo;
@Service
public class FactorDeRiesgoServiceImpl implements FactorDeRiesgoDao {
	@Autowired
	FactorDeRiesgoDao factorDeRiesgoDao;
	@Override
	public <S extends FactorDeRiesgo> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends FactorDeRiesgo> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<FactorDeRiesgo> findById(Integer id) {
		// TODO Auto-generated method stub
		return factorDeRiesgoDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return factorDeRiesgoDao.existsById(id);
	}

	@Override
	public Iterable<FactorDeRiesgo> findAll() {
		// TODO Auto-generated method stub
		return factorDeRiesgoDao.findAll();
	}

	@Override
	public Iterable<FactorDeRiesgo> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return factorDeRiesgoDao.findAllById(ids);
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
	public void delete(FactorDeRiesgo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends FactorDeRiesgo> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
