package com.unsada.integradora.core.reportes.impl;

import com.unsada.integradora.core.business.bean.ControlIngresoDTO;
import com.unsada.integradora.core.reportes.ReportesPersona;
import com.unsada.integradora.core.reportes.model.ReporteGenerico;
import com.unsada.integradora.model.dto.SolicitudActividadDTO;
import com.unsada.integradora.model.entity.Persona;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReportePersonaImpl extends ReporteGenerico implements ReportesPersona {
    @Override
    public SXSSFWorkbook solicitudesActivas(Persona persona) {
        return null;
    }

    @Override
    public SXSSFWorkbook generarReportePersonasEnContacto( List<SolicitudActividadDTO> solicitudActividadDTOS, String sheetName) throws IllegalAccessException {
        return populateFromList(solicitudActividadDTOS, sheetName);
    }









}
