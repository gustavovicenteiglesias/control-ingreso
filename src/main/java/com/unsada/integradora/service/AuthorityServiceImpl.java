package com.unsada.integradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.AuthorityDao;
import com.unsada.integradora.model.entity.Authority;
@Service
public class AuthorityServiceImpl implements AuthorityDao {
	@Autowired
	AuthorityDao authorityDao;
	@Override
	public <S extends Authority> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Authority> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Authority> findById(Integer id) {
		// TODO Auto-generated method stub
		return authorityDao.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return authorityDao.existsById(id);
	}

	@Override
	public Iterable<Authority> findAll() {
		// TODO Auto-generated method stub
		return authorityDao.findAll();
	}

	@Override
	public Iterable<Authority> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return authorityDao.findAllById(ids);
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
	public void delete(Authority entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends Authority> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
