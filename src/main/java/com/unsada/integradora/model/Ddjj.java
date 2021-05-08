package com.unsada.integradora.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ddjj database table.
 * 
 */
@Entity
@NamedQuery(name="Ddjj.findAll", query="SELECT d FROM Ddjj d")
public class Ddjj implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_ddjj")
	private int idDdjj;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona")
	private Persona persona;

	//bi-directional one-to-one association to DdjjFactorDeRiesgo
	@OneToOne(mappedBy="ddjj")
	private DdjjFactorDeRiesgo ddjjFactorDeRiesgo;

	//bi-directional many-to-one association to Respuesta
	@OneToMany(mappedBy="ddjj")
	private List<Respuesta> respuestas;

	//bi-directional many-to-one association to Solicitud
	@OneToMany(mappedBy="ddjj")
	private List<Solicitud> solicituds;

	public Ddjj() {
	}

	public int getIdDdjj() {
		return this.idDdjj;
	}

	public void setIdDdjj(int idDdjj) {
		this.idDdjj = idDdjj;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public DdjjFactorDeRiesgo getDdjjFactorDeRiesgo() {
		return this.ddjjFactorDeRiesgo;
	}

	public void setDdjjFactorDeRiesgo(DdjjFactorDeRiesgo ddjjFactorDeRiesgo) {
		this.ddjjFactorDeRiesgo = ddjjFactorDeRiesgo;
	}

	public List<Respuesta> getRespuestas() {
		return this.respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	public Respuesta addRespuesta(Respuesta respuesta) {
		getRespuestas().add(respuesta);
		respuesta.setDdjj(this);

		return respuesta;
	}

	public Respuesta removeRespuesta(Respuesta respuesta) {
		getRespuestas().remove(respuesta);
		respuesta.setDdjj(null);

		return respuesta;
	}

	public List<Solicitud> getSolicituds() {
		return this.solicituds;
	}

	public void setSolicituds(List<Solicitud> solicituds) {
		this.solicituds = solicituds;
	}

	public Solicitud addSolicitud(Solicitud solicitud) {
		getSolicituds().add(solicitud);
		solicitud.setDdjj(this);

		return solicitud;
	}

	public Solicitud removeSolicitud(Solicitud solicitud) {
		getSolicituds().remove(solicitud);
		solicitud.setDdjj(null);

		return solicitud;
	}

}