package com.unsada.integradora.service.interfaces;

import com.unsada.integradora.model.entity.Persona;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;

import com.unsada.integradora.model.entity.Ddjj;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Primary
public interface DDjjServiceApi extends CrudRepository<Ddjj, Integer> {
    public List<Ddjj> findByFechaAndPersona(Date fecha, Persona persona);
    public List<Ddjj> findByPersona(Persona persona);
}
