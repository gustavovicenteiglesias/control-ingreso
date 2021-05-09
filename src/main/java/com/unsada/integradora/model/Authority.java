package com.unsada.integradora.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the authority database table.
 * 
 */
@Entity
@NamedQuery(name="Authority.findAll", query="SELECT a FROM Authority a")
public class Authority implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	@Column(name="id_authority")
	private int idAuthority;

	private String role;

	public Authority() {
	}

	public int getIdAuthority() {
		return this.idAuthority;
	}

	public void setIdAuthority(int idAuthority) {
		this.idAuthority = idAuthority;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}