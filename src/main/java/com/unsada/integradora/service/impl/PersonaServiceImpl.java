package com.unsada.integradora.service.impl;


import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.unsada.integradora.model.dto.SolicitudActividadDTO;
import com.unsada.integradora.model.entity.Actividad;
import com.unsada.integradora.model.entity.Solicitud;
import com.unsada.integradora.model.mapper.interfaces.SolicitudActividadMapper;
import com.unsada.integradora.service.interfaces.PersonaServiceApi;
import com.unsada.integradora.service.interfaces.SolicitudServiceApi;
import com.unsada.integradora.service.interfaces.SolicitudesDTOServiceApi;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unsada.integradora.dao.PersonaDao;
import com.unsada.integradora.model.entity.Persona;
@Service
public class PersonaServiceImpl implements PersonaServiceApi, SolicitudesDTOServiceApi {
	@Autowired
	PersonaDao personaDao;
	@Autowired
	SolicitudServiceApi solicitudServiceApi;
	@Autowired
	SolicitudActividadMapper solicitudActividadMapper;
	@Override
	public <S extends Persona> S save(S entity) {
		// TODO Auto-generated method stub
		return personaDao.save(entity);
	}

	@Override
	public Optional<Persona> findByDni(String dni) {
		return personaDao.findByDni(dni);
	}

	@Override
	public Optional<Persona> findById(Integer id) {
		return personaDao.findById(id);
	}

	@Override
	public List<Persona> findAll() {
		return (List<Persona>) personaDao.findAll();
	}


	public void deleteById(Integer id) {
		personaDao.deleteById(id);
	}

	@Override
	public void delete(Persona entity) {
		personaDao.delete(entity);
	}


	@Override
	public Persona findPersonaPorSolicitud(Integer idsolicitud) {
		return personaDao.findPersonaPorSolicitud(idsolicitud);
	}

	@Override
	public Iterable<Persona> PersonaSesion(String fechainicio, String fechafin) {
		return null;
	}

	@Override
	public List<Persona> findPersonasQueTienenSolicitudEnCohorte(Integer idCohorte) {
		return personaDao.findPersonaConSolicitudesPorCohorte(idCohorte);
	}

	@Override
	public List<Persona> findPersonasQueTienenSolicitudEnHorario(Integer idHorario) {
		return personaDao.findPersonaConSolicitudesPorHorario(idHorario);
	}

	@Override
	public List<Persona> findPersonasQueTienenSolicitudEnSesion(Integer idSesion) {
		return personaDao.findPersonaConSolicitudesPorSesion(idSesion);
	}


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
			solicitudes.removeIf(i -> i.getPresente() != 1);
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
