package com.distribuida.Servicios;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HolaImpl implements Hola{

    @Override
    public String test(String msj) {
        return "Hola" + msj;
    }
}
