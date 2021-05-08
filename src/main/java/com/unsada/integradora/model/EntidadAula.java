package com.unsada.integradora.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the entidad_aula database table.
 * 
 */
@Entity
@Table(name="entidad_aula")
@NamedQuery(name="EntidadAula.findAll", query="SELECT e FROM EntidadAula e")
public class EntidadAula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_aula")
	private int idAula;

	@Column(name="capacidad_con_aforo")
	private int capacidadConAforo;

	private String nombre;

	//bi-directional many-to-one association to Edificio
	@ManyToOne
	@JoinColumn(name="id_edificio")
	private Edificio edificio;

	//bi-directional many-to-one association to SesionPresencial
	@OneToMany(mappedBy="entidadAula")
	private List<SesionPresencial> sesionPresencials;

	public EntidadAula() {
	}

	public int getIdAula() {
		return this.idAula;
	}

	public void setIdAula(int idAula) {
		this.idAula = idAula;
	}

	public int getCapacidadConAforo() {
		return this.capacidadConAforo;
	}

	public void setCapacidadConAforo(int capacidadConAforo) {
		this.capacidadConAforo = capacidadConAforo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Edificio getEdificio() {
		return this.edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	public List<SesionPresencial> getSesionPresencials() {
		return this.sesionPresencials;
	}

	public void setSesionPresencials(List<SesionPresencial> sesionPresencials) {
		this.sesionPresencials = sesionPresencials;
	}

	public SesionPresencial addSesionPresencial(SesionPresencial sesionPresencial) {
		getSesionPresencials().add(sesionPresencial);
		sesionPresencial.setEntidadAula(this);

		return sesionPresencial;
	}

	public SesionPresencial removeSesionPresencial(SesionPresencial sesionPresencial) {
		getSesionPresencials().remove(sesionPresencial);
		sesionPresencial.setEntidadAula(null);

		return sesionPresencial;
	}

}