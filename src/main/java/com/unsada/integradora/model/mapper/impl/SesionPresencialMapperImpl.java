package com.unsada.integradora.model.mapper.impl;

import com.unsada.integradora.model.dto.SesionPresencialDTO;
import com.unsada.integradora.model.entity.SesionPresencial;
import com.unsada.integradora.model.mapper.interfaces.SesionMapper;
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
        return new SesionPresencialDTO(
                sesion.getFecha(),
                sesion.getIdSesionPresencial(),
                sesion.getCohorteHorario().getCohorte().getActividad().getNombre(),
                sesion.getCohorteHorario().getHorario().getDia(),
                sesion.getCohorteHorario().getHorario().getHoraInicio(),
                sesion.getCohorteHorario().getHorario().getHoraFin()
                );
    }
}
