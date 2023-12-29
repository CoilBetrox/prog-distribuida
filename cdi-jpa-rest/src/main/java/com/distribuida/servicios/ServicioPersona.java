package com.distribuida.servicios;

import com.distribuida.db.Persona;

import java.util.List;

public interface ServicioPersona {
    List<Persona> findAll();
    void insert(Persona p);
    Persona findById(Integer id);
    void update(Persona p);
    boolean delete(Integer id);
}
