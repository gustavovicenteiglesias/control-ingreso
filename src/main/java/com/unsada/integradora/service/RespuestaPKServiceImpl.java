package com.unsada.integradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.RespuestaPKDao;
import com.unsada.integradora.model.RespuestaPK;
@Service
public class RespuestaPKServiceImpl implements RespuestaPKDao {
	@Autowired
	RespuestaPKDao respuestaPKDao;
	@Override
	public <S extends RespuestaPK> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends RespuestaPK> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<RespuestaPK> findById(Integer id) {
		// TODO Auto-generated method stub
		return respuestaPKDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return respuestaPKDao.existsById(id);
	}

	@Override
	public Iterable<RespuestaPK> findAll() {
		// TODO Auto-generated method stub
		return respuestaPKDao.findAll();
	}

	@Override
	public Iterable<RespuestaPK> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return respuestaPKDao.findAllById(ids);
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
	public void delete(RespuestaPK entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends RespuestaPK> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
