package com.unsada.integradora.model.mapper.impl;

import com.unsada.integradora.model.dto.ActividadDependenciasDTO;
import com.unsada.integradora.model.entity.Actividad;
import com.unsada.integradora.model.mapper.interfaces.ActividadDependenciasMapper;
import org.springframework.stereotype.Component;

@Component
public class ActividadDependenciasMapperImpl implements ActividadDependenciasMapper {
    @Override
    public Actividad toEntity(ActividadDependenciasDTO dto) {
        return null;
    }

    @Override
    public ActividadDependenciasDTO toDTO(Actividad actividad) {
        return new ActividadDependenciasDTO(
                actividad.getIdActividad(),
                actividad.getPropuesta().getIdPropuesta(),
                actividad.getNombre(),
                actividad.getPropuesta().getDependencia().getNombre(),
                actividad.getPropuesta().getNombre()
        );
    }
}
