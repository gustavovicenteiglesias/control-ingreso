package com.unsada.integradora.service;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.unsada.integradora.model.CohorteHorario;
import com.unsada.integradora.model.SesionPresencial;

public interface SesionPresencialServiceApi extends CrudRepository<SesionPresencial, Integer>{

  List<SesionPresencial> findByCohorteHorario(CohorteHorario cohorteHorario);

}
