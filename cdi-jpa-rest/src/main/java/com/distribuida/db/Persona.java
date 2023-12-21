package com.distribuida.db;

import jakarta.persistence.*;

@Entity
@Table(name = "persona")
public class Persona {

    @Id
    private Integer id;

    private String nombre;
    private String direccion;
    private Integer edad;

    public Persona() {
    }

    public Persona(Integer id, String nombre, String direccion, Integer edad) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.edad = edad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", edad=" + edad +
                '}';
    }
}
