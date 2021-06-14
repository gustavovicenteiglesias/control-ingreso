package com.unsada.integradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.SedeDao;
import com.unsada.integradora.model.entity.Sede;
@Service
public class SedeServiceImpl implements SedeDao {
	@Autowired
	SedeDao sedeDao;
	@Override
	public <S extends Sede> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Sede> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Sede> findById(Integer id) {
		// TODO Auto-generated method stub
		return sedeDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return sedeDao.existsById(id);
	}

	@Override
	public Iterable<Sede> findAll() {
		// TODO Auto-generated method stub
		return sedeDao.findAll();
	}

	@Override
	public Iterable<Sede> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return sedeDao.findAllById(ids);
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
	public void delete(Sede entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Sede> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
