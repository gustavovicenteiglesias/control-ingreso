package com.unsada.integradora.model.mapper.impl;

import com.unsada.integradora.model.dto.AulaEdificioDto;
import com.unsada.integradora.model.entity.EntidadAula;
import com.unsada.integradora.model.mapper.interfaces.AulaEdificioMapper;
import org.springframework.stereotype.Component;

@Component
public class AulaEdificioMapperImpl implements AulaEdificioMapper {

    @Override
    public EntidadAula toEntity(AulaEdificioDto dto) {
        return null;
    }

    @Override
    public AulaEdificioDto toDTO(EntidadAula aula) {
        return new AulaEdificioDto(
                aula.getIdAula(),
                aula.getNombre(),
                aula.getCapacidadConAforo(),
                aula.getSesionPresencials(),
                aula.getEdificio().getNombre(),
                aula.getEdificio().getSede().getNombre()
        );
    }
}
