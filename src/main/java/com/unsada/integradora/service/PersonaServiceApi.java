package com.unsada.integradora.service;


import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.unsada.integradora.model.Persona;

public interface PersonaServiceApi extends CrudRepository<Persona, Integer> {
	static final String FIND_PERSONA_POR_SOLICITUD= "SELECT p.* FROM persona p INNER JOIN ddjj d ON d.id_persona = p.id_persona INNER JOIN solicitud so ON so.id_ddjj = d.id_ddjj WHERE so.id_solicitud = :idsolicitud";

	Optional<Persona> findByDni (String dni);
	@Query(value = FIND_PERSONA_POR_SOLICITUD , nativeQuery = true)
	public Persona findPersonaPorSolicitud(@Param("idsolicitud") Integer idsolicitud);

	@Query(value="SELECT p.* FROM persona p \r\n" + 
			"INNER JOIN ddjj d ON d.id_persona=p.id_persona\r\n" + 
			"INNER JOIN solicitud s ON s.id_ddjj=d.id_ddjj\r\n" + 
			"INNER JOIN sesion_presencial sp ON sp.id_sesion_presencial=s.id_sesion_presencial \r\n" + 
			"INNER Join entidad_aula a ON a.id_aula=sp.id_aula \r\n" + 
			"WHERE sp.fecha BETWEEN :fechainicio and :fechafin",nativeQuery = true )

	public Iterable<Persona> PersonaSesion(@Param("fechainicio") String fechainicio, @Param("fechafin")  String fechafin);
}
