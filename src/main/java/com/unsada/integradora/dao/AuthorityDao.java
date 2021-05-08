package com.unsada.integradora.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.Authority;
@Transactional
public interface AuthorityDao extends CrudRepository<Authority, Integer> {

}
