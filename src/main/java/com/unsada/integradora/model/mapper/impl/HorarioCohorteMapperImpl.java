package com.unsada.integradora.model.mapper.impl;

import com.unsada.integradora.model.dto.HorarioCohorteDTO;
import com.unsada.integradora.model.entity.Horario;
import com.unsada.integradora.model.mapper.interfaces.HorarioCohorteMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class HorarioCohorteMapperImpl implements HorarioCohorteMapper {

    @Override
    public Horario toEntity(HorarioCohorteDTO dto) {
        return null;
    }

    @Override
    public HorarioCohorteDTO toDTO(Horario horario) {
        Set<String> nombreCohorte = new HashSet<>();
        nombreCohorte = horario.getCohorteHorarios().stream().map(i -> i.getCohorte().getNombreCohorte()).collect(Collectors.toSet());
        String nombreActividades = horario.getCohorteHorarios().stream().map(i -> i.getCohorte().getActividad().getNombre()).findFirst().get();
        boolean tieneSolicitudes = horario.getCohorteHorarios().stream()
                .anyMatch(i -> i.getSesionPresencials().stream()
                .noneMatch(j -> j.getSolicituds().isEmpty()));
        if(nombreActividades.isEmpty()){
            nombreActividades = "";
        }
        return new HorarioCohorteDTO(
                horario.getIdHorario(),
                horario.getDia(),
                horario.getHoraInicio(),
                horario.getHoraFin(),
                horario.getNombre(),
                nombreCohorte,
                nombreActividades,
                tieneSolicitudes



        );
    }
}
