package com.unsada.integradora.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.Edificio;
import com.unsada.integradora.model.EntidadAula;
@Transactional
public interface EdificioDao extends CrudRepository<Edificio, Integer> {
	Iterable<Edificio>	findBysede ( int idSede);
	@Modifying
	@Query(value="SELECT e.* FROM edificio e\r\n" + 
			"INNER JOIN entidad_aula a ON a.id_edificio=e.id_edificio\r\n" + 
			"WHERE a.id_aula=?",nativeQuery = true)
	Optional<Edificio> findEdificioByAula (Integer id_aula);
}
