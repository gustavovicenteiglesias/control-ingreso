package com.unsada.integradora.model.dto;

import com.unsada.integradora.core.business.bean.ControlIngresoDTO;
import com.unsada.integradora.model.entity.Edificio;
import com.unsada.integradora.model.entity.SesionPresencial;

import java.util.List;
import java.util.Objects;

public class AulaEdificioDto extends ControlIngresoDTO {
    private int idAula;
    private int capacidadConAforo;
    private String nombre;
    private String nombreEdificio;
    private String nombreSede;
    private List<SesionPresencial> sesiones;

    public AulaEdificioDto(int idAula, String nombre, int capacidadConAforo, List<SesionPresencial> sesiones, String nombreEdificio, String nombreSede) {
        this.capacidadConAforo = capacidadConAforo;
        this.nombre = nombre;
        this.nombreEdificio = nombreEdificio;
        this.nombreSede = nombreSede;
        this.idAula = idAula;
        this.sesiones = sesiones;
    }

    public AulaEdificioDto() {
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public List<SesionPresencial> getSesiones() {
        return sesiones;
    }

    public void setSesiones(List<SesionPresencial> sesiones) {
        this.sesiones = sesiones;
    }

    public int getCapacidadConAforo() {
        return capacidadConAforo;
    }

    public void setCapacidadConAforo(int capacidadConAforo) {
        this.capacidadConAforo = capacidadConAforo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreEdificio() {
        return nombreEdificio;
    }

    public void setNombreEdificio(String nombreEdificio) {
        this.nombreEdificio = nombreEdificio;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    @Override
    public String toString() {
        return "AulaEdificioDto{" +
                "idAula=" + idAula +
                ", capacidadConAforo=" + capacidadConAforo +
                ", nombre='" + nombre + '\'' +
                ", nombreEdificio='" + nombreEdificio + '\'' +
                ", nombreSede='" + nombreSede + '\'' +
                ", sesiones=" + sesiones +
                '}';
    }
}
