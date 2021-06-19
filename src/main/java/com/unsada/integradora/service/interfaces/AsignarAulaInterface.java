package com.unsada.integradora.service.interfaces;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.ResponseEntity;

import java.util.Date;
@NoRepositoryBean
public interface AsignarAulaInterface {
    public ResponseEntity<String> asignarAula(int idAula, int idActividad, Date fecha);
}
