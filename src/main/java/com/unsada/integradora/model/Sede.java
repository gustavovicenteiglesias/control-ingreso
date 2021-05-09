package com.unsada.integradora.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;


/**
 * The persistent class for the sede database table.
 * 
 */
@Entity
@NamedQuery(name="Sede.findAll", query="SELECT s FROM Sede s")
public class Sede implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id_sede")
	private int idSede;

	private String nombre;

	//bi-directional many-to-one association to Cohorte
	@OneToMany(mappedBy="sede")
	@JsonManagedReference("cohorte-sede")
	private List<Cohorte> cohortes;

	//bi-directional many-to-one association to Edificio
	@OneToMany(mappedBy="sede")
	@JsonManagedReference("sede-edificios")
	private List<Edificio> edificios;

	public Sede() {
	}

	public int getIdSede() {
		return this.idSede;
	}

	public void setIdSede(int idSede) {
		this.idSede = idSede;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cohorte> getCohortes() {
		return this.cohortes;
	}

	public void setCohortes(List<Cohorte> cohortes) {
		this.cohortes = cohortes;
	}

	public Cohorte addCohorte(Cohorte cohorte) {
		getCohortes().add(cohorte);
		cohorte.setSede(this);

		return cohorte;
	}

	public Cohorte removeCohorte(Cohorte cohorte) {
		getCohortes().remove(cohorte);
		cohorte.setSede(null);

		return cohorte;
	}

	public List<Edificio> getEdificios() {
		return this.edificios;
	}

	public void setEdificios(List<Edificio> edificios) {
		this.edificios = edificios;
	}

	public Edificio addEdificio(Edificio edificio) {
		getEdificios().add(edificio);
		edificio.setSede(this);

		return edificio;
	}

	public Edificio removeEdificio(Edificio edificio) {
		getEdificios().remove(edificio);
		edificio.setSede(null);

		return edificio;
	}

}