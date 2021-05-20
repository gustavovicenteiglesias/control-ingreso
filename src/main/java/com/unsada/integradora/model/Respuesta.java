package com.unsada.integradora.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the respuesta database table.
 * 
 */
@Entity
@NamedQuery(name="Respuesta.findAll", query="SELECT r FROM Respuesta r")
public class Respuesta implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id_respuesta;

	private byte afirmativo;

	//bi-directional many-to-one association to Ddjj
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_ddjj" )
	//@JsonBackReference("respuestas-ddjj")
	private Ddjj ddjj;

	//bi-directional many-to-one association to Pregunta
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_pregunta")
	//@JsonBackReference("pregunta-respuesta")
	private Pregunta pregunta;

	public Respuesta() {
	}

	

	public Integer getId_respuesta() {
		return id_respuesta;
	}



	public void setId_respuesta(Integer id_respuesta) {
		this.id_respuesta = id_respuesta;
	}



	public byte getAfirmativo() {
		return this.afirmativo;
	}

	public void setAfirmativo(byte afirmativo) {
		this.afirmativo = afirmativo;
	}

	public Ddjj getDdjj() {
		return this.ddjj;
	}

	public void setDdjj(Ddjj ddjj) {
		this.ddjj = ddjj;
	}

	public Pregunta getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}



	@Override
	public String toString() {
		return "Respuesta [id_respuesta=" + id_respuesta + ", afirmativo=" + afirmativo + ", ddjj=" + ddjj
				+ ", pregunta=" + pregunta + "]";
	}
	
}