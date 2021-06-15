package com.unsada.integradora.service.interfaces;

public interface LoginServiceInterface {
    public String encode(String x);
    public String decode(String y);
    public int length();
}
