package com.unsada.integradora.service.interfaces;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.unsada.integradora.model.dto.SolicitudActividadDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.unsada.integradora.model.entity.Persona;

public interface PersonaServiceApi{
	void deleteById(Integer integer);
	void delete(Persona entity);
	<S extends Persona> S save(S entity);
	Optional<Persona> findByDni (String dni);
	Optional<Persona> findById (Integer id);
	List<Persona> findAll();
	Persona findPersonaPorSolicitud(@Param("idsolicitud") Integer idsolicitud);
	Iterable<Persona> PersonaSesion(@Param("fechainicio") String fechainicio, @Param("fechafin") String fechafin);
	List<Persona> findPersonasQueTienenSolicitudEnCohorte(@Param("idcohorte") Integer idCohorte);

}
