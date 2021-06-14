package com.unsada.integradora.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.entity.Actividad;

public interface ActividadServiceApi extends CrudRepository<Actividad, Integer> {
	@Modifying
	@Transactional
	@Query(value="SELECT * FROM actividad a INNER JOIN propuesta p ON p.id_propuesta=a.id_propuesta WHERE a.id_propuesta= :propuesta",nativeQuery = true )
	Iterable<Actividad> findByPropuesta (@Param("propuesta") int idpropuesta);
}
