package com.unsada.integradora.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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

import com.unsada.integradora.model.entity.Actividad;
import com.unsada.integradora.model.entity.Cohorte;
import com.unsada.integradora.model.entity.CohorteHorario;
import com.unsada.integradora.model.entity.EntidadAula;
import com.unsada.integradora.model.entity.Horario;
import com.unsada.integradora.model.entity.SesionPresencial;
import com.unsada.integradora.service.interfaces.ActividadServiceApi;
import com.unsada.integradora.service.interfaces.CohorteHorarioServiceApi;
import com.unsada.integradora.service.interfaces.CohorteServiceApi;
import com.unsada.integradora.service.interfaces.EntidadAulaServiceApi;
import com.unsada.integradora.service.interfaces.HorarioServiceApi;
import com.unsada.integradora.service.interfaces.SesionPresencialServiceApi;

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
	@Autowired
	EntidadAulaServiceApi aulaServiceApi;

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
	@GetMapping(value = "/find-por-actividad-fecha/{idActividad}/")
	public Map<String, Object> dat(@PathVariable("idActividad") Integer idActividad, @RequestParam("fecha") Date fecha){
		HashMap<String, Object> response = new HashMap<String, Object>();
		Optional<Actividad> actividad = actividadServiceApi.findById(idActividad);
		try{
			Cohorte cohorte = CohorteServiceApi.getCohorte(actividad.get().getCohortes(), fecha);
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

	
	@GetMapping(value = "/find/sede_actrividad/{idActividad}/{idSede}")
	public Map<String, Object> dataClase1(@PathVariable("idActividad") Integer idActividad,@PathVariable("idSede") Integer idSede ) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		try {
			List<Horario> claseData;
			claseData = (List<Horario>) horarioServiceApi.findBySedeActividad(idActividad, idSede);
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

	@PostMapping(value = "/create-sesiones-vacias/{idActividad}/{idCohorte}")
	public ResponseEntity<String> createByCohorte( @PathVariable("idActividad") int idActividad, @PathVariable("idCohorte") int idCohorte, @RequestBody Horario data) {
		Optional<Actividad> actividad = actividadServiceApi.findById(idActividad);
		Optional<Cohorte> cohorte = cohorteServiceApi.findById(idCohorte);
		try {
			if(cohorte.isPresent()){
				crearSesiones(cohorte.get(), horarioServiceApi.save(data), null);
			}
			return new ResponseEntity<>("Save successful: Se encontraron "  + " cohortes", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("" + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(value = "/create-por-cohorte/{idActividad}/{idAula}/{idCohorte}")
	public ResponseEntity<String> createByCohorte( @PathVariable("idActividad") int idActividad,  @PathVariable("idAula") int idAula,@PathVariable("idCohorte") int idCohorte, @RequestBody Horario data) {
		Optional<Actividad> actividad = actividadServiceApi.findById(idActividad);
		Optional<EntidadAula> aula = aulaServiceApi.findById(idAula);
		Optional<Cohorte> cohorte = cohorteServiceApi.findById(idCohorte);
		try {
			if(cohorte.isPresent()){
				crearSesiones(cohorte.get(), horarioServiceApi.save(data), aula.get());
			}
			return new ResponseEntity<>("Save successful: Se encontraron "  + " cohortes", HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("" + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
    /*
    En base a la fecha fin y de inicio, evalua los dias del horario a registrar. Para las fechas
    entre inicio y fin que corerspondan al dia, se genera una sesion.
    */
	private void crearSesiones(Cohorte cohorte, Horario data, EntidadAula aula) {
			List<CohorteHorario> cohorteHorario = cohorte.getCohorteHorarios();
			Date inicio = (Date) cohorte.getFechaInicio();
			Date fin = (Date) cohorte.getFechaFin();
			List<LocalDate> fechas = inicio.toLocalDate().datesUntil(fin.toLocalDate()).collect(Collectors.toList());
			for(LocalDate fecha : fechas){
				if(fecha.getDayOfWeek().equals(CohorteServiceApi.maskDay(data.getDia()))){
					SesionPresencial sesion = new SesionPresencial();
					if(!cohorteHorario.isEmpty()){
						try{
							cohorteHorario.stream().filter(i -> i.getCohorte().equals(cohorte));
							sesion.setCohorteHorario(cohorteHorario.get(0));
							
						}catch(Exception e){
							System.out.println("no encontrado el cohorte horario");
						}
						System.out.println("Cohorte existe");
					}else{
						sesion.setCohorteHorario(crearCohorteHorario(cohorte, data));
					}
					sesion.setFecha(Date.from(fecha.atStartOfDay(ZoneId.of("America/Argentina/Catamarca")).toInstant()));
					if(aula != null){	sesion.setEntidadAula(aula);}
					sesionPresencialServiceApi.save(sesion);
				}
			}			
		
	}
	private CohorteHorario crearCohorteHorario(Cohorte cohorte, Horario data){
		CohorteHorario cohorteHorario = new CohorteHorario();
		cohorteHorario.setCohorte(cohorte);
		cohorteHorario.setHorario(data);
		return cohorteHorarioServiceApi.save(cohorteHorario);
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
