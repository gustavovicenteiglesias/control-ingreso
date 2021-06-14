package com.unsada.integradora.model.dto;


import com.unsada.integradora.core.business.bean.ControlIngresoDTO;

import java.util.Date;

public class SolicitudActividadDTO extends ControlIngresoDTO {

    private Date fechaCarga;
    private Boolean presente;
    private String nombre;
    private String telefono;
    private String mail;
    private String nombreActividad;
    private String nombreDependencia;
    private String nombrePropuesta;

    public String getNombrePropuesta() {
        return nombrePropuesta;
    }

    public void setNombrePropuesta(String nombrePropuesta) {
        this.nombrePropuesta = nombrePropuesta;
    }

    public SolicitudActividadDTO() {
    }

    public SolicitudActividadDTO(Date fechaCarga, Boolean presente, String nombre, String telefono, String mail, String nombreActividad, String nombreDependencia, String nombrePropuesta) {
        this.fechaCarga = fechaCarga;
        this.presente = presente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.mail = mail;
        this.nombreActividad = nombreActividad;
        this.nombreDependencia = nombreDependencia;
        this.nombrePropuesta = nombrePropuesta;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Boolean getPresente() {
        return presente;
    }

    public void setPresente(Boolean presente) {
        this.presente = presente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getNombreDependencia() {
        return nombreDependencia;
    }

    public void setNombreDependencia(String nombreDependencia) {
        this.nombreDependencia = nombreDependencia;
    }

    @Override
    public String toString() {
        return "SolicitudActividadDTO{" +
                "fechaCarga=" + fechaCarga +
                ", presente=" + presente +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", mail='" + mail + '\'' +
                ", nombreActividad='" + nombreActividad + '\'' +
                ", nombreDependencia='" + nombreDependencia + '\'' +
                '}';
    }
}
