package com.unsada.integradora.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.unsada.integradora.model.dto.SesionPresencialDTO;
import com.unsada.integradora.model.entity.Actividad;
import com.unsada.integradora.model.mapper.interfaces.SesionMapper;
import com.unsada.integradora.service.interfaces.ActividadServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unsada.integradora.model.entity.SesionPresencial;
import com.unsada.integradora.service.interfaces.SesionPresencialServiceApi;

@RestController
@RequestMapping(value = "/api/sesionpresencial")
@CrossOrigin("*")
public class SesionPresencialController {
	@Autowired
	SesionPresencialServiceApi sesionPresencialServiceApi;

	@Autowired
	ActividadServiceApi actividadServiceApi;

	@Autowired
	SesionMapper sesionMapper;

	@GetMapping(value = "/all")
	public Map<String, Object> listclase() {

		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			List<SesionPresencial> sesiones;
			sesiones = (List<SesionPresencial>) sesionPresencialServiceApi.findAll();
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

			response.put("data", sesiones);
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


	@GetMapping(value = "/find/{id}")
	public Map<String, Object> dataClase(@PathVariable("id") Integer id) {
		HashMap<String, Object> response = new HashMap<String, Object>();

		try {

			Optional<SesionPresencial> clase = sesionPresencialServiceApi.findById(id);

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
	@GetMapping(value = "/sesiones-por-persona/{id_persona}")//trae sesion por persona
	public Map<String, Object> dataClase2(@PathVariable("id_persona") Integer id_persona) {
		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			List<SesionPresencial> sesiones = (List<SesionPresencial>) sesionPresencialServiceApi.session_persona(id_persona);;
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

}
