package com.unsada.integradora.model.dto;

public class EdificioDTO {
    private String direccion;
    private String nombre;
    private String nombreConSede;
    private int idEdificio;

    public void EdificioDTO(){

    }

    public EdificioDTO(String direccion, String nombre, String nombreConSede, int idEdificio) {
        this.direccion = direccion;
        this.nombre = nombre;
        this.nombreConSede = nombreConSede;
        this.idEdificio = idEdificio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreConSede() {
        return nombreConSede;
    }

    public void setNombreConSede(String nombreConSede) {
        this.nombreConSede = nombreConSede;
    }

    public int getIdEdificio() {
        return idEdificio;
    }

    public void setIdEdificio(int idEdificio) {
        this.idEdificio = idEdificio;
    }

}
