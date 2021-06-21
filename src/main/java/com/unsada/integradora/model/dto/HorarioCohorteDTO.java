package com.unsada.integradora.model.dto;

import java.sql.Time;
import java.util.Set;

public class HorarioCohorteDTO {
    private int idHorario;
    private String dia;
    private Time horaInicio;
    private Time horaFin;
    private String nombre;
    private Set nombreCohorte;

    public HorarioCohorteDTO(int idHorario, String dia, Time horaInicio, Time horaFin, String nombre, Set nombreCohorte) {
        this.idHorario = idHorario;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.nombre = nombre;
        this.nombreCohorte = nombreCohorte;
    }

    public HorarioCohorteDTO() {
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set getNombreCohorte() {
        return nombreCohorte;
    }

    public void setNombreCohorte(Set nombreCohorte) {
        this.nombreCohorte = nombreCohorte;
    }
}
