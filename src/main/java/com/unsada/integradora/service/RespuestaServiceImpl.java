package com.unsada.integradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.RespuestaDao;
import com.unsada.integradora.model.entity.Respuesta;
@Service
public class RespuestaServiceImpl implements RespuestaDao {
	@Autowired
	RespuestaDao respuestaDao;
	@Override
	public <S extends Respuesta> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Respuesta> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Respuesta> findById(Integer id) {
		// TODO Auto-generated method stub
		return respuestaDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return respuestaDao.existsById(id);
	}

	@Override
	public Iterable<Respuesta> findAll() {
		// TODO Auto-generated method stub
		return respuestaDao.findAll();
	}

	@Override
	public Iterable<Respuesta> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return respuestaDao.findAllById(ids);
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
	public void delete(Respuesta entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Respuesta> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
