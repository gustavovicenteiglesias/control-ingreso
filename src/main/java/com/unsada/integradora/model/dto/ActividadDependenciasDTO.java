package com.unsada.integradora.model.dto;


import com.unsada.integradora.core.business.bean.ControlIngresoDTO;

public class ActividadDependenciasDTO extends ControlIngresoDTO {
    private int idActividad;
    private int idPropuesta;
    private String nombreActividad;
    private String nombreDependencia;
    private String nombrePropuesta;

    public ActividadDependenciasDTO(int idActividad, int idPropuesta, String nombreActividad, String nombreDependencia, String nombrePropuesta) {
        this.idActividad = idActividad;
        this.idPropuesta = idPropuesta;
        this.nombreActividad = nombreActividad;
        this.nombreDependencia = nombreDependencia;
        this.nombrePropuesta = nombrePropuesta;
    }

    public ActividadDependenciasDTO() {
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(int idPropuesta) {
        this.idPropuesta = idPropuesta;
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

    public String getNombrePropuesta() {
        return nombrePropuesta;
    }

    public void setNombrePropuesta(String nombrePropuesta) {
        this.nombrePropuesta = nombrePropuesta;
    }

    @Override
    public String toString() {
        return "ActividadDependenciasDTO{" +
                "idActividad=" + idActividad +
                ", idPropuesta=" + idPropuesta +
                ", nombreActividad='" + nombreActividad + '\'' +
                ", nombreDependencia='" + nombreDependencia + '\'' +
                ", nombrePropuesta='" + nombrePropuesta + '\'' +
                '}';
    }
}
