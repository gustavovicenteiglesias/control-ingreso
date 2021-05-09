package com.unsada.integradora.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the persona database table.
 * 
 */
@Entity
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id_persona")
	private int idPersona;

	@Column(name="correo_electronico")
	private String correoElectronico;

	private String direccion;

	private String dni;

	private String nombre;

	private String telefono;

	//bi-directional many-to-one association to Ddjj
	@OneToMany(mappedBy="persona")
	private List<Ddjj> ddjjs;

	public Persona() {
	}

	public int getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Ddjj> getDdjjs() {
		return this.ddjjs;
	}

	public void setDdjjs(List<Ddjj> ddjjs) {
		this.ddjjs = ddjjs;
	}

	public Ddjj addDdjj(Ddjj ddjj) {
		getDdjjs().add(ddjj);
		ddjj.setPersona(this);

		return ddjj;
	}

	public Ddjj removeDdjj(Ddjj ddjj) {
		getDdjjs().remove(ddjj);
		ddjj.setPersona(null);

		return ddjj;
	}

}