package com.unsada.integradora.model;

import java.io.Serializable;
import javax.persistence.*;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id_factor_de_riesgo")
	private int idFactorDeRiesgo;

	private String nombre;

	//bi-directional many-to-many association to Ddjj 
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
	@JoinTable(
	        name = "factor_de_riesgo_ddjj",
	        joinColumns = @JoinColumn(name = "id_factor_de_riesgo1", nullable = false),
	        inverseJoinColumns = @JoinColumn(name="id_ddjj1", nullable = false)
	    )
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

	
	
	
	

}