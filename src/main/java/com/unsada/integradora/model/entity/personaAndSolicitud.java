package com.unsada.integradora.model.entity;

import java.util.List;

public class personaAndSolicitud {
    private List<Solicitud> solicitudes;
    private List<Persona> personas;

    public personaAndSolicitud() {
    }

    public personaAndSolicitud(List<Solicitud> solicitudes, List<Persona> personas) {
        this.solicitudes = solicitudes;
        this.personas = personas;
    }

    public List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    
}

