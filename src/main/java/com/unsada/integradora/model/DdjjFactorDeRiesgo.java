package com.unsada.integradora.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ddjj_factor_de_riesgo database table.
 * 
 */
@Entity
@Table(name="ddjj_factor_de_riesgo")
@NamedQuery(name="DdjjFactorDeRiesgo.findAll", query="SELECT d FROM DdjjFactorDeRiesgo d")
public class DdjjFactorDeRiesgo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_ddjj")
	private int idDdjj;

	//bi-directional one-to-one association to Ddjj
	@OneToOne
	@JoinColumn(name="id_ddjj")
	private Ddjj ddjj;

	//bi-directional many-to-one association to FactorDeRiesgo
	@ManyToOne
	@JoinColumn(name="id_factor_de_riesgo")
	private FactorDeRiesgo factorDeRiesgo;

	public DdjjFactorDeRiesgo() {
	}

	public int getIdDdjj() {
		return this.idDdjj;
	}

	public void setIdDdjj(int idDdjj) {
		this.idDdjj = idDdjj;
	}

	public Ddjj getDdjj() {
		return this.ddjj;
	}

	public void setDdjj(Ddjj ddjj) {
		this.ddjj = ddjj;
	}

	public FactorDeRiesgo getFactorDeRiesgo() {
		return this.factorDeRiesgo;
	}

	public void setFactorDeRiesgo(FactorDeRiesgo factorDeRiesgo) {
		this.factorDeRiesgo = factorDeRiesgo;
	}

}