package com.unsada.integradora.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the respuesta database table.
 * 
 */
@Embeddable
public class RespuestaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_respuesta")
	private int idRespuesta;

	@Column(name="id_ddjj")
	private int idDdjj;

	public RespuestaPK() {
	}
	public int getIdRespuesta() {
		return this.idRespuesta;
	}
	public void setIdRespuesta(int idRespuesta) {
		this.idRespuesta = idRespuesta;
	}
	public int getIdDdjj() {
		return this.idDdjj;
	}
	public void setIdDdjj(int idDdjj) {
		this.idDdjj = idDdjj;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RespuestaPK)) {
			return false;
		}
		RespuestaPK castOther = (RespuestaPK)other;
		return 
			(this.idRespuesta == castOther.idRespuesta)
			&& (this.idDdjj == castOther.idDdjj);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idRespuesta;
		hash = hash * prime + this.idDdjj;
		
		return hash;
	}
}