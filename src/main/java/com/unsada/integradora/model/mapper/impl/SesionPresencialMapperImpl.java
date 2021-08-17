package com.unsada.integradora.model.mapper.impl;

import com.unsada.integradora.model.dto.SesionPresencialDTO;
import com.unsada.integradora.model.entity.SesionPresencial;
import com.unsada.integradora.model.mapper.interfaces.SesionMapper;
import com.unsada.integradora.service.interfaces.CohorteServiceApi;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class SesionPresencialMapperImpl implements SesionMapper {
    @Override
    public SesionPresencial toEntity(SesionPresencialDTO dto) {
        return null;
    }

    @Override
    public SesionPresencialDTO toDTO(SesionPresencial sesion) {
        boolean tieneSolicitudes = !sesion.getSolicituds().isEmpty();
        int idEdificio = 0;
        try{
            idEdificio = sesion.getEntidadAula().getEdificio().getIdEdificio();
        }catch (Exception e){
            idEdificio = 0;
        }

        return new SesionPresencialDTO(
                sesion.getFecha(),
                sesion.getIdSesionPresencial(),
                sesion.getCohorteHorario().getCohorte().getActividad().getNombre(),
                CohorteServiceApi.maskDayOfweek(sesion.getFecha().toLocalDate().getDayOfWeek()),
                sesion.getCohorteHorario().getHorario().getHoraInicio(),
                sesion.getCohorteHorario().getHorario().getHoraFin(),
                tieneSolicitudes,
                sesion.getEntidadAula(),
                idEdificio,
                sesion.getCohorteHorario().getCohorte().getNombreCohorte()

                );
    }
}
