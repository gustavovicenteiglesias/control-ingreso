package com.unsada.integradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.DependenciaDao;
import com.unsada.integradora.model.entity.Dependencia;
@Service
public class DependenciaServiceImpl implements DependenciaDao {
	@Autowired
	DependenciaDao dependenciaDao;
	@Override
	public <S extends Dependencia> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Dependencia> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Dependencia> findById(Integer id) {
		// TODO Auto-generated method stub
		return dependenciaDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return dependenciaDao.existsById(id);
	}

	@Override
	public Iterable<Dependencia> findAll() {
		// TODO Auto-generated method stub
		return dependenciaDao.findAll();
	}

	@Override
	public Iterable<Dependencia> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return dependenciaDao.findAllById(ids);
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
	public void delete(Dependencia entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Dependencia> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
