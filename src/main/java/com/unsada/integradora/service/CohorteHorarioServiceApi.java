package com.unsada.integradora.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

import com.unsada.integradora.model.Actividad;
import com.unsada.integradora.model.Cohorte;
import com.unsada.integradora.model.CohorteHorario;
import com.unsada.integradora.model.Horario;

public interface CohorteHorarioServiceApi extends CrudRepository<CohorteHorario, Integer> {

  public Optional<CohorteHorario> findByCohorteAndHorario(Cohorte cohorte, Horario data);



}
