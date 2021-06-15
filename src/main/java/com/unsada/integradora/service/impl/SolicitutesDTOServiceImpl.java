package com.unsada.integradora.service.impl;

import com.unsada.integradora.dao.PersonaDao;
import com.unsada.integradora.model.dto.SolicitudActividadDTO;
import com.unsada.integradora.model.entity.Actividad;
import com.unsada.integradora.model.entity.Persona;
import com.unsada.integradora.model.entity.Solicitud;
import com.unsada.integradora.model.mapper.interfaces.SolicitudActividadMapper;
import com.unsada.integradora.service.interfaces.SolicitudServiceApi;
import com.unsada.integradora.service.interfaces.SolicitudesDTOServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolicitutesDTOServiceImpl implements SolicitudesDTOServiceApi {

    @Autowired
    PersonaDao personaDao;
    @Autowired
    SolicitudServiceApi solicitudServiceApi;
    @Autowired
    SolicitudActividadMapper solicitudActividadMapper;

    @Override
    public List<SolicitudActividadDTO> solicitudesContactos(Date fechaInicio, Date fechaFin, int idPersona) {
        List<Persona> enContacto = new ArrayList<Persona>();
        List<Solicitud> solicitudesContactos = new ArrayList<Solicitud>();
        List<SolicitudActividadDTO> solicitudActividadDTOS = new ArrayList<>();

        try {
            List<Solicitud> solicitudes = new ArrayList<Solicitud>();
            solicitudes = (List<Solicitud>) solicitudServiceApi.findSolicitudesInRange(fechaInicio, fechaFin);
            List<Integer> sesionesEnSeguimiento = solicitudServiceApi.findSolicitudesPorPersona(idPersona);
            solicitudes.removeIf(i -> !((i.getFechaCarga().compareTo(fechaInicio) > 0) && i.getFechaCarga().compareTo(fechaFin) <= 0));
            for (Solicitud s : solicitudes) {
                if (sesionesEnSeguimiento.contains(s.getSesionPresencial().getIdSesionPresencial())) {
                    Optional<Persona> p = Optional.ofNullable(s.getDdjj().getPersona());
                    if (!solicitudesContactos.stream().anyMatch(i -> i.getDdjj().getPersona().getNombre().equals(p.get().getNombre()))) {
                        solicitudesContactos.add(s);
                    }
                    ;
                }
            }
            solicitudActividadDTOS = solicitudesContactos.stream().map(temp -> {
                SolicitudActividadDTO obj = new SolicitudActividadDTO();
                Optional<Actividad> act = Optional.ofNullable(temp.getSesionPresencial().getCohorteHorario().getCohorte().getActividad());
                Optional<Persona> person = Optional.ofNullable(temp.getDdjj().getPersona());
                return solicitudActividadMapper.toDTO(temp);
            }).collect(Collectors.toList());

            return solicitudActividadDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
