package com.unsada.integradora.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.PropuestaDao;
import com.unsada.integradora.model.entity.Propuesta;
@Service
public class PropuestaServiceImpl implements PropuestaDao {
	@Autowired
	PropuestaDao propuestaDao;
	@Override
	public <S extends Propuesta> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Propuesta> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Propuesta> findById(Integer id) {
		// TODO Auto-generated method stub
		return propuestaDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return propuestaDao.existsById(id);
	}

	@Override
	public Iterable<Propuesta> findAll() {
		// TODO Auto-generated method stub
		return propuestaDao.findAll();
	}

	@Override
	public Iterable<Propuesta> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return propuestaDao.findAllById(ids);
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
	public void delete(Propuesta entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Propuesta> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<Propuesta> findByDependencia(int idDependencia) {
		// TODO Auto-generated method stub
		return propuestaDao.findByDependencia(idDependencia);
	}

}
