package com.unsada.integradora.service;

import org.springframework.data.repository.CrudRepository;

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

}
