package com.unsada.integradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.SesionPresencialDao;
import com.unsada.integradora.model.SesionPresencial;
@Service
public class SesionPresencialServiceImpl implements SesionPresencialDao {
	@Autowired
	SesionPresencialDao	sesionPresencialDao;
	@Override
	public <S extends SesionPresencial> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SesionPresencial> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SesionPresencial> findById(Integer id) {
		// TODO Auto-generated method stub
		return sesionPresencialDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return sesionPresencialDao.existsById(id);
	}

	@Override
	public Iterable<SesionPresencial> findAll() {
		// TODO Auto-generated method stub
		return sesionPresencialDao.findAll();
	}

	@Override
	public Iterable<SesionPresencial> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return sesionPresencialDao.findAllById(ids);
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
	public void delete(SesionPresencial entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends SesionPresencial> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<SesionPresencial> session_persona(int id_persona) {
		// TODO Auto-generated method stub
		return sesionPresencialDao.session_persona(id_persona);
	}

}
