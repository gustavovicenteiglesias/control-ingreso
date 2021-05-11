package com.unsada.integradora.controller;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
//import java.lang.StackWalker.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unsada.integradora.model.Actividad;
import com.unsada.integradora.model.Cohorte;
import com.unsada.integradora.model.CohorteHorario;
import com.unsada.integradora.model.Horario;
import com.unsada.integradora.model.SesionPresencial;
import com.unsada.integradora.service.ActividadServiceApi;
import com.unsada.integradora.service.CohorteHorarioServiceApi;
import com.unsada.integradora.service.CohorteServiceApi;
import com.unsada.integradora.service.HorarioServiceApi;
import com.unsada.integradora.service.SesionPresencialServiceApi;

@RestController
@RequestMapping(value = "/api/horario")
@CrossOrigin("*")
public class HorarioController {
	@Autowired
	HorarioServiceApi horarioServiceApi;
	@Autowired
	ActividadServiceApi actividadServiceApi;
	@Autowired 
	CohorteServiceApi cohorteServiceApi;
	@Autowired 
	CohorteHorarioServiceApi cohorteHorarioServiceApi;
	@Autowired
	SesionPresencialServiceApi sesionPresencialServiceApi;

	@GetMapping(value = "/all")
	public Map<String, Object> listclase() {

		HashMap<String, Object> response = new HashMap<String, Object>();
		try {
			List<Horario> claseData;
			claseData = (List<Horario>) horarioServiceApi.findAll();
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
	@GetMapping(value = "/find-actividad-fecha/{idActividad}/")
	public Map<String, Object> dat(@PathVariable("idActividad") Integer idActividad, @RequestParam("fecha") Date fecha){
		HashMap<String, Object> response = new HashMap<String, Object>();
		Optional<Actividad> actividad = actividadServiceApi.findById(idActividad);
		try{
			Cohorte cohorte = getCohorte(actividad.get().getCohortes(), fecha);
			List<CohorteHorario> cohorteHorarios = cohorteHorarioServiceApi.findByCohorte(cohorte);
			List<Horario> horarios = new ArrayList<Horario>();
			for(CohorteHorario cohorteHorario : cohorteHorarios){
				horarios.add(cohorteHorario.getHorario());
			}
			response.put("message", "Successful load");
			response.put("data", horarios);
			response.put("success", true);
			return response;
		}catch(Exception e){
			response.put("message", "" + e.getMessage());
			response.put("success", false);
			return response;

		}
	}

	private Cohorte getCohorte(List<Cohorte> cohortes, Date date){
		for(Cohorte cohorte : cohortes){
			Date inicio = (Date) cohorte.getFechaInicio();
			Date fin = (Date) cohorte.getFechaFin();
			List<LocalDate> fechas = inicio.toLocalDate().datesUntil(fin.toLocalDate()).collect(Collectors.toList());
			if(fechas.contains(date.toLocalDate())){
				return cohorte;
			}
		}
		return null;
	}
	@GetMapping(value = "/find/{id}")
	public Map<String, Object> dataClase(@PathVariable("id") Integer id) {
		HashMap<String, Object> response = new HashMap<String, Object>();

		try {

			Optional<Horario> clase = horarioServiceApi.findById(id);

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

	@PostMapping(value = "/create-todos-cohortes/{idActividad}")
	public ResponseEntity<String> createForAll( @PathVariable("idActividad") int idActividad, @RequestBody Horario data) {
		Optional<Actividad> actividad = actividadServiceApi.findById(idActividad);
		try {
			List<Cohorte>  cohortes =cohorteServiceApi.findByActividad(actividad);
			if(!cohortes.isEmpty()){

				crearSesiones(cohortes, horarioServiceApi.save(data));
			}
			return new ResponseEntity<>("Save successful: Se encontraron " + cohortes.size() + " cohortes", HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("" + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@PostMapping(value = "/create-por-cohorte/{idActividad}/{idCohorte}")
	public ResponseEntity<String> createByCohorte( @PathVariable("idActividad") int idActividad,@PathVariable("idCohorte") int idCohorte, @RequestBody Horario data) {
		Optional<Actividad> actividad = actividadServiceApi.findById(idActividad);
		try {
			List<Cohorte>  cohorte =cohorteServiceApi.findByIdCohorteAndActividad(idCohorte, actividad.get());
			if(!cohorte.isEmpty()){

				crearSesiones(cohorte, horarioServiceApi.save(data));
			}
			return new ResponseEntity<>("Save successful: Se encontraron " + cohorte.size() + " cohortes", HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("" + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
/* 
En base a la fecha fin y de inicio, evalua los dias del horario a registrar. Para las fechas
entre inicio y fin que corerspondan al dia, se genera una sesion.
*/
	private void crearSesiones(List<Cohorte> cohortes, Horario data) {
		for(Cohorte cohorte : cohortes){
			List<CohorteHorario> cohorteHorario = cohorte.getCohorteHorarios();
			Date inicio = (Date) cohorte.getFechaInicio();
			Date fin = (Date) cohorte.getFechaFin();
			List<LocalDate> fechas = inicio.toLocalDate().datesUntil(fin.toLocalDate()).collect(Collectors.toList());
			System.out.println(fechas.size());
			for(LocalDate fecha : fechas){
				if(fecha.getDayOfWeek().equals(maskDay(data.getDia()))){
					SesionPresencial sesion = new SesionPresencial();
					if(!cohorteHorario.isEmpty()){
						sesion.setCohorteHorario(cohorteHorario.get(0));
						System.out.println("Cohorte existe pe");
					}else{
						sesion.setCohorteHorario(crearCohorteHorario(cohorte, data));
					}
					sesion.setFecha(Date.from(fecha.atStartOfDay(ZoneId.of("America/Argentina/Catamarca")).toInstant()));
					sesionPresencialServiceApi.save(sesion);
				}
			}			
		}
	}
	private CohorteHorario crearCohorteHorario(Cohorte cohorte, Horario data){
		CohorteHorario cohorteHorario = new CohorteHorario();
		cohorteHorario.setCohorte(cohorte);
		cohorteHorario.setHorario(data);
		return cohorteHorarioServiceApi.save(cohorteHorario);
	}

	public static DayOfWeek maskDay(String dia) {
		switch (dia.toUpperCase()) {
		case "LUNES":
						return DayOfWeek.MONDAY;

		case "MARTES":
						return DayOfWeek.TUESDAY ;

		case "MIERCOLES":
						return DayOfWeek.WEDNESDAY ;

		case "JUEVES":
						return DayOfWeek.THURSDAY;

		case "VIERNES":
						return DayOfWeek.FRIDAY ;

		case "SABADO":
						return DayOfWeek.SATURDAY;

		case "DOMINGO":
						return DayOfWeek.SUNDAY;
		default:
						return null;
		}
}



	@PutMapping(value = "/update/{id}")

	public Map<String, Object> update(@PathVariable("id") Integer id, @RequestBody Horario data) {

		HashMap<String, Object> response = new HashMap<String, Object>();

		try {
			data.setIdHorario(id);
			horarioServiceApi.save(data);
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
			horarioServiceApi.deleteById(id);
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
