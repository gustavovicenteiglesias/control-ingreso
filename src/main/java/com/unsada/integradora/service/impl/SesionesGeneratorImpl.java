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
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class SesionesGeneratorImpl implements SesionesGeneratorInterface {
    @Autowired
    SesionPresencialServiceApi sesionPresencialServiceApi;
    @Autowired
    CohorteHorarioServiceApi cohorteHorarioServiceApi;

    @Override
    public void crearSesiones(Cohorte cohorte, Horario data, EntidadAula aula) {
        Date inicio = (Date) cohorte.getFechaInicio();
        Date fin = (Date) cohorte.getFechaFin();
        List<LocalDate> fechasEntreIniciofin = inicio.toLocalDate().datesUntil(fin.toLocalDate()).collect(Collectors.toList());
        for(LocalDate fecha : fechasEntreIniciofin){
            if(fecha.getDayOfWeek().equals(CohorteServiceApi.maskDay(data.getDia()))){
                SesionPresencial sesion = new SesionPresencial();
                CohorteHorario cohorteHorario = findCohorteHorario(cohorte, data);
                if(cohorteHorario != null){
                    sesion.setCohorteHorario(cohorteHorario);
                }else{
                    sesion.setCohorteHorario(crearCohorteHorario(cohorte, data));
                }
                sesion.setFecha(Date.from(fecha.atStartOfDay(ZoneId.of("America/Argentina/Catamarca")).toInstant()));
                if(aula != null){	sesion.setEntidadAula(aula);}
                sesionPresencialServiceApi.save(sesion);
            }
        }

    }


    @Override
    public void actualizarSesiones(Cohorte cohorteOriginal, Cohorte cohorteActualizado) {
        Date ogFin = (Date) cohorteOriginal.getFechaFin();
        Date nuevoInicio= (Date) cohorteActualizado.getFechaInicio();
        Date nuevoFin = (Date) cohorteActualizado.getFechaFin();
        List<LocalDate> fechas = nuevoInicio.toLocalDate().datesUntil(nuevoFin.toLocalDate()).collect(Collectors.toList());
        List<LocalDate> nuevasFechas = getNuevasFechas(ogFin
                ,nuevoFin
                , filterFechasPorDiasHorarios(cohorteOriginal, fechas)
                ,cohorteOriginal);

        nuevasFechas.forEach(System.out::println);
        cohorteOriginal.getCohorteHorarios().stream().forEach(i ->{
            Horario horario = i.getHorario();
            nuevasFechas.forEach(fecha ->{
                if(fecha.getDayOfWeek().equals(CohorteServiceApi.maskDay(horario.getDia()))){
                   SesionPresencial sesion = new SesionPresencial();
                   sesion.setFecha(Date.valueOf(fecha));
                   sesion.setCohorteHorario(findCohorteHorario(cohorteOriginal, horario));
                   sesionPresencialServiceApi.save(sesion);
                }
            });
        });

    }
    //Trae todas las fechas sin sesion existentes
    public List<LocalDate> getNuevasFechas(Date originalFin, Date nuevoFin, List<LocalDate> nuevasFechas, Cohorte cohorteOriginal) {
        if (originalFin != nuevoFin) {
            List<SesionPresencial> sesiones = cohorteOriginal.getCohorteHorarios()
                    .stream()
                    .distinct()
                    .flatMap(i -> i.getSesionPresencials().stream())
                    .collect(Collectors.toList());

            sesiones.stream().forEach(sesion -> {
                LocalDate sesionToLocal = sesion.getFecha().toInstant().atZone(ZoneId.of("America/Argentina/Catamarca")).toLocalDate();
                System.out.println(sesionToLocal);
                if (nuevasFechas.contains(sesionToLocal)) {
                    System.out.println("no hace falta sesion para la fecha: " + sesion.getFecha() + " id " + sesion.getIdSesionPresencial());
                    nuevasFechas.remove(sesionToLocal);
                }
            });
            return nuevasFechas;
        }
        return null;

    }
    //Dado una lista de dias y un cohorte devuelve los dias que correspondan al dia asignado para los horarios
    public List<LocalDate> filterFechasPorDiasHorarios(Cohorte cohorteOriginal, List<LocalDate> fechas){
        List<String> dias = cohorteOriginal.getCohorteHorarios().stream().distinct().map(i -> i.getHorario().getDia()).collect(Collectors.toList());
        List<DayOfWeek> diasDeLaSemana = dias.stream().map(CohorteServiceApi::maskDay).collect(Collectors.toList());
        fechas = fechas.stream().filter(fecha -> diasDeLaSemana.contains(fecha.getDayOfWeek())).collect(Collectors.toList());
        return fechas;
    }

   public void crearSesionesInRange(List<LocalDate> fechas, CohorteHorario cohorteHorario) {
        fechas.forEach(fecha -> {
            SesionPresencial sesionPresencial = new SesionPresencial();
            sesionPresencial.setFecha(Date.valueOf(fecha));
            sesionPresencial.setCohorteHorario(cohorteHorario);
        });
    }


    public CohorteHorario findCohorteHorario(Cohorte cohorte, Horario horario){
        List<CohorteHorario> cohorteHorario = cohorte.getCohorteHorarios();
        if(!cohorteHorario.isEmpty()){
            cohorteHorario = cohorteHorario.stream()
                    .filter(i -> i.getCohorte().equals(cohorte))
                    .filter(h -> h.getHorario().equals(horario))
                    .collect(Collectors.toList());
            return cohorteHorario.get(0);
        }
        System.out.println("no encontrado el cohorte horario");
        return null;
    }

    public CohorteHorario crearCohorteHorario(Cohorte cohorte, Horario data){
        CohorteHorario cohorteHorario = new CohorteHorario();
        cohorteHorario.setCohorte(cohorte);
        cohorteHorario.setHorario(data);
        return cohorteHorarioServiceApi.save(cohorteHorario);
    }
}
