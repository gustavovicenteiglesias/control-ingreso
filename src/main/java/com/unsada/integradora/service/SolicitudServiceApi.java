package com.unsada.integradora.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.unsada.integradora.model.entity.SesionPresencial;
import com.unsada.integradora.model.entity.Solicitud;

public interface SolicitudServiceApi extends CrudRepository<Solicitud, Integer> {
	static final String FIND_SOLICITUDES_IN_RANGE = "SELECT s.* FROM solicitud s INNER JOIN sesion_presencial sp ON sp.id_sesion_presencial=s.id_sesion_presencial";
	static final String FIND_SESIONES_POR_PERSONA ="SELECT s.id_sesion_presencial FROM solicitud s INNER JOIN ddjj d ON s.id_ddjj = d.id_ddjj WHERE d.id_persona = :idpersona ";
	
	@Query(value= "SELECT * FROM `solicitud` WHERE qr_acceso=?", nativeQuery = true)
	public Optional<Solicitud> findByQr (String qr_acceso);
	
	public int countBySesionPresencialAndFechaCarga(SesionPresencial sesion, Date fecha);

	@Query(value = FIND_SESIONES_POR_PERSONA, nativeQuery = true)
	public List<Integer> findSolicitudesPorPersona(@Param("idpersona") int idpersona);

	
	
	@Query(value= FIND_SOLICITUDES_IN_RANGE, nativeQuery = true )
	public List<Solicitud> findSolicitudesInRange(@Param("fechainicio") Date fechainicio, @Param("fechafin") Date fechafin);
}
