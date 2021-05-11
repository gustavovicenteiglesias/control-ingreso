package com.unsada.integradora.controller;

import java.sql.Date;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unsada.integradora.model.Actividad;
import com.unsada.integradora.model.Cohorte;
import com.unsada.integradora.model.CohorteHorario;
import com.unsada.integradora.model.EntidadAula;
import com.unsada.integradora.model.Horario;
import com.unsada.integradora.model.Propuesta;
import com.unsada.integradora.model.SesionPresencial;
import com.unsada.integradora.service.ActividadServiceApi;
import com.unsada.integradora.service.CohorteHorarioServiceApi;
import com.unsada.integradora.service.CohorteServiceApi;
import com.unsada.integradora.service.EntidadAulaServiceApi;
import com.unsada.integradora.service.HorarioServiceApi;
import com.unsada.integradora.service.PropuestaServiceApi;
import com.unsada.integradora.service.SesionPresencialServiceApi;

@RestController
@RequestMapping(value = "/api/actividad")
@CrossOrigin("*")
public class ActividadController {
	@Autowired
	ActividadServiceApi actividadServiceApi;
	@Autowired
	@Qualifier("propuestaServiceApi")
	PropuestaServiceApi propuestaServiceApi ;
	@Autowired CohorteServiceApi cohorteServiceApi;
	@Autowired HorarioServiceApi horarioServiceApi;
	@Autowired EntidadAulaServiceApi aulaServiceApi;
	@Autowired CohorteHorarioServiceApi cohorteHorarioServiceApi;
	@Autowired SesionPresencialServiceApi sesionPresencialServiceApi;




	@GetMapping(value = "/all")
	public Map<String, Object> listclase() {

		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			List<Actividad> claseData;
			claseData = (List<Actividad>) actividadServiceApi.findAll();
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

	/*@GetMapping(value= "/find-por-fecha/")
	public Map<String, Object> actividadPorFecha(@RequestParam("fecha") Date fecha){
		HashMap<String, Object> response = new HashMap<String, Object>();
		try{
			Iterable<Cohorte> cohortes =cohorteServiceApi.findAll();
			List<Actividad> actividades = new ArrayList<Actividad>();
			for(Cohorte cohorte : cohortes){
				Date inicio = (Date) cohorte.getFechaInicio();
				Date fin = (Date) cohorte.getFechaFin();
				List<LocalDate> fechas = inicio.toLocalDate().datesUntil(fin.toLocalDate()).collect(Collectors.toList());
				if(fechas.contains(fecha.toLocalDate())){
					if(!actividades.contains(cohorte.getActividad())){
						actividades.add(cohorte.getActividad());
					}
				}
			}
			response.put("data", actividades);
			return response;
		}catch(Exception e){
			response.put("message", "" + e.getMessage());
			response.put("success", false);
			return response;
			}
		}*/
	@GetMapping(value = "/find/propuesta/{id}")
	public Map<String, Object> listclase1(@PathVariable("id") Integer id) {

		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			List<Actividad> claseData;
			claseData = (List<Actividad>) actividadServiceApi.findByPropuesta(id);
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

			Optional<Actividad> clase = actividadServiceApi.findById(id);

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
	public ResponseEntity<String> create(@RequestBody Actividad data) {
		try {
			actividadServiceApi.save(data);
			return new ResponseEntity<>("Save successful ", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("" + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@GetMapping(value ="/cohortes/{idActividad}")
	public Map<String, Object> listCohortes(@PathVariable("idActividad") int idActividad){
		HashMap<String, Object> response = new HashMap<String, Object>();
		Optional<Actividad> actividad = actividadServiceApi.findById(idActividad);
		try{
		  List<Cohorte> cohortes = cohorteServiceApi.findByActividad(actividad);
			response.put("data", cohortes );
			response.put("success", true);
			return response;
		}catch(Exception e){
			response.put("message", e.getMessage());
			response.put("success ", false);
		}
		return response;


	}
	//Si ya existe el registro de fecha sobreescribe el aula, sino crea uno nuevo 
	/*@PostMapping(value="/asignar-aula-por-cohorte-horario-fecha/{idAula}/{idCohorte}/{idHorario}/")
	public ResponseEntity<String> asignarAulaPorFecha( @PathVariable("idAula") int idAula, @PathVariable("idCohorte") int idCohorte, @PathVariable("idHorario") int idHorario, @RequestParam("fecha") Date date ) {
		Optional<Horario> horario = horarioServiceApi.findById(idHorario);
		Optional<EntidadAula> aula = aulaServiceApi.findById(idAula);
		Optional<Cohorte> cohorte = cohorteServiceApi.findById(idCohorte);
		try {
			Optional<CohorteHorario> cohorteHorario = cohorteHorarioServiceApi.findByCohorteAndHorario(cohorte.get(), horario.get());
			Optional<SesionPresencial> sesion = sesionPresencialServiceApi.findByCohorteHorarioAndFecha(cohorteHorario.get(), date);
			if(sesion.isPresent()){
				sesion.get().setEntidadAula(aula.get());
				sesionPresencialServiceApi.save(sesion.get());
			}else{
				SesionPresencial sesionPresencial = new SesionPresencial();
				sesionPresencial.setCohorteHorario(cohorteHorario.get());
				sesionPresencial.setEntidadAula(aula.get());
				sesionPresencial.setFecha(date);
				sesionPresencialServiceApi.save(sesionPresencial);
			}
			
			return new ResponseEntity<>("Save successful " + date, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("" + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
*/

	/*@PostMapping(value="/asignar-aula-por-cohorte-horario/{idAula}/{idCohorte}/{idHorario}")
	public ResponseEntity<String> asignarAula( @PathVariable("idAula") int idAula, @PathVariable("idCohorte") int idCohorte, @PathVariable("idHorario") int idHorario ) {
		Optional<Horario> horario = horarioServiceApi.findById(idHorario);
		Optional<EntidadAula> aula = aulaServiceApi.findById(idAula);
		Optional<Cohorte> cohorte = cohorteServiceApi.findById(idCohorte);
		try {
			Optional<CohorteHorario> cohorteHorario = cohorteHorarioServiceApi.findByCohorteAndHorario(cohorte.get(), horario.get());
			List<SesionPresencial> sesiones = sesionPresencialServiceApi.findByCohorteHorario(cohorteHorario.get());
			for (SesionPresencial sesionPresencial : sesiones) {
				sesionPresencial.setEntidadAula(aula.get());
				sesionPresencialServiceApi.save(sesionPresencial);
			}
			
			return new ResponseEntity<>("Save successful ", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("" + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}*/

	@PutMapping(value = "/update/{id}")
	public Map<String, Object> update(@PathVariable("id") Integer id, @RequestBody Actividad data) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		try {
			data.setIdActividad(id);
			actividadServiceApi.save(data);
			response.put("message", "Successful update");
			response.put("success", true);
			return response;
		} catch (Exception e) {
			response.put("message", e.getMessage());
			response.put("success", false);
			return response;
		}

	}
	@PutMapping(value = "/update/propuesta/{id}/{idPropuesta}")

	public Map<String, Object> update1(@PathVariable("id") Integer id,@PathVariable("idPropuesta") Integer idPropuesta, @RequestBody Actividad data) {

		HashMap<String, Object> response = new HashMap<String, Object>();
		Propuesta propuesta=propuestaServiceApi.findById(idPropuesta).get();
		System.out.print(propuesta);

		try {
			data.setIdActividad(id);
			data.setPropuesta(propuesta);
			actividadServiceApi.save(data);
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
			actividadServiceApi.deleteById(id);
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
