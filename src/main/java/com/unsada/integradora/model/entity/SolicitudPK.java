package com.unsada.integradora.model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the solicitud database table.
 * 
 */
@Embeddable
public class SolicitudPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_solicitud")
	private int idSolicitud;

	@Column(name="id_sesion_presencial", insertable=false, updatable=false)
	private int idSesionPresencial;

	public SolicitudPK() {
	}
	public int getIdSolicitud() {
		return this.idSolicitud;
	}
	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	public int getIdSesionPresencial() {
		return this.idSesionPresencial;
	}
	public void setIdSesionPresencial(int idSesionPresencial) {
		this.idSesionPresencial = idSesionPresencial;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SolicitudPK)) {
			return false;
		}
		SolicitudPK castOther = (SolicitudPK)other;
		return 
			(this.idSolicitud == castOther.idSolicitud)
			&& (this.idSesionPresencial == castOther.idSesionPresencial);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idSolicitud;
		hash = hash * prime + this.idSesionPresencial;
		
		return hash;
	}
}