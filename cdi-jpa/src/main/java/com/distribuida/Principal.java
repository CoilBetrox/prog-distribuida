package com.distribuida;

import com.distribuida.db.Persona;
import com.distribuida.servicios.ServicioPersona;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Principal {
    public static void main(String[] args) {

        SeContainer container = SeContainerInitializer.newInstance().initialize();

        ServicioPersona servicio = container.select(ServicioPersona.class).get();

        Persona p = new Persona();
        //p.setId(1);
        p.setNombre("name2");
        p.setDireccion("direct2");
        p.setEdad(22);

        servicio.insert(p);

        servicio.findAll().stream().map(Persona::getNombre).forEach(System.out::println);




    }
}
