package com.unsada.integradora.model.dto;


import com.unsada.integradora.core.business.bean.ControlIngresoDTO;
import com.unsada.integradora.model.entity.Actividad;
import com.unsada.integradora.model.entity.Edificio;

import java.util.List;

public class ActividadDependenciasDTO extends ControlIngresoDTO {
    private int idPropuesta;
    private Actividad actividad;
    private String nombreDependencia;
    private String nombrePropuesta;
    private List<Edificio> edificios;

    public ActividadDependenciasDTO(int idPropuesta, Actividad actividad, String nombreDependencia, String nombrePropuesta, List<Edificio> edificios) {
        this.idPropuesta = idPropuesta;
        this.actividad = actividad;
        this.nombreDependencia = nombreDependencia;
        this.nombrePropuesta = nombrePropuesta;
        this.edificios = edificios;
    }

    public ActividadDependenciasDTO() {
    }

    public int getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(int idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public Actividad getNombreActividad() {
        return actividad;
    }

    public void setNombreActividad(Actividad nombreActividad) {
        this.actividad = nombreActividad;
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

    public List<Edificio> getEdificios() {
        return edificios;
    }

    public void setEdificios(List<Edificio> edificios) {
        this.edificios = edificios;
    }


    @Override
    public String toString() {
        return "ActividadDependenciasDTO{" +
                ", idPropuesta=" + idPropuesta +
                ", nombreActividad='" + actividad.getNombre() + '\'' +
                ", nombreDependencia='" + nombreDependencia + '\'' +
                ", nombrePropuesta='" + nombrePropuesta + '\'' +
                '}';
    }
}
