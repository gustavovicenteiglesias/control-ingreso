package com.unsada.integradora.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cohorte database table.
 * 
 */
@Entity
@NamedQuery(name="Cohorte.findAll", query="SELECT c FROM Cohorte c")
public class Cohorte implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id_cohorte")
	private int idCohorte;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fin")
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

	//bi-directional many-to-one association to Actividad
	@ManyToOne(optional = true)
	@JoinColumn(name="id_actividad")
	//@JsonIgnore
	@JsonBackReference("actividad-cohorte")
	private Actividad actividad;

	//bi-directional many-to-one association to Sede
	@ManyToOne
	@JoinColumn(name="id_sede")
	//@JsonIgnore
	//@JsonBackReference("cohorte-sede")
	private Sede sede;

	//bi-directional many-to-one association to CohorteHorario
	@OneToMany(mappedBy="cohorte")
	
	@JsonManagedReference("cohorte-horario")
	private List<CohorteHorario> cohorteHorarios;

	public Cohorte() {
	}

	public int getIdCohorte() {
		return this.idCohorte;
	}

	public void setIdCohorte(int idCohorte) {
		this.idCohorte = idCohorte;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Actividad getActividad() {
		return this.actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Sede getSede() {
		return this.sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public List<CohorteHorario> getCohorteHorarios() {
		return this.cohorteHorarios;
	}

	public void setCohorteHorarios(List<CohorteHorario> cohorteHorarios) {
		this.cohorteHorarios = cohorteHorarios;
	}

	public CohorteHorario addCohorteHorario(CohorteHorario cohorteHorario) {
		getCohorteHorarios().add(cohorteHorario);
		cohorteHorario.setCohorte(this);

		return cohorteHorario;
	}

	public CohorteHorario removeCohorteHorario(CohorteHorario cohorteHorario) {
		getCohorteHorarios().remove(cohorteHorario);
		cohorteHorario.setCohorte(null);

		return cohorteHorario;
	}

}