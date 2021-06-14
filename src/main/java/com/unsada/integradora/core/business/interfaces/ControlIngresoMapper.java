package com.unsada.integradora.core.business.interfaces;

public interface ControlIngresoMapper<E, T> {

    E toEntity(T dto);

    T toDTO(E entity);

}
