package com.unsada.integradora.util;

import com.unsada.integradora.model.entity.SesionPresencial;
import com.unsada.integradora.model.entity.Solicitud;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface SesionCreator {
    public SesionPresencial createSesion(SesionPresencial sesion, Date date);
}
