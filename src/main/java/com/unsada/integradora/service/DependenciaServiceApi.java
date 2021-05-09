package com.unsada.integradora.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;

import com.unsada.integradora.model.Dependencia;
@Qualifier("DependenciaServiceApi")
public interface DependenciaServiceApi extends CrudRepository<Dependencia, Integer> {

}
