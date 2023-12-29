package com.distribuida.servicios;

import com.distribuida.db.Persona;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class ServicioPersonaImpl implements ServicioPersona{

    @Inject
    EntityManager em;

    @Override
    public List<Persona> findAll() {
        return em.createQuery("select o from Persona o").getResultList();
    }


    public void insert(Persona p){
        var tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(p);
            tx.commit();
        }catch (Exception ex){
            tx.rollback();
            //ex.printStackTrace();
        }
    }

    @Override
    public Persona findById(Integer id) {
        return em.find(Persona.class, id);
    }

    @Override
    public void update(Persona p) {
        var tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(p);
            tx.commit();
        }catch (Exception ex){
            tx.rollback();
        }
    }

    @Override
    public boolean delete(Integer id) {
        var tx = em.getTransaction();
        try {
            tx.begin();
            Persona persona = em.find(Persona.class, id);
            if (persona != null){
                em.remove(persona);
                tx.commit();
                return true;
            }else {
                tx.rollback();
                return false;
            }
        }catch (Exception ex){
            tx.rollback();
            return false;
        }
    }


}
