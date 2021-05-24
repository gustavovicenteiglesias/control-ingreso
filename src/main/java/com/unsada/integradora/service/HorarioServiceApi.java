package com.unsada.integradora.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.unsada.integradora.model.Horario;

public interface HorarioServiceApi extends CrudRepository<Horario, Integer> {

	@Query(value="SELECT h.* FROM horario h\r\n" + 
			"INNER JOIN cohorte_horario co ON co.id_horario=h.id_horario\r\n" + 
			"INNER JOIN cohorte c ON c.id_cohorte=co.id_cohorte\r\n" + 
			"WHERE c.id_actividad= :actividad AND c.id_sede=:sede",nativeQuery = true)
	Iterable<Horario> findBySedeActividad(@Param("actividad")Integer actividad,@Param("sede")Integer sede);
}
