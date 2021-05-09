package com.unsada.integradora.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;


/**
 * The persistent class for the dependencia database table.
 * 
 */
@Entity
@NamedQuery(name="Dependencia.findAll", query="SELECT d FROM Dependencia d")
public class Dependencia implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id_dependencia")
	private int idDependencia;

	private String nombre;

	//bi-directional many-to-one association to Propuesta
	@OneToMany(mappedBy="dependencia")
	@JsonManagedReference("propuesta-dependencia")
	private List<Propuesta> propuestas;

	public Dependencia() {
	}

	public int getIdDependencia() {
		return this.idDependencia;
	}

	public void setIdDependencia(int idDependencia) {
		this.idDependencia = idDependencia;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Propuesta> getPropuestas() {
		return this.propuestas;
	}

	public void setPropuestas(List<Propuesta> propuestas) {
		this.propuestas = propuestas;
	}

	public Propuesta addPropuesta(Propuesta propuesta) {
		getPropuestas().add(propuesta);
		propuesta.setDependencia(this);

		return propuesta;
	}

	public Propuesta removePropuesta(Propuesta propuesta) {
		getPropuestas().remove(propuesta);
		propuesta.setDependencia(null);

		return propuesta;
	}

}