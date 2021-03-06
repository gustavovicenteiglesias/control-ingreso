package com.unsada.integradora.controller;

//import java.lang.StackWalker.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.unsada.integradora.model.dto.CohorteDTO;
import com.unsada.integradora.model.mapper.interfaces.CohorteMapper;
import com.unsada.integradora.service.interfaces.SesionesGeneratorInterface;
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

import com.unsada.integradora.model.entity.Actividad;
import com.unsada.integradora.model.entity.Cohorte;
import com.unsada.integradora.model.entity.Sede;
import com.unsada.integradora.service.interfaces.ActividadServiceApi;
import com.unsada.integradora.service.interfaces.CohorteServiceApi;
import com.unsada.integradora.service.interfaces.SedeServiceApi;

import javax.swing.text.html.Option;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/cohorte")
@CrossOrigin("*")
public class CohorteController {
	@Autowired
	CohorteServiceApi cohorteServiceApi;
	@Autowired
	SedeServiceApi sedeServiceApi;
	@Autowired
	ActividadServiceApi actividadServiceApi;
	@Autowired
	SesionesGeneratorInterface sesionesGenerator;
	@Autowired
	CohorteMapper cohorteMapper;

	@GetMapping(value ="/por-actividad/{idActividad}")
	public Map<String, Object> listCohortesPorActividad(@PathVariable("idActividad") int idActividad){
		HashMap<String, Object> response = new HashMap<String, Object>();
		try {
			List<Cohorte> claseData;
			claseData = (List<Cohorte>) cohorteServiceApi.findAll();
			claseData.removeIf(i -> i.getActividad().getIdActividad() != idActividad);
			response.put("message", "Successful load");
			response.put("data",claseData);
			response.put("success", true);
			return response;
	
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", e.getMessage());
			response.put("success ", false);
			return response;
		}
	
	}

	@GetMapping(value = "/all")
	public Map<String, Object> listclase() {

	HashMap<String, Object> response = new HashMap<String, Object>();

	try {
		List<Cohorte> claseData;
		claseData = (List<Cohorte>) cohorteServiceApi.findAll();
		List<CohorteDTO> cohortes = claseData.stream().map(i ->cohorteMapper.toDTO(i)).collect(Collectors.toList());
		response.put("message", "Successful load");
		response.put("data",cohortes);
		response.put("success", true);
		return response;

	} catch (Exception e) {
		response.put("message", e.getMessage());
		response.put("success ", false);
		return response;
	}

}
	@GetMapping(value = "/find/actividad/{idactividad}")
	public Map<String, Object> listclase1(@PathVariable("idactividad") Integer idactividad) {

	HashMap<String, Object> response = new HashMap<String, Object>();

	try {
		List<Cohorte> claseData;
		claseData = (List<Cohorte>) cohorteServiceApi.findByActivity(idactividad);
		response.put("message", "Successful load");
		response.put("data",claseData);
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
			Optional<Cohorte> clase = cohorteServiceApi.findById(id);
			if (clase.isPresent()) {
				response.put("message", "Successful load");
				response.put("data", cohorteMapper.toDTO(clase.get()));
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
	
	@PostMapping(value = "/create/{idActividad}/{idSede}")
	public ResponseEntity<String> create(@RequestBody Cohorte data, @PathVariable ("idActividad") int idActividad, @PathVariable("idSede") int idSede) {
		Optional<Sede> sede = sedeServiceApi.findById(idSede);
		Optional<Actividad> actividad = actividadServiceApi.findById(idActividad);
		try {
			data.setActividad(actividad.get());
			data.setSede(sede.get());;
			Cohorte cohorte = cohorteServiceApi.save(data);
			return new ResponseEntity<>(String.valueOf(cohorte.getIdCohorte()), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("failed" + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PutMapping(value = "/update/{id}")

	public Map<String, Object> update(@PathVariable("id") Integer id, @Valid @RequestBody  Cohorte data) {

		HashMap<String, Object> response = new HashMap<String, Object>();
		Optional<Cohorte> cohorteOriginal = cohorteServiceApi.findById(id);

		try {
			if((data.getFechaFin() != null) && data.getFechaInicio() != null){
				sesionesGenerator.actualizarSesiones(cohorteOriginal.get(), data);
			}
			Cohorte cohorteActualizado= cohorteOriginal.get();
			cohorteActualizado.setFechaInicio(data.getFechaInicio());
			cohorteActualizado.setFechaFin(data.getFechaFin());
			if(data.getSede() == null){
				cohorteActualizado.setSede(cohorteOriginal.get().getSede());
			}
			cohorteServiceApi.save(cohorteActualizado);
			response.put("message", "Successful update");
			response.put("success", true);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", e.getMessage());
			response.put("success", false);
			return response;
		}

	}
	
	@DeleteMapping(value = "/delete/{id}")

	public Map<String, Object> update(@PathVariable("id") Integer id) {

		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			cohorteServiceApi.deleteById(id);
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
