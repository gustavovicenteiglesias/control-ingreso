package com.unsada.integradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.SolicitudPKDao;
import com.unsada.integradora.model.SolicitudPK;
@Service
public class SolicitudPKServiceImpl implements SolicitudPKDao {
	@Autowired
	SolicitudPKDao solicitudPKDao;
	@Override
	public <S extends SolicitudPK> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SolicitudPK> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<SolicitudPK> findById(Integer id) {
		// TODO Auto-generated method stub
		return solicitudPKDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return solicitudPKDao.existsById(id);
	}

	@Override
	public Iterable<SolicitudPK> findAll() {
		// TODO Auto-generated method stub
		return solicitudPKDao.findAll();
	}

	@Override
	public Iterable<SolicitudPK> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return solicitudPKDao.findAllById(ids);
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
	public void delete(SolicitudPK entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends SolicitudPK> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
