package com.unsada.integradora.util;

import com.unsada.integradora.model.entity.*;
import com.unsada.integradora.service.interfaces.SesionPresencialServiceApi;
import com.unsada.integradora.service.interfaces.SolicitudServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SolicitudCreatorImpl implements  SolicitudCreator{

    @Autowired
    SesionPresencialServiceApi sesionPresencialApi;

    @Autowired
    SolicitudServiceApi solicitudServiceApi;

    public Optional<SesionPresencial> generarSesion(CohorteHorario cohorteHorario, EntidadAula aula, Date date) {
        try {
            System.out.println("La fecha para la busqueda es :" + date);
            List<SesionPresencial> sesiones = sesionPresencialApi.findByCohorteHorario(cohorteHorario);
            Optional<SesionPresencial> sesionn = sesiones.stream().filter(i -> !i.getFecha().equals(date) && i.getEntidadAula() != aula).findFirst();
            return sesionn;
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }


    public Cohorte getCohorte(List<Cohorte> cohortes, Date date){
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


    @Override
    public int evalCapacidad(SesionPresencial sesionPresencial, Date date, EntidadAula aula) {
        int nroSolicitudes = solicitudServiceApi.countBySesionPresencialAndFechaCarga(sesionPresencial, date);
        int capacidadActual = aula.getCapacidadConAforo() - nroSolicitudes;
        return capacidadActual;
    }

    @Override
    public int getNumeroSolicitudes(SesionPresencial sesion, Date date) {
        return (solicitudServiceApi.countBySesionPresencialAndFechaCarga(sesion, date) + 1 );
    }



    @Override
    public Solicitud crearSolicitud(Ddjj ddjj, Horario horario, Actividad actividad, EntidadAula aula, Date date) throws Exception {

        Cohorte cohorte = getCohorte(actividad.getCohortes(), date);
        if (cohorte == null) throw new Exception("Cohorte no encontrado");

        Optional<CohorteHorario> cohorteHorario = cohorte.getCohorteHorarios()
                .stream()
                .filter(ch -> ch.getHorario() == horario && ch.getCohorte() == cohorte).findFirst();
        if (!cohorteHorario.isPresent()) throw new Exception("Cohorte horario no existe");

        Optional<SesionPresencial> sesionPresencial = this.generarSesion(cohorteHorario.get(), aula, date);

        if (!sesionPresencial.isPresent()) throw new Exception("La sesion no existe");
        if (evalCapacidad(sesionPresencial.get(), date, aula) >= 0) {
            Solicitud solicitud = new Solicitud();
            solicitud.setDdjj(ddjj);
            solicitud.setFechaCarga(date);
            solicitud.setQrAcceso(QrCreatorService.generateQrId());
            solicitud.setSesionPresencial(sesionPresencial.get());
            return solicitud;
        }
        return null;

    }
}
