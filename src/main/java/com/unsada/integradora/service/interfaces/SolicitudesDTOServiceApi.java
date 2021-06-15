package com.unsada.integradora.service.interfaces;

import com.unsada.integradora.model.dto.SolicitudActividadDTO;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface SolicitudesDTOServiceApi  {
    public List<SolicitudActividadDTO> solicitudesContactos(Date fechaInicio, Date fechaFin, int idPersona);
}
