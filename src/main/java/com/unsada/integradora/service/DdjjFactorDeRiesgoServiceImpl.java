package com.unsada.integradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.DdjjFactorDeRiesgoDao;
import com.unsada.integradora.model.entity.DdjjFactorDeRiesgo;
@Service
public class DdjjFactorDeRiesgoServiceImpl implements DdjjFactorDeRiesgoDao {
	@Autowired
	DdjjFactorDeRiesgoDao ddjjFactorDeRiesgoDao;
	@Override
	public <S extends DdjjFactorDeRiesgo> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends DdjjFactorDeRiesgo> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<DdjjFactorDeRiesgo> findById(Integer id) {
		// TODO Auto-generated method stub
		return ddjjFactorDeRiesgoDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return ddjjFactorDeRiesgoDao.existsById(id);
	}

	@Override
	public Iterable<DdjjFactorDeRiesgo> findAll() {
		// TODO Auto-generated method stub
		return ddjjFactorDeRiesgoDao.findAll();
	}

	@Override
	public Iterable<DdjjFactorDeRiesgo> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return ddjjFactorDeRiesgoDao.findAllById(ids);
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
	public void delete(DdjjFactorDeRiesgo entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends DdjjFactorDeRiesgo> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
