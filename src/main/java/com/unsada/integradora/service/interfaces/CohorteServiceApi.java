package com.unsada.integradora.service.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.unsada.integradora.model.entity.Actividad;
import com.unsada.integradora.model.entity.Cohorte;

public interface CohorteServiceApi extends CrudRepository<Cohorte, Integer> {

  public List<Cohorte> findAll();
  public List<Cohorte> findByActividad(Optional<Actividad> actividad);
  public List<Cohorte> findByIdCohorteAndActividad(int idCohorte, Actividad actividad);
  public static Cohorte getCohorte(List<Cohorte> cohortes, Date date){
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
  public static List<Cohorte> getCohortesByDate(List<Cohorte> todos, Date date) {
    List<Cohorte> cohortesInRange = new ArrayList<Cohorte>();
    for(Cohorte cohorte : todos ){
			Date inicio = (Date) cohorte.getFechaInicio();
			Date fin = (Date) cohorte.getFechaFin();
			List<LocalDate> fechas = inicio.toLocalDate().datesUntil(fin.toLocalDate()).collect(Collectors.toList());
			if(fechas.contains(date.toLocalDate())){
				cohortesInRange.add(cohorte);
			}
		}
		return cohortesInRange;
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
  

  @Query(value= "SELECT * FROM cohorte WHERE id_actividad=?", nativeQuery = true)
   Iterable<Cohorte> findByActivity (Integer id_actividad);

}
