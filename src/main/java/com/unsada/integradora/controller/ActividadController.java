package com.unsada.integradora.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.unsada.integradora.model.dto.ActividadDependenciasDTO;
import com.unsada.integradora.model.mapper.interfaces.ActividadDependenciasMapper;
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

import com.unsada.integradora.model.entity.Actividad;
import com.unsada.integradora.model.entity.Cohorte;
import com.unsada.integradora.model.entity.CohorteHorario;
import com.unsada.integradora.model.entity.EntidadAula;
import com.unsada.integradora.model.entity.Horario;
import com.unsada.integradora.model.entity.Propuesta;
import com.unsada.integradora.model.entity.SesionPresencial;
import com.unsada.integradora.service.interfaces.ActividadServiceApi;
import com.unsada.integradora.service.interfaces.CohorteHorarioServiceApi;
import com.unsada.integradora.service.interfaces.CohorteServiceApi;
import com.unsada.integradora.service.interfaces.EntidadAulaServiceApi;
import com.unsada.integradora.service.interfaces.HorarioServiceApi;
import com.unsada.integradora.service.interfaces.PropuestaServiceApi;
import com.unsada.integradora.service.interfaces.SesionPresencialServiceApi;

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
	@Autowired
	ActividadDependenciasMapper actividadDependenciasMapper;




	@GetMapping(value = "/all")
	public Map<String, Object> listclase() {

		HashMap<String, Object> response = new HashMap<String, Object>();

		//Convierto las actividades a DTO, los dto tienen los datos extra
		try {
			List<Actividad> claseData;
			claseData = (List<Actividad>) actividadServiceApi.findAll();
			List<ActividadDependenciasDTO> allActividades = claseData.stream()
					.map(i -> actividadDependenciasMapper.toDTO(i))
					.collect(Collectors.toList());
			response.put("message", "Successful load");
			response.put("data", allActividades);
			response.put("success", true);
			return response;

		} catch (Exception e) {
			response.put("message", e.getMessage());
			response.put("success ", false);
			return response;
		}

	}

	@GetMapping(value= "/find-por-fecha/")
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
		}
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
	@GetMapping(value = "/find/por-propuesta-en-rango-actual/{id}")
	public Map<String, Object> listPropsPorCohorte(@PathVariable("id") Integer id) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		Date sqlDate = new Date(new java.util.Date().getTime());
		List<Actividad> actividadesPorPropuesta = (List<Actividad>) actividadServiceApi.findByPropuesta(id);

		List<Cohorte> cohortesInRange = CohorteServiceApi.getCohortesByDate(cohorteServiceApi.findAll(), sqlDate);
		actividadesPorPropuesta.removeIf( i -> !contieneAlguno(i.getCohortes(), cohortesInRange));
		try {
			if(actividadesPorPropuesta.isEmpty()){
				response.put("message", "No hay actividades disponibles en esta fecha para la propuesta");
				response.put("success", false);
			}else{
				response.put("message", "Success");
				response.put("success", true);
			}
			response.put("data", actividadesPorPropuesta);
			return response;

		} catch (Exception e) {
			response.put("message", e.getMessage());
			response.put("success ", false);
			return response;
		}

	}
	private boolean contieneAlguno(List<Cohorte> cohortes, List<Cohorte> cohortesInRange) {
		for (Cohorte cohorte : cohortes) {
			if(cohortesInRange.contains(cohorte)){
				return true;
			}
		}
		return false;
	}

	@GetMapping(value = "/find/{id}")
	public Map<String, Object> dataClase(@PathVariable("id") Integer id) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		try {

			Optional<Actividad> clase = actividadServiceApi.findById(id);

			if (clase.isPresent()) {
				response.put("message", "Successful load");
				response.put("data", actividadDependenciasMapper.toDTO(clase.get()));
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

	@PostMapping(value = "/create-por-propuesta/{idPropuesta}")
	public ResponseEntity<String> create(@RequestBody Actividad data, @PathVariable ("idPropuesta") int idPropuesta) {
		Optional<Propuesta> propuesta = propuestaServiceApi.findById(idPropuesta);
		System.out.println(propuesta.get().getIdPropuesta());
			data.setPropuesta(propuesta.get());
			try{
				Actividad actividad = this.crearActividad(data);
				System.out.println(actividad);
				return new ResponseEntity<>("Save successful ", HttpStatus.OK);

			}catch (Exception e) {
				return new ResponseEntity<>("El nombre de la actividad ya existe ", HttpStatus.INTERNAL_SERVER_ERROR);
			}


	}
	private Actividad crearActividad(Actividad actividad) throws Exception {
		for (Actividad i : actividadServiceApi.findAll()) {
			if (i.getNombre().equals(actividad.getNombre()))
				throw new Exception("Nombre ya existe para esta actividad");
		}
		return actividad;
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
	@PostMapping(value="/asignar-aula-por-cohorte-horario-fecha/{idAula}/{idCohorte}/{idHorario}/")
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


	@PostMapping(value="/asignar-aula-por-cohorte-horario/{idAula}/{idCohorte}/{idHorario}")
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

	}

	@PutMapping(value = "/update/{id}")
	public Map<String, Object> update(@PathVariable("id") Integer id, @RequestBody Actividad data) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		Optional<Actividad> actividad = actividadServiceApi.findById(id);
		try {
			actividad.get().setNombre(data.getNombre());
			actividadServiceApi.save((Actividad) actividad.get());
			System.out.println("updated mafa");
			System.out.println(actividad.get().toString());
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
