package com.unsada.integradora.util;

import com.unsada.integradora.model.entity.SesionPresencial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SesionesFilter {

    private LocalDate date = LocalDate.now();
    @Value("${vars.duracion-ddjj}")
    private int duracionDdjj;

    public List<SesionPresencial> filterSesionesFechaActual(List<SesionPresencial> sesiones){
        date.minusDays(1);
       sesiones.stream().filter(i -> i.getFecha().toLocalDate().datesUntil(date).count() == duracionDdjj).collect(Collectors.toList());
       return sesiones;
    }


}
