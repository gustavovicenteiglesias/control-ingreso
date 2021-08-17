package com.unsada.integradora.service.interfaces;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.unsada.integradora.dao.SolicitudDao;
import com.unsada.integradora.model.dto.SolicitudActividadDTO;
import com.unsada.integradora.model.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


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
