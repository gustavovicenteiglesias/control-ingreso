package com.unsada.integradora.model.entity;

import java.io.Serializable;
import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;


/**
 * The persistent class for the factor_de_riesgo database table.
 * 
 */
@Entity
@Table(name="factor_de_riesgo")
@NamedQuery(name="FactorDeRiesgo.findAll", query="SELECT f FROM FactorDeRiesgo f")
public class FactorDeRiesgo implements Serializable {
	private static final long serialVersionUID = 1L;
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="id_factor_de_riesgo")
	private int idFactorDeRiesgo;

	private String nombre;

	//bi-directional many-to-many association to Ddjj 
	@ManyToMany(mappedBy = "factorDeRiesgo")
	
	@JsonIgnore
	//@JsonBackReference("DdjjFactorDeRiesgo-FactorDeRiesgo")
	private Set<Ddjj> ddjj;

	public FactorDeRiesgo() {
	}

	public int getIdFactorDeRiesgo() {
		return this.idFactorDeRiesgo;
	}

	public void setIdFactorDeRiesgo(int idFactorDeRiesgo) {
		this.idFactorDeRiesgo = idFactorDeRiesgo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Ddjj> getDdjj() {
		return ddjj;
	}

	public void setDdjj(Set<Ddjj> ddjj) {
		this.ddjj = ddjj;
	}

	@Override
	public String toString() {
		return "FactorDeRiesgo [idFactorDeRiesgo=" + idFactorDeRiesgo + ", nombre=" + nombre + ", ddjj=" + ddjj + "]";
	}

	
	
	
	

}