package com.unsada.integradora.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.PersonaDao;
import com.unsada.integradora.model.Persona;
@Service
public class PersonaServiceImpl implements PersonaDao {
	@Autowired
	PersonaDao personaDao;
	@Override
	public <S extends Persona> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Persona> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Persona> findById(Integer id) {
		// TODO Auto-generated method stub
		return personaDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return personaDao.existsById(id);
	}

	@Override
	public Iterable<Persona> findAll() {
		// TODO Auto-generated method stub
		return personaDao.findAll();
	}

	@Override
	public Iterable<Persona> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return personaDao.findAllById(ids);
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
	public void delete(Persona entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Persona> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<Persona> findByDni(String dni) {
		// TODO Auto-generated method stub
		return personaDao.findByDni(dni);
	}

	@Override
	public Iterable<Persona> PersonaSesion(String fechainicio, String fechafin) {
		// TODO Auto-generated method stub
		return personaDao.PersonaSesion(fechainicio, fechafin);
	}

	

}
