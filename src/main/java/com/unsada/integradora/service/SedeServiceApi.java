package com.unsada.integradora.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;

import com.unsada.integradora.model.entity.Sede;
@Qualifier(" SedeServiceApi")
public interface SedeServiceApi extends CrudRepository<Sede, Integer> {

}
