package com.unsada.integradora.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
import com.unsada.integradora.model.Ddjj;
import com.unsada.integradora.model.EntidadAula;
import com.unsada.integradora.model.Horario;
import com.unsada.integradora.model.SesionPresencial;
import com.unsada.integradora.model.Solicitud;
import com.unsada.integradora.model.SolicitudPK;
import com.unsada.integradora.service.ActividadServiceApi;
import com.unsada.integradora.service.CohorteHorarioServiceApi;
import com.unsada.integradora.service.CohorteServiceApi;
import com.unsada.integradora.service.DDjjServiceApi;
import com.unsada.integradora.service.DDjjServiceImpl;
import com.unsada.integradora.service.EntidadAulaServiceApi;
import com.unsada.integradora.service.HorarioServiceApi;
import com.unsada.integradora.service.SesionPresencialServiceApi;
import com.unsada.integradora.service.SolicitudServiceApi;
import com.unsada.integradora.util.QrCreatorService;


@RestController
@RequestMapping(value = "/api/solicitud")
@CrossOrigin("*")
public class SolicitudController {
	@Autowired
	SolicitudServiceApi solicitudServiceApi;
	@Autowired
	DDjjServiceApi ddjjServiceApi;
	@Autowired
	ActividadServiceApi actividadServiceApi;
	@Autowired 
	HorarioServiceApi horarioServiceApi;
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
			List<Solicitud> claseData;
			claseData = (List<Solicitud>) solicitudServiceApi.findAll();
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
			Optional<Solicitud> clase = solicitudServiceApi.findById(id);
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

	@PostMapping(value = "/create-ddjj-actividad-aula-horario/{idDdjj}/{idActividad}/{idAula}/{idHorario}/")
	public ResponseEntity<String> create(@RequestBody Solicitud data, @PathVariable("idActividad") int idActividad,@PathVariable("idDdjj") int idDdjj, @PathVariable("idAula") int idAula, @PathVariable("idHorario") int idHorario, @RequestParam("fecha") Date date) {
		Optional<Ddjj> declaracion = ddjjServiceApi.findById(idDdjj);
		Optional<Horario> horario= horarioServiceApi.findById(idHorario);
		Optional<Actividad> actividad = actividadServiceApi.findById(idActividad);
		Optional<EntidadAula> aula = aulaServiceApi.findById(idAula);
		Optional<CohorteHorario> cohorteHorario = Optional.empty();
		List<Cohorte> cohortes = cohorteServiceApi.findByActividad(actividad);
		if(aula.isPresent()){
			if(aula.get().getCapacidadConAforo() > 0){
				try {
					Cohorte cohorte = getCohorte(cohortes, date);
					System.out.println("Cohorte is : " + cohorte.getIdCohorte());
					try{
						cohorteHorario =cohorteHorarioServiceApi.findByCohorteAndHorario(cohorte, horario.get());
						System.out.println("No hay cohortehorario definido para este horario");
					}catch(NoSuchElementException e){
						return new ResponseEntity<>("Horario de cohorte no encontrado", HttpStatus.NOT_FOUND);
					}
					Optional<SesionPresencial> sesion = generarSesion(cohorteHorario, aula, date);
					if (sesion.isPresent()){
						data.setSesionPresencial(sesion.get());
						data.setDdjj(declaracion.get());
						data.setFechaCarga(date);
						data.setQrAcceso(QrCreatorService.generateQrId());
						solicitudServiceApi.save(data);
					}else{
						return new ResponseEntity<>("Sesion no encontrada" , HttpStatus.NOT_FOUND);
					}
					
				} catch (NullPointerException e) {
					return new ResponseEntity<>("Error creando solicitud ", HttpStatus.INTERNAL_SERVER_ERROR);
				}

			}else{
				return new ResponseEntity<>("El aula solicitada no tiene más espacio ", HttpStatus.UNAUTHORIZED);

			}
		}
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}
	private Optional<SesionPresencial> generarSesion(Optional<CohorteHorario> cohorteHorario, Optional<EntidadAula> aula, Date date){
		try {
			System.out.println("La fecha para la busqueda es :" + date);
			System.out.println("El cohorte horario es:" + cohorteHorario.get().getIdCohorteHorario());
			System.out.println("El aula es:" + aula.get().getIdAula());
			int capacidadConAforo = aula.get().getCapacidadConAforo();
			aula.get().setCapacidadConAforo(capacidadConAforo - 1);
			Optional<SesionPresencial>  sesionn= sesionPresencialServiceApi.findByEntidadAulaAndCohorteHorarioAndFecha(aula.get(),cohorteHorario.get(), date);
			sesionn.get().setEntidadAula(aula.get());
			return sesionn;
		}
		catch(NoSuchElementException e){
			return Optional.empty();
		}
		

	}
	@GetMapping(value = "/find/uuid/{id}")
	public Map<String, Object> dataClase(@PathVariable("id") String id) {
		HashMap<String, Object> response = new HashMap<String, Object>();

		try {

			Optional<Solicitud> clase = solicitudServiceApi.findByQr(id);

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

	@PutMapping(value = "/update/{idsolicitud}/{idsesionpresencial}")

	public Map<String, Object> update(@PathVariable("idsolicitud") Integer idsolicitud,@PathVariable("idsesionpresencial") Integer idsesionpresencial,  @RequestBody Solicitud data) {

		HashMap<String, Object> response = new HashMap<String, Object>();
		Ddjj ddjjs=ddjjServiceApi.findById(1).get();
		SesionPresencial sesionPresencial=sesionPresencialServiceApi.findById(idsesionpresencial).get();

		try {
			data.setId_solicitud(idsolicitud);
			data.setDdjj(ddjjs);
			data.setFechaCarga(solicitudServiceApi.findById(idsolicitud).get().getFechaCarga());
			data.setQrAcceso(solicitudServiceApi.findById(idsolicitud).get().getQrAcceso());
			data.setSesionPresencial(sesionPresencial);
			
			solicitudServiceApi.save(data);
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
			solicitudServiceApi.deleteById(id);
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
