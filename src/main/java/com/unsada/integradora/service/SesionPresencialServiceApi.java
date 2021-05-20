package com.unsada.integradora.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.unsada.integradora.model.CohorteHorario;
import com.unsada.integradora.model.EntidadAula;
import com.unsada.integradora.model.SesionPresencial;

public interface SesionPresencialServiceApi extends CrudRepository<SesionPresencial, Integer>{

  List<SesionPresencial> findByCohorteHorario(CohorteHorario cohorteHorario);

  Optional<SesionPresencial> findByCohorteHorarioAndFecha(CohorteHorario cohorteHorario, Date date);

  Optional<SesionPresencial> findByEntidadAulaAndCohorteHorarioAndFecha(EntidadAula entidadAula, CohorteHorario cohorteHorario,
      java.sql.Date date);
  @Query(value="SELECT sp.* FROM sesion_presencial sp \r\n" + 
  		"INNER JOIN solicitud s ON s.id_sesion_presencial=sp.id_sesion_presencial\r\n" + 
  		"INNER JOIN ddjj d ON d.id_ddjj=s.id_ddjj\r\n" + 
  		"INNER JOIN persona p ON p.id_persona=s.id_ddjj\r\n" + 
  		"WHERE p.id_persona= :id_persona",nativeQuery = true )
  Optional<SesionPresencial> session_persona (@Param("id_persona") int id_persona );

}
