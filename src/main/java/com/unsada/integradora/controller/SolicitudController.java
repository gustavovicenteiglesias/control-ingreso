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
import com.unsada.integradora.model.entity.Ddjj;
import com.unsada.integradora.model.entity.EntidadAula;
import com.unsada.integradora.model.entity.Horario;
import com.unsada.integradora.model.entity.SesionPresencial;
import com.unsada.integradora.model.entity.Solicitud;
import com.unsada.integradora.service.interfaces.ActividadServiceApi;
import com.unsada.integradora.service.interfaces.CohorteHorarioServiceApi;
import com.unsada.integradora.service.interfaces.CohorteServiceApi;
import com.unsada.integradora.service.interfaces.DDjjServiceApi;
import com.unsada.integradora.service.interfaces.EntidadAulaServiceApi;
import com.unsada.integradora.service.interfaces.HorarioServiceApi;
import com.unsada.integradora.service.interfaces.SesionPresencialServiceApi;
import com.unsada.integradora.service.interfaces.SolicitudServiceApi;
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
	public Map<String, Object> create(@RequestBody Solicitud data, @PathVariable("idActividad") int idActividad,@PathVariable("idDdjj") int idDdjj, @PathVariable("idAula") int idAula, @PathVariable("idHorario") int idHorario, @RequestParam("fecha") Date date) {
		HashMap<String, Object> response = new HashMap<String, Object>();
		Optional<Ddjj> declaracion = ddjjServiceApi.findById(idDdjj);
		Optional<Horario> horario= horarioServiceApi.findById(idHorario);
		Optional<Actividad> actividad = actividadServiceApi.findById(idActividad);
		Optional<EntidadAula> aula = aulaServiceApi.findById(idAula);
		List<Cohorte> cohortes = cohorteServiceApi.findByActividad(actividad);
		if(aula.isPresent()){
			int capacidadDeAula = aula.get().getCapacidadConAforo();
			try {

				Cohorte cohorte = getCohorte(cohortes, date);
				cohorte.getCohorteHorarios().removeIf(i -> !i.getHorario().equals(horario.get()) && !i.getCohorte().getActividad().equals(actividad.get()));
				
				Optional<SesionPresencial> sesion = generarSesion(cohorte.getCohorteHorarios().get(0), aula, date);
				if (sesion.isPresent()){
					int nroSolicitudes = solicitudServiceApi.countBySesionPresencialAndFechaCarga(sesion.get(), date);
					int capacidadActual = capacidadDeAula - nroSolicitudes;
					if(capacidadActual >= 0){
						data.setSesionPresencial(sesion.get());
						data.setDdjj(declaracion.get());
						data.setFechaCarga(date);
						data.setQrAcceso(QrCreatorService.generateQrId());
						int qr=solicitudServiceApi.save(data).getId_solicitud();
						response.put("message", "Successful load");
						response.put("data", qr);
						response.put("success", true);
						response.put("nroSolicitudes:", nroSolicitudes ++);
						response.put("capacidadMaxima:", capacidadDeAula );
						response.put("disponible:", capacidadActual -- );
					}else{
						response.put("message", "El aula solicitada no tiene más espacio ");
						response.put("success", false);
						response.put("nroSolicitudes:", nroSolicitudes);
						response.put("capacidadActual:", capacidadActual  );
						response.put("capacidadMaxima:", capacidadDeAula );
						return response;
					}

					return response;
				}else{
					response.put("message", "Sesion no encontrada");
					response.put("success", false);
					return response;
					
				}
				
			} catch (NullPointerException e) {
				response.put("message", "failed");
				response.put("success", false);
				return response;
				
			}

			
		}
		response.put("message", "Successful load");
		
		response.put("success", true);
		return response;
	}
	private Optional<SesionPresencial> generarSesion(CohorteHorario cohorteHorario, Optional<EntidadAula> aula, Date date){
		try {
			System.out.println("La fecha para la busqueda es :" + date);
			List<SesionPresencial> sesiones = sesionPresencialServiceApi.findByCohorteHorario(cohorteHorario);
			Optional<SesionPresencial>  sesionn = sesiones.stream().filter(i -> !i.getFecha().equals(date)).findFirst();
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
				System.out.println("encontrado");

				return cohorte;
			}
		}
		return null;
	}

	@PutMapping(value = "/update/{idsolicitud}/{idEdificio}")

	public Map<String, Object> update(@PathVariable("idsolicitud") Integer idsolicitud, @PathVariable("idEdificio") int idEdificio) {

		HashMap<String, Object> response = new HashMap<String, Object>();
		Optional<Solicitud> solicitud = solicitudServiceApi.findById(idsolicitud);
		try {
			if(solicitud.get().getSesionPresencial().getEntidadAula().getEdificio().getIdEdificio() == idEdificio){
				solicitud.get().setPresente((byte) 1);
				solicitudServiceApi.save(solicitud.get());
				response.put("message", "Successful update");
				response.put("success", true);
			}else{
				response.put("message", "El codigo de acceso no corresponde al edificio");
				response.put("success", false);

			}
			return response;
		} catch (Exception e) {
			response.put("message", e.getMessage());
			response.put("success", false);
			return response;
		}

	}


	@DeleteMapping(value = "/delete/{id}")

	public Map<String, Object> delete(@PathVariable("id") Integer id) {

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
