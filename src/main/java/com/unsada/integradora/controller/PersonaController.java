package com.unsada.integradora.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.unsada.integradora.model.dto.SolicitudActividadDTO;
import com.unsada.integradora.model.entity.Actividad;
import com.unsada.integradora.model.entity.Horario;
import com.unsada.integradora.model.entity.Persona;
import com.unsada.integradora.model.entity.Solicitud;
import com.unsada.integradora.model.mapper.interfaces.SolicitudActividadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unsada.integradora.service.PersonaServiceApi;
import com.unsada.integradora.service.SolicitudServiceApi;

@RestController
@RequestMapping(value = "/api/persona")
@CrossOrigin("*")
public class PersonaController {
	@Autowired
	PersonaServiceApi personaServiceApi;
	@Autowired
	SolicitudServiceApi solicitudServiceApi;
	@Autowired
	SolicitudActividadMapper solicitudActividadMapper;

	@GetMapping(value = "/all")
	public Map<String, Object> listclase() {

		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			List<Persona> claseData;
			claseData = (List<Persona>) personaServiceApi.findAll();
			response.put("message", "Successful load");
			response.put("data", claseData);
			response.put("success", true);
			return response;

		} catch (Exception e) {
			response.put("message", e.getMessage());
			response.put("success ", false);
			return response;
		}

	}

	@GetMapping(value = "/find/persona_sesion/{fechainicio}/{fechafin}/{idPersona}")
	public Map<String, Object> dataClase1 (@PathVariable("fechainicio") Date fechainicio,@PathVariable("fechafin") Date fechafin, @PathVariable("idPersona") int idPersona) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		List<Persona> enContacto = new ArrayList<Persona>();
		List<Solicitud> solicitudesContactos = new ArrayList<Solicitud>();
		List<SolicitudActividadDTO> solicitudActividadDTOS = new ArrayList<>();

		try {
			List<Solicitud> solicitudes = new ArrayList<Solicitud>();
			solicitudes = (List<Solicitud>) solicitudServiceApi.findSolicitudesInRange(fechainicio, fechafin);
			List<Integer> sesionesEnSeguimiento = solicitudServiceApi.findSolicitudesPorPersona(idPersona);
			solicitudes.removeIf(i -> !((i.getFechaCarga().compareTo(fechainicio) > 0 ) && i.getFechaCarga().compareTo(fechafin) <=0));
			for (Solicitud s : solicitudes) {
				if(sesionesEnSeguimiento.contains(s.getSesionPresencial().getIdSesionPresencial()) ) {
					Optional<Persona> p = Optional.ofNullable(s.getDdjj().getPersona());
					if(!solicitudesContactos.stream().anyMatch(i -> i.getDdjj().getPersona().getNombre().equals(p.get().getNombre()))){
						solicitudesContactos.add(s);
					};
				}
			}
			solicitudActividadDTOS = solicitudesContactos.stream().map(temp ->{
				SolicitudActividadDTO obj = new SolicitudActividadDTO();
				Optional<Actividad> act = Optional.ofNullable(temp.getSesionPresencial().getCohorteHorario().getCohorte().getActividad());
				Optional<Persona> person = Optional.ofNullable(temp.getDdjj().getPersona());
				return solicitudActividadMapper.toDTO(temp);
			}).collect(Collectors.toList());

			response.put("message", "Success");
			response.put("personas", solicitudActividadDTOS);
			response.put("success", true);
			return response;

		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", e.getMessage());
			response.put("success ", false);
			return response;
		}
	}
	private Entry<Persona, Horario> newEntry(Persona persona, Solicitud solicitud) {
		return null;
	}

	public boolean compararFechas(Date index, Date fechainicio, Date fechafin){
		if(index.compareTo(fechainicio) > 0 && index.compareTo(fechafin) <= 0){
			System.out.println("fecha entre este margen");
			return true;
		};
		return false;
	}
	@GetMapping(value = "/find/{id}")
	public Map<String, Object> dataClase(@PathVariable("id") Integer id) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		try {

			Optional<Persona> clase = personaServiceApi.findById(id);

			if (clase.isPresent()) {
				response.put("message", "Successful load");
				response.put("data", clase);
				response.put("success", true);
				return response;
			} else {
				response.put("message", "Not found data");
				response.put("data", null);
				response.put("success", false);
				return response;
			}

		} catch (Exception e) {
			response.put("message", "" + e.getMessage());
			response.put("success", false);
			return response;
		}
	}
	@GetMapping(value = "/find/dni/{id}")
	public Map<String, Object> dataClase(@PathVariable("id") String id) {
		HashMap<String, Object> response = new HashMap<String, Object>();

		try {

			Optional<Persona> clase = personaServiceApi.findByDni(id);

			if (clase.isPresent()) {
				response.put("message", "Successful load");
				response.put("data", clase);
				response.put("success", true);
				return response;
			} else {
				response.put("message", "Not found data");
				response.put("data", null);
				response.put("success", false);
				return response;
			}

		} catch (Exception e) {
			response.put("message", "" + e.getMessage());
			response.put("success", false);
			return response;
		}
	}

	@PostMapping(value = "/create")
	public Map<String, Object> create(@RequestBody Persona data) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		try {
			int id_persona=personaServiceApi.save(data).getIdPersona();
			response.put("message", "Successful load");
			response.put("data", id_persona);
			response.put("success", true);
			return response;
		} catch (Exception e) {
			response.put("message", "" + e.getMessage());
			response.put("success", false);
			return response;
			
		}

	}

	@PutMapping(value = "/update/{id}")

	public Map<String, Object> update(@PathVariable("id") Integer id, @RequestBody Persona data) {

		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			data.setIdPersona(id);
			personaServiceApi.save(data);
			response.put("message", "Successful update");
			response.put("success", true);
			return response;
		} catch (Exception e) {
			response.put("message", e.getMessage());
			response.put("success", false);
			return response;
		}

	}

	@DeleteMapping(value = "/delete/{id}")

	public Map<String, Object> update(@PathVariable("id") Integer id) {

		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			personaServiceApi.deleteById(id);
			response.put("message", "Successful delete");
			response.put("success", true);
			return response;
		} catch (Exception e) {
			response.put("message", e.getMessage());
			response.put("success", false);
			return response;
		}
	}

}
