package com.unsada.integradora.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the factor_de_riesgo database table.
 * 
 */
@Entity
@Table(name="factor_de_riesgo")
@NamedQuery(name="FactorDeRiesgo.findAll", query="SELECT f FROM FactorDeRiesgo f")
public class FactorDeRiesgo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_factor_de_riesgo")
	private int idFactorDeRiesgo;

	private String nombre;

	//bi-directional many-to-one association to DdjjFactorDeRiesgo
	@OneToMany(mappedBy="factorDeRiesgo")
	private List<DdjjFactorDeRiesgo> ddjjFactorDeRiesgos;

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

	public List<DdjjFactorDeRiesgo> getDdjjFactorDeRiesgos() {
		return this.ddjjFactorDeRiesgos;
	}

	public void setDdjjFactorDeRiesgos(List<DdjjFactorDeRiesgo> ddjjFactorDeRiesgos) {
		this.ddjjFactorDeRiesgos = ddjjFactorDeRiesgos;
	}

	public DdjjFactorDeRiesgo addDdjjFactorDeRiesgo(DdjjFactorDeRiesgo ddjjFactorDeRiesgo) {
		getDdjjFactorDeRiesgos().add(ddjjFactorDeRiesgo);
		ddjjFactorDeRiesgo.setFactorDeRiesgo(this);

		return ddjjFactorDeRiesgo;
	}

	public DdjjFactorDeRiesgo removeDdjjFactorDeRiesgo(DdjjFactorDeRiesgo ddjjFactorDeRiesgo) {
		getDdjjFactorDeRiesgos().remove(ddjjFactorDeRiesgo);
		ddjjFactorDeRiesgo.setFactorDeRiesgo(null);

		return ddjjFactorDeRiesgo;
	}

}