package com.unsada.integradora.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

import com.unsada.integradora.model.Actividad;
import com.unsada.integradora.model.Cohorte;

public interface CohorteServiceApi extends CrudRepository<Cohorte, Integer> {

  public List<Cohorte> findByActividad(Optional<Actividad> actividad);

  public List<Cohorte> findByIdCohorteAndActividad(int idCohorte, Actividad actividad);
  @Query(value= "SELECT * FROM cohorte WHERE id_actividad=?", nativeQuery = true)
   Iterable<Cohorte> findByActivity (Integer id_actividad);

}
