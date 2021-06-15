package com.unsada.integradora.service.interfaces;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.entity.Propuesta;
@Qualifier("propuestaServiceApi")
public interface PropuestaServiceApi extends CrudRepository<Propuesta, Integer> {
	@Transactional
	@Modifying
	@Query(value="SELECT * FROM propuesta p INNER JOIN dependencia d ON d.id_dependencia=p.id_dependencia WHERE p.id_dependencia= :dependencia",nativeQuery = true )
	Iterable<Propuesta> findByDependencia (@Param("dependencia") int idDependencia);
}
