package com.unsada.integradora.model.dto;

import java.util.Date;
import java.sql.Time;

public class SesionPresencialDTO {
    private Date fecha;
    private int idSesionPresencial;
    private String nombreActividad;
    private String dia;
    private Time horaInicio;
    private Time horaFin;
    private boolean tieneSolicitudes;

    public boolean isTieneSolicitudes() {
        return tieneSolicitudes;
    }

    public void setTieneSolicitudes(boolean tieneSolicitudes) {
        this.tieneSolicitudes = tieneSolicitudes;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdSesionPresencial() {
        return idSesionPresencial;
    }

    public void setIdSesionPresencial(int idSesionPresencial) {
        this.idSesionPresencial = idSesionPresencial;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public SesionPresencialDTO(Date fecha, int idSesionPresencial, String nombreActividad, String dia, Time horaInicio, Time horaFin, boolean tieneSolicitudes) {
        this.fecha = fecha;
        this.idSesionPresencial = idSesionPresencial;
        this.nombreActividad = nombreActividad;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.tieneSolicitudes = tieneSolicitudes;
    }

    @Override
    public String toString() {
        return "SesionPresencialDTO{" +
                "fecha=" + fecha +
                ", idSesionPresencial=" + idSesionPresencial +
                ", nombreActividad='" + nombreActividad + '\'' +
                ", dia='" + dia + '\'' +
                ", horaInicio=" + horaInicio +
                ", horaFin=" + horaFin +

                '}';
    }
}
