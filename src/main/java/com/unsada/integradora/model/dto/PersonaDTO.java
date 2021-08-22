package com.unsada.integradora.model.dto;

public class PersonaDTO {
    private String dni;
    private String nombre;
    private  String mail;
    private String telefono;

    public PersonaDTO(String dni, String nombre, String correoElectronico, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.mail = correoElectronico;
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return mail;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.mail = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
