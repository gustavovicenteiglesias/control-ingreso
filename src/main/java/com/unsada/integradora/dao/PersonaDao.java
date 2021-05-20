package com.unsada.integradora.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.Persona;

@Transactional
public interface PersonaDao extends CrudRepository<Persona, Integer> {
	Optional<Persona> findByDni (String dni);
	@Modifying

	@Query(value="SELECT p.* FROM persona p \r\n" + 
			"INNER JOIN ddjj d ON d.id_persona=p.id_persona\r\n" + 
			"INNER JOIN solicitud s ON s.id_ddjj=d.id_ddjj\r\n" + 
			"INNER JOIN sesion_presencial sp ON sp.id_sesion_presencial=s.id_sesion_presencial \r\n" + 
			"INNER Join entidad_aula a ON a.id_aula=sp.id_aula \r\n" + 
			"WHERE sp.fecha BETWEEN :fechainicio and :fechafin",nativeQuery = true )

	public Iterable<Persona> PersonaSesion(@Param("fechainicio") String fechainicio, @Param("fechafin")  String fechafin);

}

