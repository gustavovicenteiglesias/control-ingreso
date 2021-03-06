package com.unsada.integradora.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the propuesta database table.
 * 
 */
@Entity
@NamedQuery(name="Propuesta.findAll", query="SELECT p FROM Propuesta p")
public class Propuesta implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id_propuesta")
	private int idPropuesta;

	private String nombre;

	//bi-directional many-to-one association to Actividad
	@OneToMany(mappedBy="propuesta", cascade = CascadeType.PERSIST)
	@JsonIgnore
	//@JsonManagedReference("propuesta-actividad")
	private List<Actividad> actividads;

	//bi-directional many-to-one association to Dependencia
	@ManyToOne
	@JoinColumn(name="id_dependencia")
	@JsonBackReference("propuesta-dependencia")
	private Dependencia dependencia;

	public Propuesta() {
	}

	public int getIdPropuesta() {
		return this.idPropuesta;
	}

	public void setIdPropuesta(int idPropuesta) {
		this.idPropuesta = idPropuesta;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Actividad> getActividads() {
		return this.actividads;
	}

	public void setActividads(List<Actividad> actividads) {
		this.actividads = actividads;
	}

	public Actividad addActividad(Actividad actividad) {
		getActividads().add(actividad);
		actividad.setPropuesta(this);

		return actividad;
	}

	public Actividad removeActividad(Actividad actividad) {
		getActividads().remove(actividad);
		actividad.setPropuesta(null);

		return actividad;
	}

	public Dependencia getDependencia() {
		return this.dependencia;
	}

	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}

}