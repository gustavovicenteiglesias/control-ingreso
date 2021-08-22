package com.unsada.integradora.dao;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.unsada.integradora.model.dto.SolicitudActividadDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.unsada.integradora.model.entity.Persona;

@Transactional
public interface PersonaDao extends CrudRepository<Persona, Integer> {
	String FIND_PERSONA_POR_SOLICITUD= "SELECT p.* FROM persona p INNER JOIN ddjj d " +
			"ON d.id_persona = p.id_persona INNER JOIN solicitud so " +
			"ON so.id_ddjj = d.id_ddjj WHERE so.id_solicitud = :idsolicitud";
	String FIND_PERSONA_CON_SOLICITUDES_POR_COHORTE =
			"SELECT DISTINCT p.* FROM persona p INNER JOIN ddjj dj\n" +
					"ON p.id_persona = dj.id_persona INNER JOIN solicitud s \n" +
					"ON dj.id_ddjj = s.id_ddjj INNER JOIN sesion_presencial sp \n" +
					"ON s.id_sesion_presencial = sp.id_sesion_presencial INNER JOIN cohorte_horario ch \n" +
					"ON sp.id_cohorte_horario = ch.id_cohorte_horario where ch.id_cohorte = :idCohorte";
	String FIND_PERSONA_CON_SOLICITUDES_POR_HORARIO =
			"SELECT DISTINCT p.* FROM persona p INNER JOIN ddjj dj\n" +
					"ON p.id_persona = dj.id_persona INNER JOIN solicitud s \n" +
					"ON dj.id_ddjj = s.id_ddjj INNER JOIN sesion_presencial sp \n" +
					"ON s.id_sesion_presencial = sp.id_sesion_presencial INNER JOIN cohorte_horario ch \n" +
					"ON sp.id_cohorte_horario = ch.id_cohorte_horario where ch.id_horario = :idHorario";
	String FIND_PERSONA_CON_SOLICITUDES_POR_SESION =
			"SELECT DISTINCT p.* FROM persona p INNER JOIN ddjj dj\n" +
					"ON p.id_persona = dj.id_persona INNER JOIN solicitud s \n" +
					"ON dj.id_ddjj = s.id_ddjj INNER JOIN sesion_presencial sp \n" +
					"ON s.id_sesion_presencial = sp.id_sesion_presencial INNER JOIN cohorte_horario ch \n" +
					"ON sp.id_cohorte_horario = ch.id_cohorte_horario where s.id_sesion_presencial = :idSesion";
	String PERSONA_SESION =
			"SELECT p.* FROM persona p \r\n" +
					"INNER JOIN ddjj d ON d.id_persona=p.id_persona\n" +
					"INNER JOIN solicitud s ON s.id_ddjj=d.id_ddjj\n" +
					"INNER JOIN sesion_presencial sp ON sp.id_sesion_presencial=s.id_sesion_presencial \n" +
					"INNER Join entidad_aula a ON a.id_aula=sp.id_aula \n" +
					"WHERE sp.fecha BETWEEN :fechainicio and :fechafin";

	@Query(value = FIND_PERSONA_CON_SOLICITUDES_POR_COHORTE , nativeQuery = true)
	public List<Persona> findPersonaConSolicitudesPorCohorte(@Param("idCohorte") Integer idsolicitud);

	@Query(value = FIND_PERSONA_CON_SOLICITUDES_POR_HORARIO,nativeQuery = true)
	public List<Persona> findPersonaConSolicitudesPorHorario(@Param("idHorario") Integer idHorario);

	@Query(value = FIND_PERSONA_CON_SOLICITUDES_POR_SESION,nativeQuery = true)
	public List<Persona> findPersonaConSolicitudesPorSesion(@Param("idSesion") Integer idSesion);

	@Modifying
	@Query(value=PERSONA_SESION, nativeQuery = true)
	public Iterable<Persona> PersonaSesion(@Param("fechainicio") String fechainicio, @Param("fechafin")  String fechafin);

    @Query(value = FIND_PERSONA_POR_SOLICITUD,nativeQuery = true)
	public Persona findPersonaPorSolicitud(@Param("idsolicitud") Integer idsolicitud);



	Optional<Persona> findByDni (String dni);
}

