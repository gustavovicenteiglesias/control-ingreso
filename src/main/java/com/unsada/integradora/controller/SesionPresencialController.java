package com.unsada.integradora.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.unsada.integradora.model.dto.SesionPresencialDTO;
import com.unsada.integradora.model.entity.Actividad;
import com.unsada.integradora.model.mapper.interfaces.SesionMapper;
import com.unsada.integradora.service.interfaces.*;
import com.unsada.integradora.util.SesionCreator;
import com.unsada.integradora.util.SesionesFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unsada.integradora.model.entity.SesionPresencial;

@RestController
@RequestMapping(value = "/api/sesionpresencial")
@CrossOrigin("*")
public class SesionPresencialController {
	@Autowired
	SesionPresencialServiceApi sesionPresencialServiceApi;

	@Autowired
	ActividadServiceApi actividadServiceApi;

	@Autowired
	HorarioServiceApi horarioServiceApi;

	@Autowired
	SesionMapper sesionMapper;

	@Autowired
	SolicitudServiceApi solicitudServiceApi;

	@Autowired
	SesionesFilter sesionesFilter;

	@Autowired
	SesionCreator sesionCreator;


	@GetMapping(value = "/all")
	public Map<String, Object> listclase() {

		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			List<SesionPresencial> sesiones;
			sesiones = (List<SesionPresencial>) sesionPresencialServiceApi.findAll();
			List<SesionPresencialDTO> sesionDto = sesiones.stream().map(i ->{
				return sesionMapper.toDTO(i);
			} ).collect(Collectors.toList());
			response.put("message", "Successful load");
			response.put("data", sesionDto);
			response.put("success", true);
			return response;

		} catch (Exception e) {
			response.put("message", e.getMessage());
			response.put("success ", false);
			e.printStackTrace();
			return response;
		}

	}
	//Sesiones por actividad:
	@GetMapping(value = "/por-actividad/{idActividad}")
	public Map<String, Object> listarPorActividad(@PathVariable("idActividad") int idActividad){
		HashMap<String, Object> response = new HashMap<String, Object>();
		Optional<Actividad> actividad = actividadServiceApi.findById(idActividad);
		try{
			List<SesionPresencial> sesiones = actividad.stream()
					.flatMap(i -> i.getCohortes().stream())
					.flatMap(i -> i.getCohorteHorarios().stream())
					.flatMap(i -> i.getSesionPresencials().stream())
					.collect(Collectors.toList());

			List<SesionPresencialDTO> sesionPresencialDTOS = sesiones.stream().map(i -> sesionMapper.toDTO(i)).collect(Collectors.toList());
			response.put("data", sesionPresencialDTOS);
			response.put("Sucess", true);
			return response;
		}catch (Exception e){
			e.printStackTrace();
			return response;
		}
	}
	@GetMapping(value = "/sesionhorario/{id_horario}")
	public Map<String, Object> listclase1(@PathVariable("id_horario") Integer id_horario) {

		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			List<SesionPresencial> claseData;
			claseData = (List<SesionPresencial>) sesionPresencialServiceApi.findSesionHoratio(id_horario);
			claseData = sesionesFilter.filterSesionesFechaActual(claseData);
			List<SesionPresencialDTO> sesionPresencialDTOS = claseData.stream().map(i -> sesionMapper.toDTO(i)).collect(Collectors.toList());
			response.put("data", sesionPresencialDTOS);
			response.put("message", "Successful load");
			response.put("success", true);
			return response;

		} catch (Exception e) {
			response.put("message", e.getMessage());
			response.put("success ", false);
			return response;
		}

	}


	@GetMapping(value = "/find/{id}")
	public Map<String, Object> dataClase(@PathVariable("id") Integer id) {
		HashMap<String, Object> response = new HashMap<String, Object>();

		try {

			Optional<SesionPresencial> clase = sesionPresencialServiceApi.findById(id);

			if (clase.isPresent()) {
				response.put("message", "Successful load");
				response.put("data", sesionMapper.toDTO(clase.get()));
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
	@GetMapping(value = "/sesiones-por-persona/{id_persona}")//trae sesion por persona
	public Map<String, Object> dataClase2(@PathVariable("id_persona") Integer id_persona) {
		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			List<SesionPresencial> sesiones = (List<SesionPresencial>) sesionPresencialServiceApi.session_persona(id_persona);
			List<SesionPresencialDTO> sesionDto = sesiones.stream().map(i -> sesionMapper.toDTO(i)).collect(Collectors.toList());

			response.put("message", "Successful load");
			response.put("data", sesionDto);
			response.put("success", true);
			return response;

		} catch (Exception e) {
			response.put("message", e.getMessage());
			response.put("success ", false);
			return response;
		}

		
	}

	@PostMapping(value = "/create")
	public ResponseEntity<String> create(@RequestBody SesionPresencial data) {

		try {
			sesionPresencialServiceApi.save(data);
			return new ResponseEntity<>("Save successful ", HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("" + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(value = "/update/{id}")

	public Map<String, Object> update(@PathVariable("id") Integer id, @RequestBody SesionPresencial data) {

		HashMap<String, Object> response = new HashMap<String, Object>();
		try {
			data.setIdSesionPresencial(id);
			sesionPresencialServiceApi.save(data);
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
			sesionPresencialServiceApi.deleteById(id);
			response.put("message", "Successful delete");
			response.put("success", true);
			return response;
		} catch (Exception e) {
			response.put("message", e.getMessage());
			response.put("success", false);
			return response;
		}
	}

	@PutMapping(value = "/update-fecha/{idsesion}")
	public Map<String, Object> updateFecha(@RequestBody SesionPresencial ses, @PathVariable("idsesion") Integer idsesion) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		Date date = ses.getFecha();
		Optional<SesionPresencial> sesion = sesionPresencialServiceApi.findById(idsesion);

		try{
			SesionPresencial s = sesionCreator.createSesion(sesion.get(), ses.getFecha());
			System.out.println(s);
			response.put("success", true);
			response.put("message", "fecha cambiada");
			sesionPresencialServiceApi.save(s);

		}catch (Exception e){
			e.printStackTrace();
			response.put("success", false);
			response.put("message", "error en el cambio de fecha para la sesion");
		}

		return response;

	}


}
