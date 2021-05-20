package com.unsada.integradora.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the sesion_presencial database table.
 * 
 */
@Entity
@Table(name="sesion_presencial")
@NamedQuery(name="SesionPresencial.findAll", query="SELECT s FROM SesionPresencial s")
public class SesionPresencial implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id_sesion_presencial")
	private int idSesionPresencial;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	//bi-directional many-to-one association to CohorteHorario
	@ManyToOne
	@JoinColumn(name="id_cohorte_horario")
	//@JsonIgnore
	@JsonBackReference("SesionPresencial-cohorteHorario")
	private CohorteHorario cohorteHorario;

	//bi-directional many-to-one association to EntidadAula
	@ManyToOne
	@JoinColumn(name="id_aula")
	//@JsonIgnore
	@JsonBackReference("aula-sesionPresencials")
	private EntidadAula entidadAula;

	//bi-directional many-to-one association to Solicitud
	@OneToMany(mappedBy="sesionPresencial")
	@JsonIgnore
	//@JsonManagedReference("solicitud-sessionpresencial")
	private List<Solicitud> solicituds;

	public SesionPresencial() {
	}

	public int getIdSesionPresencial() {
		return this.idSesionPresencial;
	}

	public void setIdSesionPresencial(int idSesionPresencial) {
		this.idSesionPresencial = idSesionPresencial;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public CohorteHorario getCohorteHorario() {
		return this.cohorteHorario;
	}

	public void setCohorteHorario(CohorteHorario cohorteHorario) {
		this.cohorteHorario = cohorteHorario;
	}

	public EntidadAula getEntidadAula() {
		return this.entidadAula;
	}

	public void setEntidadAula(EntidadAula entidadAula) {
		this.entidadAula = entidadAula;
	}

	public List<Solicitud> getSolicituds() {
		return this.solicituds;
	}

	public void setSolicituds(List<Solicitud> solicituds) {
		this.solicituds = solicituds;
	}

	public Solicitud addSolicitud(Solicitud solicitud) {
		getSolicituds().add(solicitud);
		solicitud.setSesionPresencial(this);

		return solicitud;
	}

	public Solicitud removeSolicitud(Solicitud solicitud) {
		getSolicituds().remove(solicitud);
		solicitud.setSesionPresencial(null);

		return solicitud;
	}

}