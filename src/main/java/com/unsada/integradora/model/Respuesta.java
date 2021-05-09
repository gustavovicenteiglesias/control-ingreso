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
	@EmbeddedId
	private RespuestaPK id;

	private byte afirmativo;

	//bi-directional many-to-one association to Ddjj
	@ManyToOne
	@JoinColumn(name="id_ddjj", insertable = false, updatable = false )
	@JsonBackReference("respuestas-ddjj")
	private Ddjj ddjj;

	//bi-directional many-to-one association to Pregunta
	@ManyToOne
	@JoinColumn(name="id_pregunta")
	@JsonBackReference("pregunta-respuesta")
	private Pregunta pregunta;

	public Respuesta() {
	}

	public RespuestaPK getId() {
		return this.id;
	}

	public void setId(RespuestaPK id) {
		this.id = id;
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

}