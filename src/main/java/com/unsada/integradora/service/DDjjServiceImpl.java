package com.unsada.integradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.DdjjDao;
import com.unsada.integradora.model.entity.Ddjj;
@Service
public class DDjjServiceImpl implements DdjjDao {
	@Autowired
	DdjjDao ddjjDao;
	@Override
	public <S extends Ddjj> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Ddjj> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Ddjj> findById(Integer id) {
		// TODO Auto-generated method stub
		return ddjjDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return ddjjDao.existsById(id);
	}

	@Override
	public Iterable<Ddjj> findAll() {
		// TODO Auto-generated method stub
		return ddjjDao.findAll();
	}

	@Override
	public Iterable<Ddjj> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return ddjjDao.findAllById(ids);
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
	public void delete(Ddjj entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Ddjj> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
