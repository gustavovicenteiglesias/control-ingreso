package com.unsada.integradora.model.mapper.impl;

import com.unsada.integradora.model.dto.SolicitudActividadDTO;
import com.unsada.integradora.model.entity.Solicitud;
import com.unsada.integradora.model.mapper.interfaces.SolicitudActividadMapper;
import org.springframework.stereotype.Component;

@Component
public class SolicitudDependenciasMapperImpl implements SolicitudActividadMapper {

    @Override
    public Solicitud toEntity(SolicitudActividadDTO dto) {
        return null;
    }

    @Override
    public SolicitudActividadDTO toDTO(Solicitud solicitud) {
        String nombreCompleto =  solicitud.getSesionPresencial().getEntidadAula().getEdificio().getSede().getNombre()+ " - " +solicitud.getSesionPresencial().getEntidadAula().getEdificio().getNombre();
        return new SolicitudActividadDTO(
                solicitud.getFechaCarga(),
                solicitud.getPresente(),
                solicitud.getId_solicitud(),
                solicitud.getDdjj().getPersona().getNombre(),
                solicitud.getDdjj().getPersona().getTelefono(),
                solicitud.getDdjj().getPersona().getCorreoElectronico(),
                solicitud.getSesionPresencial().getCohorteHorario().getCohorte().getActividad().getNombre(),
                solicitud.getSesionPresencial().getCohorteHorario().getCohorte().getActividad().getPropuesta().getDependencia().getNombre(),
                solicitud.getSesionPresencial().getCohorteHorario().getCohorte().getActividad().getPropuesta().getNombre(),
                solicitud.getSesionPresencial().getEntidadAula().getEdificio().getNombre(),
                solicitud.getSesionPresencial().getEntidadAula().getEdificio().getSede().getNombre(),
                nombreCompleto
                );
    }
}
