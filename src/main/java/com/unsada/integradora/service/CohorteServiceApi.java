package com.unsada.integradora.service;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.unsada.integradora.model.Actividad;
import com.unsada.integradora.model.Cohorte;

public interface CohorteServiceApi extends CrudRepository<Cohorte, Integer> {

  public List<Actividad> findByActividad(int idActividad);

}
