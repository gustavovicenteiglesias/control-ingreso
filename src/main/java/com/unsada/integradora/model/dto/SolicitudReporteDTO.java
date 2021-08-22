package com.unsada.integradora.model.dto;

import java.util.Date;

public class SolicitudReporteDTO {

    private Date Fecha;
    private String Presente;
    private String Nombre;
    private String Telefono;
    private String Mail;
    private String Actividad;
    private String Edificio;
    private String Sede;

    public SolicitudReporteDTO(Date fecha, String presente, String nombre, String telefono, String mail, String actividad, String edificio, String sede) {
        Fecha = fecha;
        Presente = presente;
        Nombre = nombre;
        Telefono = telefono;
        Mail = mail;
        Actividad = actividad;
        Edificio = edificio;
        Sede = sede;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public String getPresente() {
        return Presente;
    }

    public void setPresente(String presente) {
        Presente = presente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getActividad() {
        return Actividad;
    }

    public void setActividad(String actividad) {
        Actividad = actividad;
    }

    public String getEdificio() {
        return Edificio;
    }

    public void setEdificio(String edificio) {
        Edificio = edificio;
    }

    public String getSede() {
        return Sede;
    }

    public void setSede(String sede) {
        Sede = sede;
    }
}
