package com.unsada.integradora.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;

import com.unsada.integradora.model.Propuesta;
@Qualifier("propuestaServiceApi")
public interface PropuestaServiceApi extends CrudRepository<Propuesta, Integer> {

}
