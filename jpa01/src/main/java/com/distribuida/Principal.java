package com.distribuida;

import com.distribuida.db.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Principal {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu-distribuida");
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            var p = new Persona();
            //p.setId(1);
            p.setNombre("Daniel");
            p.setEdad(20);
            p.setDireccion("Direction");
            em.persist(p);
            em.getTransaction().commit();

            TypedQuery<Persona> qry = em.createQuery("select p from Persona p", Persona.class);
            var listaPersonas = qry.getResultList();


            for (Persona per: listaPersonas
                 ) {
                System.out.println(per.toString());
            }

        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }


    }
}
