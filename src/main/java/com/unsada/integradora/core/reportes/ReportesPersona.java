package com.unsada.integradora.core.reportes;

import com.unsada.integradora.model.dto.SolicitudActividadDTO;
import com.unsada.integradora.model.entity.Persona;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.List;

public interface ReportesPersona{
        public SXSSFWorkbook solicitudesActivas(Persona persona);
        public SXSSFWorkbook generarReportePersonasEnContacto(List<SolicitudActividadDTO> personasEnContacto, String sheetName) throws IllegalAccessException;
}
