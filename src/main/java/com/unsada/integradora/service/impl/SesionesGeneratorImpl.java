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
import java.sql.SQLOutput;
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
    @Autowired
    CohorteServiceApi cohorteServiceApi;

    @Override
    public void crearSesiones(Cohorte cohorte, Horario data, EntidadAula aula) {
        Date inicio = (Date) cohorte.getFechaInicio();
        Date fin = (Date) cohorte.getFechaFin();
        System.out.println(cohorte);
        System.out.println(data);
        List<LocalDate> fechasEntreIniciofin = inicio.toLocalDate().datesUntil(fin.toLocalDate()).collect(Collectors.toList());
        for(LocalDate fecha : fechasEntreIniciofin){
            System.out.println(data.getDia());
            if(fecha.getDayOfWeek().equals(CohorteServiceApi.maskDay(data.getDia()))){
                SesionPresencial sesion = new SesionPresencial();
                CohorteHorario cohorteHorario = findCohorteHorario(cohorte, data);
                if(cohorteHorario == null){
                    crearCohorteHorario(cohorte, data);
                    cohorteHorario = cohorte.getCohorteHorarios().get(0);

                }
                sesion.setCohorteHorario(cohorteHorario);
                sesion.setFecha(Date.from(fecha.atStartOfDay(ZoneId.of("America/Argentina/Catamarca")).toInstant()));
                if(aula != null){	sesion.setEntidadAula(aula);}
                sesionPresencialServiceApi.save(sesion);
            }
        }

    }


    @Override
    public void actualizarSesiones(Cohorte cohorteOriginal, Cohorte cohorteActualizado) {
        System.out.println(cohorteActualizado);
        System.out.println(cohorteOriginal);
        Date ogFin = (Date) cohorteOriginal.getFechaFin();
        Date nuevoInicio= (Date) cohorteActualizado.getFechaInicio();
        Date nuevoFin = (Date) cohorteActualizado.getFechaFin();
        System.out.println("----------");

        List<LocalDate> fechas = nuevoInicio.toLocalDate().datesUntil(nuevoFin.toLocalDate()).collect(Collectors.toList());
        if(ogFin.toLocalDate().isAfter(nuevoFin.toLocalDate())){
            List<LocalDate> fechasSesionesParaEliminar = nuevoFin.toLocalDate().datesUntil(ogFin.toLocalDate()).collect(Collectors.toList());
            borrarSesionesPresencialNuevoFin(cohorteOriginal, fechasSesionesParaEliminar);
            fechasSesionesParaEliminar.stream().forEach(i-> System.out.println(i));

        }

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
                   CohorteHorario ch = findCohorteHorario(cohorteOriginal, horario);
                   sesion.setFecha(Date.valueOf(fecha));
                   sesion.setCohorteHorario(ch);
                   sesionPresencialServiceApi.save(sesion);
                }
            });
        });

    }
    public void borrarSesionesPresencialNuevoFin(Cohorte cohorte, List<LocalDate> fechas){
        List<SesionPresencial> sesiones = findSesiones(cohorte);
       sesiones.forEach(sesion ->{
            if(fechas.contains(sesion.getFecha().toInstant().atZone(ZoneId.of("America/Argentina/Catamarca")).toLocalDate())){
               sesionPresencialServiceApi.deleteById(sesion.getIdSesionPresencial());
            }
        });
    }
    //Trae todas las fechas sin sesion existentes
    public List<LocalDate> getNuevasFechas(Date originalFin, Date nuevoFin, List<LocalDate> nuevasFechas, Cohorte cohorteOriginal) {
        if (originalFin != nuevoFin) {
            List<SesionPresencial> sesiones = findSesiones(cohorteOriginal);

            sesiones.stream().forEach(sesion -> {
                LocalDate sesionToLocal = sesion.getFecha().toInstant().atZone(ZoneId.of("America/Argentina/Catamarca")).toLocalDate();
                if (nuevasFechas.contains(sesionToLocal)) {
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
        List<DayOfWeek> diasDeLaSemana = dias.stream().map(i -> CohorteServiceApi.maskDay(i)).collect(Collectors.toList());
        fechas = fechas.stream().filter(fecha -> diasDeLaSemana.contains(fecha.getDayOfWeek())).collect(Collectors.toList());
        return fechas;
    }


    //Encuentra el cohorte horario para un horario especifico
    public CohorteHorario findCohorteHorario(Cohorte cohorte, Horario horario){
        List<CohorteHorario> cohorteHorario = cohorte.getCohorteHorarios();

        cohorteHorario = cohorteHorario.stream()
                .filter(i -> i.getCohorte().equals(cohorte))
                .filter(h -> h.getHorario().equals(horario))
                .collect(Collectors.toList());
        try{
            return cohorteHorario.get(0);

        }catch (Exception e){
            return null;
        }


    }

    public void crearCohorteHorario(Cohorte cohorte, Horario data){
        CohorteHorario cohorteHorario = new CohorteHorario();
        cohorteHorario.setCohorte(cohorte);
        cohorteHorario.setHorario(data);
        cohorte.getCohorteHorarios().add(cohorteHorario);
        cohorteServiceApi.save(cohorte);
    }

    public List<SesionPresencial> findSesiones(Cohorte cohorte){
        List<SesionPresencial> sesiones = cohorte.getCohorteHorarios()
                .stream()
                .distinct()
                .flatMap(i -> i.getSesionPresencials().stream())
                .collect(Collectors.toList());
        return sesiones;
    }
}
