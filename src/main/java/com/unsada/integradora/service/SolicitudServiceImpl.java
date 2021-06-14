package com.unsada.integradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.SolicitudDao;
import com.unsada.integradora.model.entity.Solicitud;
@Service
public class SolicitudServiceImpl implements SolicitudDao {
	@Autowired
	SolicitudDao solicitudDao;
	@Override
	public <S extends Solicitud> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Solicitud> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Solicitud> findById(Integer id) {
		// TODO Auto-generated method stub
		return solicitudDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return solicitudDao.existsById(id);
	}

	@Override
	public Iterable<Solicitud> findAll() {
		// TODO Auto-generated method stub
		return solicitudDao.findAll();
	}

	@Override
	public Iterable<Solicitud> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return solicitudDao.findAllById(ids);
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
	public void delete(Solicitud entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Solicitud> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<Solicitud> findByQr(String qr_acceso) {
		// TODO Auto-generated method stub
		return solicitudDao.findByQr(qr_acceso);
	}


}
