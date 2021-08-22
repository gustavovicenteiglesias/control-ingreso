package com.unsada.integradora.core.reportes.impl;

import com.unsada.integradora.model.dto.SolicitudActividadDTO;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.testng.annotations.Test;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReportePersonaImplTest {
    ReportePersonaImpl reportePersona = new ReportePersonaImpl();

    @Test
    public  void test() throws IllegalAccessException, IOException {
        List<SolicitudActividadDTO> personas = new ArrayList<>();
        SolicitudActividadDTO persona1 = new SolicitudActividadDTO();
        persona1.setTelefono("123123123");
        persona1.setNombre("juan");
        personas.add(persona1);
        personas.add(persona1);
        personas.add(persona1);
        FileOutputStream file = new FileOutputStream("testiii.xml");
        SXSSFWorkbook workbook = reportePersona.generarReporteGenericoPersona(personas, "testGeneradoBien");
        workbook.write(file);

    }

}