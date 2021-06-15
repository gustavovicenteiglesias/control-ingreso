package com.unsada.integradora.service.interfaces;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.unsada.integradora.model.entity.EntidadAula;
@Qualifier("EntidadAulaServiceApi")
public interface EntidadAulaServiceApi extends CrudRepository<EntidadAula, Integer> {
	@Modifying
	@Transactional
	@Query(value="SELECT * FROM entidad_aula a\r\n INNER JOIN edificio e ON e.id_edificio=a.id_edificio WHERE a.id_edificio= :edificio",nativeQuery = true )
	Iterable<EntidadAula> findByEdificio (@Param("edificio") int idEdificio);
	
	@Query(value="SELECT a.* FROM entidad_aula a\r\n" + 
			"INNER JOIN sesion_presencial sp ON sp.id_aula=a.id_aula\r\n" + 
			"WHERE sp.id_sesion_presencial=?",nativeQuery = true)
	Optional<EntidadAula> findAulaSesion (Integer sesion_presencial);
}
