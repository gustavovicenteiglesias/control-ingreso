package com.unsada.integradora.util;

import com.unsada.integradora.model.entity.Ddjj;
import com.unsada.integradora.model.entity.Persona;
import com.unsada.integradora.service.interfaces.DDjjServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EvalTieneDDJJ {
    private LocalDate date = LocalDate.now();
    @Autowired
    DDjjServiceApi ddjjService;
    @Value("${vars.duracion-ddjj}")
    private int duracionDdjj;

    public Ddjj getDdjj(Persona persona, Date fechaDeBusqueda){
        List<Ddjj> ddjj = ddjjService.findByPersona(persona);

        return compararFechas(ddjj);
    }


    //loop por todas las ddjj, si alguna coincide la devuelve, sino devuelve null
    public Ddjj compararFechas(List<Ddjj> declaraciones){

        for (Ddjj ddjj : declaraciones) {
            if (eval(ddjj) == 1) {
                return ddjj;
            }
        }
        return null;

    }


    //Compara fechas, si es el mismo dia retorna 1, si no es retorna 0, si en indice es mayor al de la ddj
    //retorna -1
    public int eval(Ddjj ddjj){
        try{
            long count = ddjj.getFecha().toLocalDate().datesUntil(date).count();
            if(count == duracionDdjj){
                System.out.println("este es el mismo dia");
                return 1;
            }else{
                return 0;
            }
        }catch (Exception e){
            System.out.println("fecha a comparar es posterior al indice");
            return -1;
        }
    }
}
