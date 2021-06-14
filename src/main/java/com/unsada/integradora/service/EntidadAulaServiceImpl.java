package com.unsada.integradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.EntidadAulaDao;
import com.unsada.integradora.model.entity.EntidadAula;
@Service
public class EntidadAulaServiceImpl implements EntidadAulaDao {
	@Autowired
	EntidadAulaDao entidadAulaDao;
	@Override
	public <S extends EntidadAula> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends EntidadAula> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<EntidadAula> findById(Integer id) {
		// TODO Auto-generated method stub
		return entidadAulaDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return entidadAulaDao.existsById(id);
	}

	@Override
	public Iterable<EntidadAula> findAll() {
		// TODO Auto-generated method stub
		return entidadAulaDao.findAll();
	}

	@Override
	public Iterable<EntidadAula> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return entidadAulaDao.findAllById(ids);
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
	public void delete(EntidadAula entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends EntidadAula> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<EntidadAula> findByEdificio(int idEdificio) {
		// TODO Auto-generated method stub
		return entidadAulaDao.findByEdificio(idEdificio);
	}

	@Override
	public Optional<EntidadAula> findAulaSesion(Integer sesion_presencial) {
		// TODO Auto-generated method stub
		return entidadAulaDao.findAulaSesion(sesion_presencial);
	}

}
