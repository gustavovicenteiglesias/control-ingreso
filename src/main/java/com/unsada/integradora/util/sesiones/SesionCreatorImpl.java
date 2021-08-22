package com.unsada.integradora.util.sesiones;

import com.unsada.integradora.model.entity.SesionPresencial;
import com.unsada.integradora.service.interfaces.HorarioServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

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
