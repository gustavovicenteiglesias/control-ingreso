package com.unsada.integradora.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;


/**
 * The persistent class for the solicitud database table.
 * 
 */
@Entity
@NamedQuery(name="Solicitud.findAll", query="SELECT s FROM Solicitud s")
public class Solicitud implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@EmbeddedId
	private SolicitudPK id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_carga")
	private Date fechaCarga;

	private byte presente;

	@Column(name="qr_acceso")
	private String qrAcceso;

	//bi-directional many-to-one association to Ddjj
	@ManyToOne
	@JoinColumn(name="id_ddjj")
	//@JsonIgnore
	@JsonBackReference("solicitud-ddjj")
	private Ddjj ddjj;

	//bi-directional many-to-one association to SesionPresencial
	@ManyToOne
	@JoinColumn(name="id_sesion_presencial", insertable = false, updatable = false)
	//@JsonIgnore
	@JsonBackReference("solicitud-sessionpresencial")
	private SesionPresencial sesionPresencial;

	public Solicitud() {
	}

	public SolicitudPK getId() {
		return this.id;
	}

	public void setId(SolicitudPK id) {
		this.id = id;
	}

	public Date getFechaCarga() {
		return this.fechaCarga;
	}

	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public byte getPresente() {
		return this.presente;
	}

	public void setPresente(byte presente) {
		this.presente = presente;
	}

	public String getQrAcceso() {
		return this.qrAcceso;
	}

	public void setQrAcceso(String qrAcceso) {
		this.qrAcceso = qrAcceso;
	}

	public Ddjj getDdjj() {
		return this.ddjj;
	}

	public void setDdjj(Ddjj ddjj) {
		this.ddjj = ddjj;
	}

	public SesionPresencial getSesionPresencial() {
		return this.sesionPresencial;
	}

	public void setSesionPresencial(SesionPresencial sesionPresencial) {
		this.sesionPresencial = sesionPresencial;
	}

}