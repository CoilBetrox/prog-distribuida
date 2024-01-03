package com.distribuida.db;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "persona")
@Data
public class Persona {

    @Id
    private Integer id;

    private String nombre;
    private String direccion;
    private Integer edad;

}
