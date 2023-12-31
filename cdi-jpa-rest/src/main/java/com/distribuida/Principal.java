package com.distribuida;

import com.distribuida.db.Persona;
import com.distribuida.servicios.ServicioPersona;
import com.google.gson.Gson;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.List;

import static spark.Spark.*;

public class Principal {

    static  SeContainer container;

    static List<Persona> listarPersonas(Request req, Response res){
        var servicio = container.select(ServicioPersona.class).get();

        res.type("application/json");
        return servicio.findAll();
    }

    static Persona buscarPersona(Request req, Response res){
        var servicio = container.select(ServicioPersona.class).get();

        res.type("application/json");
        String _id = req.params(":id");

        var persona = servicio.findById(Integer.valueOf(_id));
        if (persona == null){
            res.status(404);
            halt(404, "Persona no encontrada");
        }
        return persona;
    }

    static Persona crearPersona(Request req, Response res) {
        var servicio = container.select(ServicioPersona.class).get();
        res.type("application/json");
        Gson gson = new Gson();
        Persona nuevaPersona = gson.fromJson(req.body(), Persona.class);
        servicio.insert(nuevaPersona);
        return nuevaPersona;
    }

    static Persona actualizarPersona(Request req, Response res) {
        var servicio = container.select(ServicioPersona.class).get();
        res.type("application/json");
        Gson gson = new Gson();
        Persona personaActualizada = gson.fromJson(req.body(), Persona.class);
        servicio.update(personaActualizada);
        return personaActualizada;
    }

    static String eliminarPersona(Request req, Response res) {
        var servicio = container.select(ServicioPersona.class).get();
        res.type("application/json");
        String _id = req.params(":id");
        boolean eliminado = servicio.delete(Integer.valueOf(_id));

        if (eliminado) {
            return "Persona eliminada con éxito";
        } else {
            res.status(404);
            return "Persona no encontrada";
        }
    }

    public static void main(String[] args) {

        container = SeContainerInitializer.newInstance().initialize();

        ServicioPersona servicio = container.select(ServicioPersona.class).get();

        Spark.port(8081);

        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "*");
            response.header("Access-Control-Allow-Headers", "*");
        });



        Gson gson = new Gson();
        get("/personas", Principal::listarPersonas, gson::toJson);
        get("/personas/:id", Principal::buscarPersona, gson::toJson);
        post("/personas", Principal::crearPersona, gson::toJson);
        put("/personas/:id", Principal::actualizarPersona, gson::toJson);
        delete("/personas/:id", Principal::eliminarPersona, gson::toJson);

    }
}
