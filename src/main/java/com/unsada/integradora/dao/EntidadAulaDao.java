package com.unsada.integradora.dao;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.entity.EntidadAula;
@Transactional
public interface EntidadAulaDao extends CrudRepository<EntidadAula, Integer>{

	@Modifying
	@javax.transaction.Transactional
	@Query(value="SELECT * FROM entidad_aula a\r\n INNER JOIN edificio e ON e.id_edificio=a.id_edificio WHERE a.id_edificio= :edificio",nativeQuery = true )
	Iterable<EntidadAula> findByEdificio (@Param("edificio") int idEdificio);
	@Query(value="SELECT a.* FROM entidad_aula a\r\n" +
			"INNER JOIN sesion_presencial sp ON sp.id_aula=a.id_aula\r\n" +
			"WHERE sp.id_sesion_presencial=?",nativeQuery = true)
	Optional<EntidadAula> findAulaSesion (Integer sesion_presencial);

}
