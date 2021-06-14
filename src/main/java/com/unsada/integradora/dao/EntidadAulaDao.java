package com.unsada.integradora.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.entity.EntidadAula;
@Transactional
public interface EntidadAulaDao extends CrudRepository<EntidadAula, Integer> {
	Iterable<EntidadAula> findByEdificio (int idEdificio);
	@Modifying
	@Query(value="SELECT a.* FROM entidad_aula a\r\n" + 
			"INNER JOIN sesion_presencial sp ON sp.id_aula=a.id_aula\r\n" + 
			"WHERE sp.id_sesion_presencial=?",nativeQuery = true)
	Optional<EntidadAula> findAulaSesion (Integer sesion_presencial);

}
