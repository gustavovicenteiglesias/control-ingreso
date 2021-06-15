package com.unsada.integradora.service.interfaces;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;

import com.unsada.integradora.model.entity.Dependencia;
@Qualifier("DependenciaServiceApi")
public interface DependenciaServiceApi extends CrudRepository<Dependencia, Integer> {

}
