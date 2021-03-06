package com.unsada.integradora.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.sql.Date;
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

	private Date fecha;

	//bi-directional many-to-one association to CohorteHorario
	@ManyToOne
	@JoinColumn(name="id_cohorte_horario")
	//@JsonIgnore
	@JsonBackReference("SesionPresencial-cohorteHorario")
	private CohorteHorario cohorteHorario;

	//bi-directional many-to-one association to EntidadAula
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="id_aula")
	//@JsonIgnore
	@JsonBackReference("aula-sesionPresencials")
	private EntidadAula entidadAula;

	//bi-directional many-to-one association to Solicitud
	@OneToMany(cascade = CascadeType.ALL, mappedBy="sesionPresencial")
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

	@Override
	public String toString() {
		return "SesionPresencial{" +
				"idSesionPresencial=" + idSesionPresencial +
				", fecha=" + fecha +
				", solicituds=" + solicituds.size() +
				'}';
	}
}