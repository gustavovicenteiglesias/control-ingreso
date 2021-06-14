package com.unsada.integradora.business.interfaces;

public interface ControlIngresoMapper<E, T> {

    E toEntity(T dto);

    T toDTO(E entity);

}
