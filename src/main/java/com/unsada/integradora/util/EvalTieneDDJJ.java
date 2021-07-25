package com.unsada.integradora.util;

import com.unsada.integradora.model.entity.Ddjj;
import com.unsada.integradora.model.entity.Persona;
import com.unsada.integradora.service.interfaces.DDjjServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.sql.Date;
@Component
public class EvalTieneDDJJ {
    private LocalDate date = LocalDate.now();
    @Autowired
    DDjjServiceApi ddjjService;

    public Ddjj getDdjj(Persona persona, Date fechaDeBusqueda){
        return ddjjService.findByFechaAndPersona(fechaDeBusqueda, persona).get(0);
    }
}
