package com.unsada.integradora.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the cohorte_horario database table.
 * 
 */
@Entity
@Table(name="cohorte_horario")

public class CohorteHorario implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id_cohorte_horario")
	private int idCohorteHorario;

	//bi-directional many-to-one association to Cohorte
	@ManyToOne
	@JoinColumn(name="id_cohorte")
	
	@JsonBackReference("cohorte-horario")
	private Cohorte cohorte;

	//bi-directional many-to-one association to Horario
	@ManyToOne
	//@JsonIgnore
	@JsonBackReference("horario-cohortehorario")
	@JoinColumn(name="id_horario")
	private Horario horario;

	//bi-directional many-to-one association to SesionPresencial
	@OneToMany(mappedBy="cohorteHorario")
	@JsonIgnore
	//@JsonManagedReference("SesionPresencial-cohorteHorario")
	private List<SesionPresencial> sesionPresencials;

	public CohorteHorario() {
	}

	public int getIdCohorteHorario() {
		return this.idCohorteHorario;
	}

	public void setIdCohorteHorario(int idCohorteHorario) {
		this.idCohorteHorario = idCohorteHorario;
	}

	public Cohorte getCohorte() {
		return this.cohorte;
	}

	public void setCohorte(Cohorte cohorte) {
		this.cohorte = cohorte;
	}

	public Horario getHorario() {
		return this.horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public List<SesionPresencial> getSesionPresencials() {
		return this.sesionPresencials;
	}

	public void setSesionPresencials(List<SesionPresencial> sesionPresencials) {
		this.sesionPresencials = sesionPresencials;
	}

	public SesionPresencial addSesionPresencial(SesionPresencial sesionPresencial) {
		getSesionPresencials().add(sesionPresencial);
		sesionPresencial.setCohorteHorario(this);

		return sesionPresencial;
	}

	public SesionPresencial removeSesionPresencial(SesionPresencial sesionPresencial) {
		getSesionPresencials().remove(sesionPresencial);
		sesionPresencial.setCohorteHorario(null);

		return sesionPresencial;
	}

}