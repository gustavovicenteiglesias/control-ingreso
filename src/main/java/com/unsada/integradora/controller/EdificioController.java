package com.unsada.integradora.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.unsada.integradora.model.entity.Edificio;

import com.unsada.integradora.model.entity.Sede;
import com.unsada.integradora.service.interfaces.EdificioServiceApi;
import com.unsada.integradora.service.interfaces.SedeServiceApi;


@RestController
@RequestMapping(value = "/api/edificio")
@CrossOrigin("*")
public class EdificioController {
	@Autowired
	EdificioServiceApi edificioServiceApi;
	
	@Autowired
	@Qualifier(" SedeServiceApi")
	SedeServiceApi sedeServiceApi;

	@GetMapping(value = "/all")
	public Map<String, Object> listclase() {

		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			List<Edificio> claseData;
			claseData = (List<Edificio>) edificioServiceApi.findAll();
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

			Optional<Edificio> clase = edificioServiceApi.findById(id);

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
	
	@GetMapping(value = "/edificioByAula/{id}")
	public Map<String, Object> dataClase2(@PathVariable("id") Integer id) {
		HashMap<String, Object> response = new HashMap<String, Object>();

		try {

			Optional<Edificio> clase = edificioServiceApi.findEdificioByAula(id);

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
	@GetMapping(value = "/sede/find/{id}")
	public Map<String, Object> dataClase1(@PathVariable("id") int id) {
		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			List<Edificio> claseData;
			claseData = (List<Edificio>) edificioServiceApi.findBysede(id);
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
	@PostMapping(value = "/create")
	public ResponseEntity<String> create(@RequestBody Edificio data) {

		try {
			edificioServiceApi.save(data);
			return new ResponseEntity<>("Save successful ", HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("" + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(value = "/update/{id}")

	public Map<String, Object> update(@PathVariable("id") Integer id, @RequestBody Edificio data) {

		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			data.setIdEdificio(id);
			edificioServiceApi.save(data);
			response.put("message", "Successful update");
			response.put("success", true);
			return response;
		} catch (Exception e) {
			response.put("message", e.getMessage());
			response.put("success", false);
			return response;
		}

	}
	
	@PutMapping(value = "/update/sede/{id}/{idSede}")

	public Map<String, Object> update1(@PathVariable("id") Integer id,@PathVariable("idSede") Integer idSede, @RequestBody Edificio data) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		Sede sede=sedeServiceApi.findById(idSede).get();
		try {
			data.setIdEdificio(id);
			data.setSede(sede);
			edificioServiceApi.save(data);
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
			edificioServiceApi.deleteById(id);
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
