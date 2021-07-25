package com.unsada.integradora.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


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

	private Boolean afirmativo;

	//bi-directional many-to-one association to Ddjj
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_ddjj" )

	private Ddjj ddjj;

	//bi-directional many-to-one association to Pregunta
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_pregunta")

	private Pregunta pregunta;

	public Respuesta() {
	}

	

	public Integer getId_respuesta() {
		return id_respuesta;
	}



	public void setId_respuesta(Integer id_respuesta) {
		this.id_respuesta = id_respuesta;
	}



	

	public Boolean getAfirmativo() {
		return afirmativo;
	}



	public void setAfirmativo(Boolean afirmativo) {
		this.afirmativo = afirmativo;
	}


	@JsonIgnore
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