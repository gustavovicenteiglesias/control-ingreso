package com.unsada.integradora.util;

import com.unsada.integradora.model.entity.Horario;
import com.unsada.integradora.model.entity.SesionPresencial;
import com.unsada.integradora.model.entity.Solicitud;
import com.unsada.integradora.service.interfaces.CohorteServiceApi;
import com.unsada.integradora.service.interfaces.HorarioServiceApi;
import com.unsada.integradora.service.interfaces.SesionPresencialServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class SesionCreatorImpl implements SesionCreator{

    @Autowired
    HorarioServiceApi horarioServiceApi;


    @Override
    public SesionPresencial createSesion(SesionPresencial sesion, Date date) {
        date = Date.valueOf(date.toLocalDate().plusDays(1));
        sesion.setFecha(date);
        if(!sesion.getSolicituds().isEmpty()){
            sesion.getSolicituds().clear();
        }
        return sesion;
    }
}
