package com.unsada.integradora.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.EdificioDao;
import com.unsada.integradora.model.entity.Edificio;
@Service
public class EdificioServiceImpl implements EdificioDao {
	@Autowired
	EdificioDao edificioDao;
	@Override
	public <S extends Edificio> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Edificio> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Edificio> findById(Integer id) {
		// TODO Auto-generated method stub
		return edificioDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return edificioDao.existsById(id);
	}

	@Override
	public Iterable<Edificio> findAll() {
		// TODO Auto-generated method stub
		return edificioDao.findAll();
	}

	@Override
	public Iterable<Edificio> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return edificioDao.findAllById(ids);
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
	public void delete(Edificio entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Edificio> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterable<Edificio> findBysede(int sede) {
		// TODO Auto-generated method stub
		return edificioDao.findBysede(sede);
	}

	@Override
	public Optional<Edificio> findEdificioByAula(Integer id_aula) {
		// TODO Auto-generated method stub
		return edificioDao.findEdificioByAula(id_aula);
	}

}
