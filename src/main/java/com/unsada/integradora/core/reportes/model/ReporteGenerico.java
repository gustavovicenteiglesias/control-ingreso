package com.unsada.integradora.core.reportes.model;

import com.unsada.integradora.core.business.bean.ControlIngresoDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import java.lang.reflect.Field;
import java.util.List;

public abstract class ReporteGenerico<T extends ControlIngresoDTO> {

    public SXSSFWorkbook workbook = new SXSSFWorkbook();
    private int rowCount;

    public SXSSFWorkbook populateFromList(List<T> list, String sheetName) throws IllegalAccessException {
        Sheet sheet = workbook.createSheet(sheetName);
        rowCount = 0;
        sheet = generateTitles(list.get(0), sheet);

        for(Object object : list){
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            for (Field field : object.getClass().getDeclaredFields()){
                field.setAccessible(true);
                Cell cell = row.createCell(columnCount ++);
                Object value = (Object) field.get(object);
                try{
                    cell.setCellValue(value.toString());
                }catch (Exception e){
                    cell.setCellValue("--");
                }
            }
        }
        return  workbook;
    }


    public Sheet generateTitles(Object object, Sheet sheet){
        Row header = sheet.createRow(rowCount++);
        int columnCount = 0;
        for (Field field : object.getClass().getDeclaredFields()){
            try {
                Cell headerCell = header.createCell(columnCount++);
                headerCell.setCellValue(field.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sheet;
    };

}
