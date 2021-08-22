package com.unsada.integradora.util.solicitudes;

import com.unsada.integradora.model.entity.*;
import org.springframework.stereotype.Repository;

import java.sql.Date;
@Repository
public interface SolicitudCreator {
    public Solicitud crearSolicitud(Ddjj ddjj, Horario horario, Actividad actividad, EntidadAula aula, Date date) throws Exception;

    public int getNumeroSolicitudes(SesionPresencial sesion, Date date);

    public int evalCapacidad(SesionPresencial sesionPresencial, Date date, EntidadAula aula);
}
