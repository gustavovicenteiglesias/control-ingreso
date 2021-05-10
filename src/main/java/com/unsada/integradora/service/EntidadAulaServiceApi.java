package com.unsada.integradora.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.unsada.integradora.model.EntidadAula;
@Qualifier("EntidadAulaServiceApi")
public interface EntidadAulaServiceApi extends CrudRepository<EntidadAula, Integer> {
	@Modifying
	@Transactional
	@Query(value="SELECT * FROM entidad_aula a\r\n INNER JOIN edificio e ON e.id_edificio=a.id_edificio WHERE a.id_edificio= :edificio",nativeQuery = true )
	Iterable<EntidadAula> findByEdificio (@Param("edificio") int idEdificio);
}
