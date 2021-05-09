package com.unsada.integradora.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;


/**
 * The persistent class for the edificio database table.
 * 
 */
@Entity
@NamedQuery(name="Edificio.findAll", query="SELECT e FROM Edificio e")
public class Edificio implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id_edificio")
	private int idEdificio;

	private String direccion;

	private String nombre;

	//bi-directional many-to-one association to Sede
	@ManyToOne
	@JoinColumn(name="id_sede")
	@JsonBackReference("sede-edificios")
	private Sede sede;

	//bi-directional many-to-one association to EntidadAula
	@OneToMany(mappedBy="edificio")
	@JsonManagedReference("edificio-aula")
	private List<EntidadAula> entidadAulas;

	public Edificio() {
	}

	public int getIdEdificio() {
		return this.idEdificio;
	}

	public void setIdEdificio(int idEdificio) {
		this.idEdificio = idEdificio;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Sede getSede() {
		return this.sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public List<EntidadAula> getEntidadAulas() {
		return this.entidadAulas;
	}

	public void setEntidadAulas(List<EntidadAula> entidadAulas) {
		this.entidadAulas = entidadAulas;
	}

	public EntidadAula addEntidadAula(EntidadAula entidadAula) {
		getEntidadAulas().add(entidadAula);
		entidadAula.setEdificio(this);

		return entidadAula;
	}

	public EntidadAula removeEntidadAula(EntidadAula entidadAula) {
		getEntidadAulas().remove(entidadAula);
		entidadAula.setEdificio(null);

		return entidadAula;
	}

}