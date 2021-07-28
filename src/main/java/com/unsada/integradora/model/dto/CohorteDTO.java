package com.unsada.integradora.model.dto;

import com.unsada.integradora.model.entity.Actividad;
import com.unsada.integradora.model.entity.Sede;

import java.sql.Date;

public class CohorteDTO {
    private int idCohorte;
    private Date fechaInicio;
    private Date fechaFin;
    private String nombreCohorte;
    private String actividad;
    private Sede sede;

    public CohorteDTO(int idCohorte, Date fechaInicio, Date fechaFin, String nombreCohorte, String actividad, Sede sede) {
        this.idCohorte = idCohorte;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombreCohorte = nombreCohorte;
        this.actividad = actividad;
        this.sede = sede;
    }

    public CohorteDTO() {
    }

    public int getIdCohorte() {
        return idCohorte;
    }

    public void setIdCohorte(int idCohorte) {
        this.idCohorte = idCohorte;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombreCohorte() {
        return nombreCohorte;
    }

    public void setNombreCohorte(String nombreCohorte) {
        this.nombreCohorte = nombreCohorte;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }
}
