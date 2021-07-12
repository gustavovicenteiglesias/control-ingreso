package com.unsada.integradora.service.interfaces;

import com.unsada.integradora.model.entity.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public interface SesionesGeneratorInterface {
    public void crearSesiones(Cohorte cohorte, Horario data, EntidadAula aula) ;
    public void crearCohorteHorario(Cohorte cohorte, Horario data);
    public void actualizarSesiones(Cohorte cohorteOriginal, Cohorte cohorteActualizado);
}
