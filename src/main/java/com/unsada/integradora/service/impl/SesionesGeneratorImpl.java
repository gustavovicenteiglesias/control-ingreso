package com.unsada.integradora.service.impl;

import com.unsada.integradora.model.entity.*;
import com.unsada.integradora.service.interfaces.CohorteHorarioServiceApi;
import com.unsada.integradora.service.interfaces.CohorteServiceApi;
import com.unsada.integradora.service.interfaces.SesionPresencialServiceApi;
import com.unsada.integradora.service.interfaces.SesionesGeneratorInterface;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class SesionesGeneratorImpl implements SesionesGeneratorInterface {
    @Autowired
    SesionPresencialServiceApi sesionPresencialServiceApi;
    @Autowired
    CohorteHorarioServiceApi cohorteHorarioServiceApi;

    public void crearSesiones(Cohorte cohorte, Horario data, EntidadAula aula) {
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
    public CohorteHorario crearCohorteHorario(Cohorte cohorte, Horario data){
        CohorteHorario cohorteHorario = new CohorteHorario();
        cohorteHorario.setCohorte(cohorte);
        cohorteHorario.setHorario(data);
        return cohorteHorarioServiceApi.save(cohorteHorario);
    }

    @Override
    public void actualizarSesiones(Cohorte cohorteOriginal, Cohorte cohorteActualizado) {
        System.out.println(cohorteOriginal);
        System.out.println(cohorteActualizado);
        Date ogFin = (Date) cohorteOriginal.getFechaFin();
        Date ogInicio = (Date) cohorteOriginal.getFechaInicio();
        Date nuevoInicio= (Date) cohorteActualizado.getFechaInicio();
        Date nuevoFin = (Date) cohorteActualizado.getFechaFin();
        List<LocalDate> fechas = nuevoInicio.toLocalDate().datesUntil(nuevoFin.toLocalDate()).collect(Collectors.toList());
        List<LocalDate> nuevasFechas = filterFechasPorDiasHorarios(cohorteOriginal, fechas);
        System.out.println("nuevas fechas");
        nuevasFechas.stream().forEach(i -> System.out.println(i));
       /* if(ogFin != nuevoFin){
            List<SesionPresencial> sesiones =cohorteOriginal.getCohorteHorarios().stream().distinct().flatMap(i -> i.getSesionPresencials().stream()).collect(Collectors.toList());
            sesiones.stream().forEach(sesion -> {
                System.out.println(sesion.getFecha());
                if(!nuevasFechas.contains((LocalDate) sesion.getFecha())){
                    System.out.println("hace falta sesion para la fecha: " + sesion.getFecha() + " id " + sesion.getIdSesionPresencial());
                }
            });
        }*/

    }
    public List<LocalDate> filterFechasPorDiasHorarios(Cohorte cohorte, List<LocalDate> fechas){
        List<String> dias = cohorte.getCohorteHorarios().stream().distinct().map(i -> i.getHorario().getDia()).collect(Collectors.toList());
        List<DayOfWeek> diasDeLaSemana = dias.stream().map(i -> CohorteServiceApi.maskDay(i)).collect(Collectors.toList());
        fechas = fechas.stream().filter(fecha -> diasDeLaSemana.contains(fecha.getDayOfWeek())).collect(Collectors.toList());
        return fechas;
    }
}
