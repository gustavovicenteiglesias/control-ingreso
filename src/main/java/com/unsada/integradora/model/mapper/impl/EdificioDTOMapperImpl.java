package com.unsada.integradora.model.mapper.impl;

import com.unsada.integradora.model.dto.EdificioDTO;
import com.unsada.integradora.model.entity.Edificio;
import com.unsada.integradora.model.mapper.interfaces.EdificioDTOMapper;
import org.springframework.stereotype.Component;

@Component
public class EdificioDTOMapperImpl implements EdificioDTOMapper {
    @Override
    public Edificio toEntity(EdificioDTO dto) {
        return null;
    }

    @Override
    public EdificioDTO toDTO(Edificio entity) {
        return new EdificioDTO(
                entity.getDireccion(),
                entity.getNombre(),
                entity.getSede().getNombre() + " - " + entity.getNombre(),
                entity.getIdEdificio()
        );
    }
}
