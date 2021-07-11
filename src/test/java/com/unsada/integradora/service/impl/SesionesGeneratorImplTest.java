package com.unsada.integradora.service.impl;

import com.unsada.integradora.model.entity.Cohorte;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.util.Calendar;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SesionesGeneratorImplTest {
    Cohorte cohorte;
    Cohorte cohorteActualizado;
    @BeforeAll
    public void setUp(){
        //cohorte setup
        LocalDate inicio = LocalDate.of(2021,05,10);
        LocalDate fin = LocalDate.of(2021, 06, 10);
        LocalDate newFin = LocalDate.of(2021, 06, 30);
        cohorte.setIdCohorte(1);
        cohorte.setFechaFin(Date.valueOf(fin));
        cohorte.setFechaInicio(Date.valueOf(inicio));

        cohorteActualizado.setIdCohorte(1);
        cohorteActualizado.setFechaFin(Date.valueOf(newFin));
        cohorteActualizado.setFechaInicio(Date.valueOf(inicio));
        System.out.println(cohorte.toString());
        //
    }


    @Test
    void crearSesiones() {
    }

    @Test
    void crearCohorteHorario() {
    }

    @Test
    void actualizarSesiones() {
    }

    @Test
    void filterFechasPorDiasHorarios() {
    }
}