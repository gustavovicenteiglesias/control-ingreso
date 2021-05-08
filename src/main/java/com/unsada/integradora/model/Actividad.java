package com.unsada.integradora.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the actividad database table.
 * 
 */
@Entity
@NamedQuery(name="Actividad.findAll", query="SELECT a FROM Actividad a")
public class Actividad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_actividad")
	private int idActividad;

	private String nombre;

	//bi-directional many-to-one association to Propuesta
	@ManyToOne
	@JoinColumn(name="id_propuesta")
	private Propuesta propuesta;

	//bi-directional many-to-one association to Cohorte
	@OneToMany(mappedBy="actividad")
	private List<Cohorte> cohortes;

	public Actividad() {
	}

	public int getIdActividad() {
		return this.idActividad;
	}

	public void setIdActividad(int idActividad) {
		this.idActividad = idActividad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Propuesta getPropuesta() {
		return this.propuesta;
	}

	public void setPropuesta(Propuesta propuesta) {
		this.propuesta = propuesta;
	}

	public List<Cohorte> getCohortes() {
		return this.cohortes;
	}

	public void setCohortes(List<Cohorte> cohortes) {
		this.cohortes = cohortes;
	}

	public Cohorte addCohorte(Cohorte cohorte) {
		getCohortes().add(cohorte);
		cohorte.setActividad(this);

		return cohorte;
	}

	public Cohorte removeCohorte(Cohorte cohorte) {
		getCohortes().remove(cohorte);
		cohorte.setActividad(null);

		return cohorte;
	}

}