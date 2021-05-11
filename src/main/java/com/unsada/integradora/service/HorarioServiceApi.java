package com.unsada.integradora.service;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.unsada.integradora.model.Cohorte;
import com.unsada.integradora.model.Horario;

public interface HorarioServiceApi extends CrudRepository<Horario, Integer> {


}
