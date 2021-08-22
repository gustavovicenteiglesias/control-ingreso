package com.unsada.integradora.model.mapper.impl;

import com.unsada.integradora.model.dto.PersonaDTO;
import com.unsada.integradora.model.entity.Persona;
import com.unsada.integradora.model.mapper.interfaces.PersonaMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapperImpl implements PersonaMapper {
    @Override
    public Persona toEntity(PersonaDTO dto) {
        return null;
    }

    @Override
    public PersonaDTO toDTO(Persona entity) {
        return new PersonaDTO(
                entity.getDni(),
                entity.getNombre(),
                entity.getCorreoElectronico(),
                entity.getTelefono()
        );
    }
}
