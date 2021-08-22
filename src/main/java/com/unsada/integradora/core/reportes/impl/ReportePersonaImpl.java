package com.unsada.integradora.core.reportes.impl;

import com.unsada.integradora.core.reportes.ReportesPersona;
import com.unsada.integradora.core.reportes.model.ReporteGenerico;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportePersonaImpl extends ReporteGenerico implements ReportesPersona {


    @Override
    public SXSSFWorkbook generarReporteGenericoPersona(List<?> lista, String sheetname) throws IllegalAccessException {
        return populateFromList(lista, sheetname);

    }


}