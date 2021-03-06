package com.unsada.integradora.model.dto;


import com.unsada.integradora.core.business.bean.ControlIngresoDTO;

import java.util.Date;

public class SolicitudActividadDTO extends ControlIngresoDTO {

    private Date fechaCarga;
    private Byte presente;
    private String nombre;
    private String telefono;
    private String mail;
    private int idSolicitud;
    private String nombreActividad;
    private String nombreDependencia;
    private String nombrePropuesta;
    private String nombreEdificio;
    private String nombreSede;
    private String nombreCompleto;

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getNombrePropuesta() {
        return nombrePropuesta;
    }

    public void setNombrePropuesta(String nombrePropuesta) {
        this.nombrePropuesta = nombrePropuesta;
    }

    public SolicitudActividadDTO() {
    }

    public SolicitudActividadDTO(Date fechaCarga, Byte presente, int idSolicitud, String nombre, String telefono, String mail, String nombreActividad, String nombreDependencia, String nombrePropuesta, String nombreEdificio, String nombreSede, String nombreCompleto) {
        this.fechaCarga = fechaCarga;
        this.presente = presente;
        this.idSolicitud = idSolicitud;
        this.nombre = nombre;
        this.telefono = telefono;
        this.mail = mail;
        this.nombreActividad = nombreActividad;
        this.nombreDependencia = nombreDependencia;
        this.nombrePropuesta = nombrePropuesta;
        this.nombreEdificio = nombreEdificio;
        this.nombreSede = nombreSede;
        this.nombreCompleto = nombreCompleto;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Byte getPresente() {
        return presente;
    }

    public void setPresente(Byte presente) {
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

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public void setNombreEdificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
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
