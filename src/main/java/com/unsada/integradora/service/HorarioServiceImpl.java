package com.unsada.integradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.HorarioDao;
import com.unsada.integradora.model.Horario;
@Service
public class HorarioServiceImpl implements HorarioDao {
	@Autowired
	HorarioDao horarioDao;
	@Override
	public <S extends Horario> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Horario> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Horario> findById(Integer id) {
		// TODO Auto-generated method stub
		return horarioDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return horarioDao.existsById(id);
	}

	@Override
	public Iterable<Horario> findAll() {
		// TODO Auto-generated method stub
		return horarioDao.findAll();
	}

	@Override
	public Iterable<Horario> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return horarioDao.findAllById(ids);
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
	public void delete(Horario entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Horario> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
