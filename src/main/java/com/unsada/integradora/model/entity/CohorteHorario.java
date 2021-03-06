package com.unsada.integradora.model.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the cohorte_horario database table.
 * 
 */
@Entity
@Table(name="cohorte_horario")

public class CohorteHorario implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id_cohorte_horario")
	private int idCohorteHorario;

	//bi-directional many-to-one association to Cohorte
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_cohorte")
	
	@JsonBackReference("cohorte-horario")
	private Cohorte cohorte;

	//bi-directional many-to-one association to Horario
	@ManyToOne(cascade = CascadeType.ALL)
	//@JsonIgnore
	@JsonBackReference("horario-cohortehorario")
	@JoinColumn(name="id_horario")
	private Horario horario;

	//bi-directional many-to-one association to SesionPresencial
	@OneToMany(mappedBy="cohorteHorario", cascade = CascadeType.ALL)
	@NotFound(action= NotFoundAction.IGNORE)
	@JsonIgnore
	//@JsonManagedReference("SesionPresencial-cohorteHorario")
	private List<SesionPresencial> sesionPresencials;

	public CohorteHorario() {
	}

	public int getIdCohorteHorario() {
		return this.idCohorteHorario;
	}

	public void setIdCohorteHorario(int idCohorteHorario) {
		this.idCohorteHorario = idCohorteHorario;
	}

	public Cohorte getCohorte() {
		return this.cohorte;
	}

	public void setCohorte(Cohorte cohorte) {
		this.cohorte = cohorte;
	}

	public Horario getHorario() {
		return this.horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public List<SesionPresencial> getSesionPresencials() {
		return this.sesionPresencials;
	}

	public void setSesionPresencials(List<SesionPresencial> sesionPresencials) {
		this.sesionPresencials = sesionPresencials;
	}

	public SesionPresencial addSesionPresencial(SesionPresencial sesionPresencial) {
		getSesionPresencials().add(sesionPresencial);
		sesionPresencial.setCohorteHorario(this);

		return sesionPresencial;
	}

	public SesionPresencial removeSesionPresencial(SesionPresencial sesionPresencial) {
		getSesionPresencials().remove(sesionPresencial);
		sesionPresencial.setCohorteHorario(null);

		return sesionPresencial;
	}

	@Override
	public String toString() {
		return "CohorteHorario{" +
				"idCohorteHorario=" + idCohorteHorario +
				", cohorte=" + cohorte +
				", horario=" + horario +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CohorteHorario that = (CohorteHorario) o;
		return idCohorteHorario == that.idCohorteHorario && Objects.equals(cohorte, that.cohorte) && Objects.equals(horario, that.horario) && Objects.equals(sesionPresencials, that.sesionPresencials);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCohorteHorario, cohorte, horario, sesionPresencials);
	}
}