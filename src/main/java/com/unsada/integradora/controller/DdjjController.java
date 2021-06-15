package com.unsada.integradora.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.unsada.integradora.model.entity.Ddjj;
import com.unsada.integradora.model.entity.Persona;
import com.unsada.integradora.model.entity.Respuesta;
import com.unsada.integradora.service.interfaces.DDjjServiceApi;
import com.unsada.integradora.service.interfaces.FactorDeRiesgoServiceApi;
import com.unsada.integradora.service.interfaces.PersonaServiceApi;
import com.unsada.integradora.service.interfaces.PreguntaServiceApi;
import com.unsada.integradora.service.interfaces.RespuestaServiceApi;

@RestController
@RequestMapping(value = "/api/ddjj")
@CrossOrigin("*")
public class DdjjController {
	@Autowired
	DDjjServiceApi ddjjServiceApi;
	@Autowired
	PersonaServiceApi personaServiceApi ;
	@Autowired
	FactorDeRiesgoServiceApi factorDeRiesgoServiceApi;
	@Autowired
	PreguntaServiceApi preguntaServiceApi ;
	@Autowired
	RespuestaServiceApi respuestaServiceApi;
	
	
	
	@GetMapping(value = "/all")
	public Map<String, Object> listclase() {

		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			List<Ddjj> claseData;
			claseData = (List<Ddjj>) ddjjServiceApi.findAll();
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

			Optional<Ddjj> clase = ddjjServiceApi.findById(id);

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
	public ResponseEntity<String> create(@RequestBody Ddjj data) {

		try {
			ddjjServiceApi.save(data);
			return new ResponseEntity<>("Save successful ", HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("" + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	

	@PostMapping(value = "/crear/{idpersona}")
	public Map<String, Object> crear(@PathVariable("idpersona") Integer id,@RequestBody Ddjj data) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		Persona persona=personaServiceApi.findById(id).get();
		
		Ddjj ddjjss=new Ddjj();
		
		
		
		try {
			
			int dj=ddjjServiceApi.save(ddjjss).getIdDdjj();
			
			ddjjss.setIdDdjj(dj);
			ddjjss.setFecha(data.getFecha());
			ddjjss.setPersona(persona);
			
			ddjjss.setRespuestas(updateRespuesta(data.getRespuestas(),ddjjss));
			//ddjjss.setFactorDeRiesgo(data.getFactorDeRiesgo());
			ddjjss.setFactorDeRiesgo(data.getFactorDeRiesgo());
			//updateFactordeRiesgo(data.getFactorDeRiesgo(), ddjjss);
			ddjjServiceApi.save(ddjjss);
			response.put("message", "Successful load");
			response.put("data",dj);
			response.put("success", true);
			return response;
			
		} catch (Exception e) {
			response.put("message", "" + e.getMessage());
			response.put("success", false);
			return response;
		}

	}
	public List<Respuesta> updateRespuesta (List<Respuesta> respuesta, Ddjj ddjj){
		for (int i = 0; i < respuesta.size(); i++) {
		    respuesta.get(i).setDdjj(ddjj);
		    respuesta.get(i).setAfirmativo(respuesta.get(i).getAfirmativo());
		    respuesta.get(i).setPregunta(respuesta.get(i).getPregunta());
		}
		
		
		return respuesta;
	}
	
	

	@PutMapping(value = "/update/{id}")

	public Map<String, Object> update(@PathVariable("id") Integer id, @RequestBody Ddjj data) {

		HashMap<String, Object> response = new HashMap<String, Object>();
		

		try {
			data.setIdDdjj(id);
			
			ddjjServiceApi.save(data);
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
			ddjjServiceApi.deleteById(id);
			;
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
