package com.unsada.integradora.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the ddjj database table.
 * 
 */
@Entity
@NamedQuery(name="Ddjj.findAll", query="SELECT d FROM Ddjj d")
public class Ddjj implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id_ddjj")
	private int idDdjj;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona")
	
	private Persona persona;

	//bi-directional  to DdjjFactorDeRiesgo
	  
    
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST} )
	@JoinTable(
	        name = "ddjj_factor_de_riesgo",
	        joinColumns = @JoinColumn(name = "id_ddjj", nullable = false),
	        inverseJoinColumns = @JoinColumn(name="id_factor_de_riesgo", nullable = false)
	    )
	private Set<FactorDeRiesgo> factorDeRiesgo;

	//bi-directional many-to-one association to Respuesta
	@OneToMany(mappedBy="ddjj",cascade = CascadeType.ALL)
	
	private List<Respuesta> respuestas;

	//bi-directional many-to-one association to Solicitud
	@OneToMany(mappedBy="ddjj", cascade = {CascadeType.PERSIST})
	
	private List<Solicitud> solicituds;

	public Ddjj() {
	}
	
	public int getIdDdjj() {
		return idDdjj;
	}

	public void setIdDdjj(int idDdjj) {
		this.idDdjj = idDdjj;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@JsonIgnore
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Set<FactorDeRiesgo> getFactorDeRiesgo() {
		return factorDeRiesgo;
	}

	public void setFactorDeRiesgo(Set<FactorDeRiesgo> factorDeRiesgo) {
		this.factorDeRiesgo = factorDeRiesgo;
	}
	
	public List<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}
	@JsonIgnore
	public List<Solicitud> getSolicituds() {
		return solicituds;
	}

	public void setSolicituds(List<Solicitud> solicituds) {
		this.solicituds = solicituds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}