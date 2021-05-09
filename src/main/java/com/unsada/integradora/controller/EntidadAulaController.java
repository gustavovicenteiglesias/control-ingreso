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

import com.unsada.integradora.model.Edificio;
import com.unsada.integradora.model.EntidadAula;
import com.unsada.integradora.service.EdificioServiceApi;
import com.unsada.integradora.service.EntidadAulaServiceApi;

@RestController
@RequestMapping(value = "/api/aula")
@CrossOrigin("*")
public class EntidadAulaController {
	@Autowired
	EntidadAulaServiceApi entidadAulaServiceApi;
	@Autowired
	@Qualifier("EdificioServiceApi")
	EdificioServiceApi edificioServiceApi;
	@GetMapping(value = "/all")
	public Map<String, Object> listclase() {

		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			List<EntidadAula> claseData;
			claseData = (List<EntidadAula>) entidadAulaServiceApi.findAll();
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
	@GetMapping(value = "/find/edificio/{id}")
	public Map<String, Object> listclase1(@PathVariable("id") int id) {

		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			List<EntidadAula> claseData;
			claseData = (List<EntidadAula>) entidadAulaServiceApi.findByEdificio(id);
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

			Optional<EntidadAula> clase = entidadAulaServiceApi.findById(id);

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
	public ResponseEntity<String> create(@RequestBody EntidadAula data) {

		try {
			entidadAulaServiceApi.save(data);
			return new ResponseEntity<>("Save successful ", HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("" + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping(value = "/update/{id}")

	public Map<String, Object> update(@PathVariable("id") Integer id, @RequestBody EntidadAula data) {

		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			data.setIdAula(id);
			entidadAulaServiceApi.save(data);
			response.put("message", "Successful update");
			response.put("success", true);
			return response;
		} catch (Exception e) {
			response.put("message", e.getMessage());
			response.put("success", false);
			return response;
		}

	}
	
	@PutMapping(value = "/update/edificio/{id}/{idEdificio}")

	public Map<String, Object> updat1e(@PathVariable("id") Integer id,@PathVariable("idEdificio") Integer idEdificio, @RequestBody EntidadAula data) {

		HashMap<String, Object> response = new HashMap<String, Object>();
		Edificio edificio=edificioServiceApi.findById(idEdificio).get();
		try {
			data.setIdAula(id);
			data.setEdificio(edificio);
			entidadAulaServiceApi.save(data);
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
			entidadAulaServiceApi.deleteById(id);
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
