package com.unsada.integradora.model.mapper.impl;

import com.unsada.integradora.model.dto.ActividadDependenciasDTO;
import com.unsada.integradora.model.entity.Actividad;
import com.unsada.integradora.model.entity.Edificio;
import com.unsada.integradora.model.mapper.interfaces.ActividadDependenciasMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ActividadDependenciasMapperImpl implements ActividadDependenciasMapper {
    @Override
    public Actividad toEntity(ActividadDependenciasDTO dto) {
        return null;
    }

    @Override
    public ActividadDependenciasDTO toDTO(Actividad actividad) {
        Set<Edificio> edificios = actividad.getCohortes().stream()
                .flatMap( cohorte -> cohorte.getSede().getEdificios().stream().distinct())
                .collect(Collectors.toSet());

        return new ActividadDependenciasDTO(
                actividad.getIdActividad(),
                actividad.getPropuesta().getIdPropuesta(),
                actividad.getNombre(),
                actividad.getPropuesta().getDependencia().getNombre(),
                actividad.getPropuesta().getNombre(),
                new ArrayList<>(edificios)
        );
    }
}
