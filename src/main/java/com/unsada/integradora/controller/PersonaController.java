package com.unsada.integradora.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import com.unsada.integradora.core.reportes.impl.ReportePersonaImpl;
import com.unsada.integradora.model.entity.Ddjj;
import com.unsada.integradora.model.entity.Persona;
import com.unsada.integradora.model.mapper.interfaces.SolicitudActividadMapper;
import com.unsada.integradora.service.impl.PersonaServiceImpl;
import com.unsada.integradora.util.EvalTieneDDJJ;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unsada.integradora.service.interfaces.PersonaServiceApi;
import com.unsada.integradora.service.interfaces.SolicitudServiceApi;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/persona")
@CrossOrigin("*")
public class PersonaController {
	@Autowired
	PersonaServiceImpl personaServiceApi;
	@Autowired
	SolicitudServiceApi solicitudServiceApi;
	@Autowired
	SolicitudActividadMapper solicitudActividadMapper;
	@Autowired
	ReportePersonaImpl reportePersona;
	@Value("${vars.duracion-ddjj}")
	private int duracion;
	@Autowired
	EvalTieneDDJJ eval;

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

		try {
			response.put("message", "Success");
			response.put("personas", personaServiceApi.solicitudesContactos(fechainicio,fechafin,idPersona));
			response.put("success", true);
			return response;

		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", e.getMessage());
			response.put("success ", false);
			return response;
		}
	}

	@GetMapping(value = "/solicitudes-contacto-pdf/{fechainicio}/{fechafin}/{idPersona}")
	public ResponseEntity<ByteArrayResource> workbook(@PathVariable("fechainicio") Date fechainicio, @PathVariable("fechafin") Date fechafin, @PathVariable("idPersona") int idPersona) throws IllegalAccessException, IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try{
			SXSSFWorkbook workbook = reportePersona.generarReportePersonasEnContacto(personaServiceApi.solicitudesContactos(fechainicio,fechafin,idPersona), "personas en contacto");
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "force-download"));
			header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+generarTitulo(idPersona, fechainicio, fechafin));
			workbook.write(stream);
			return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()),
					header, HttpStatus.CREATED);
		}catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private String generarTitulo(int idPersona, Date inicio, Date fin){
		Optional<Persona> persona = personaServiceApi.findById(idPersona);
		try{
			return "Contactos_estrechos_" + persona.get().getNombre() + "_entre_" + inicio+ "_y_"+ fin + ".xlsx";
		}catch (Exception e){
			e.printStackTrace();
			return "";
		}
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
			LocalDate date = LocalDate.now();
			Ddjj ddjj = eval.getDdjj(clase.get(), Date.valueOf(date));
			if (clase.isPresent()) {
				response.put("message", "Successful load");
				response.put("data", clase);
				if(ddjj != null){
					response.put("ddjj", ddjj);
					response.put("tieneDdjj", true);
				}else{
					response.put("tieneDdjj", false);
				}
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
			System.out.println(e);
			response.put("success", false);
			return response;
		}
	}
	@GetMapping(value = "/find/dni/{id}")
	public Map<String, Object> dataClase(@PathVariable("id") String id) {
		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			Optional<Persona> clase = personaServiceApi.findByDni(id);
			LocalDate date = LocalDate.now();
			Ddjj ddjj = eval.getDdjj(clase.get(), Date.valueOf(date));

			if (clase.isPresent()) {
				response.put("message", "Successful load");
				response.put("data", clase);
				if(ddjj != null){
					response.put("ddjj", ddjj);
					response.put("tieneDdjj", true);
				}else{
					response.put("tieneDdjj", false);
				}
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
			System.out.println(e);
			response.put("success", false);
			return response;
		}
	}

	@PostMapping(value = "/create")
	public Map<String, Object> create(@Valid @RequestBody Persona data) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		try {
			int id_persona=personaServiceApi.save(data).getIdPersona();
			response.put("message", "Successful load");
			response.put("data", id_persona);
			response.put("success", true);
			return response;
		} catch (Exception e) {
			response.put("message", "" + e.getMessage());
			System.out.println(e);
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
