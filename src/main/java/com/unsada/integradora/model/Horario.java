package com.unsada.integradora.model;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the horario database table.
 * 
 */
@Entity
@NamedQuery(name="Horario.findAll", query="SELECT h FROM Horario h")
public class Horario implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id_horario")
	private int idHorario;

	private String dia;

	@Column(name="hora_inicio")
	private Time horaInicio;

	@Column(name="hora_fin")
	private Time horaFin;


	private String nombre;

	//bi-directional many-to-one association to CohorteHorario
	@OneToMany(mappedBy="horario")
	//@JsonIgnore
	@JsonManagedReference("horario-cohortehorario")
	private List<CohorteHorario> cohorteHorarios;

	public Horario() {
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

	

	public Time getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<CohorteHorario> getCohorteHorarios() {
		return cohorteHorarios;
	}

	public void setCohorteHorarios(List<CohorteHorario> cohorteHorarios) {
		this.cohorteHorarios = cohorteHorarios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}