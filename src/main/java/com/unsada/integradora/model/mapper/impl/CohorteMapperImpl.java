package com.unsada.integradora.model.mapper.impl;

import com.unsada.integradora.model.dto.CohorteDTO;
import com.unsada.integradora.model.entity.Cohorte;
import com.unsada.integradora.model.mapper.interfaces.CohorteMapper;
import org.springframework.stereotype.Component;

@Component
public class CohorteMapperImpl implements CohorteMapper {

    @Override
    public Cohorte toEntity(CohorteDTO dto) {
        return null;
    }

    @Override
    public CohorteDTO toDTO(Cohorte cohorte) {
        return new CohorteDTO(
                cohorte.getIdCohorte(),
                cohorte.getFechaInicio(),
                cohorte.getFechaFin(),
                cohorte.getNombreCohorte(),
                cohorte.getActividad(),
                cohorte.getSede()
        );
    }
}
