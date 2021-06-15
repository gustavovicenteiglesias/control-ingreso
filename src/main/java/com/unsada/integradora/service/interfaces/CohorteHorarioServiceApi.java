package com.unsada.integradora.service.interfaces;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

import com.unsada.integradora.model.entity.Cohorte;
import com.unsada.integradora.model.entity.CohorteHorario;
import com.unsada.integradora.model.entity.Horario;

public interface CohorteHorarioServiceApi extends CrudRepository<CohorteHorario, Integer> {

  public Optional<CohorteHorario> findByCohorteAndHorario(Cohorte cohorte, Horario data);
  public List<CohorteHorario> findByCohorte(Cohorte cohorte);




}
