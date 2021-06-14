package com.unsada.integradora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.Edificio;
@Qualifier("EdificioServiceApi")
public interface EdificioServiceApi extends CrudRepository<Edificio, Integer>{
	@Modifying
	@Transactional
	@Query(value="SELECT * FROM edificio e INNER JOIN sede s ON s.id_sede=e.id_sede WHERE e.id_sede= :sede",nativeQuery = true )
	Iterable<Edificio>	findBysede (@Param("sede") int idSede);
	@Query(value="SELECT e.* FROM edificio e\r\n" + 
			"INNER JOIN entidad_aula a ON a.id_edificio=e.id_edificio\r\n" + 
			"WHERE a.id_aula=?",nativeQuery = true)
	Optional<Edificio> findEdificioByAula (Integer id_aula);
}
