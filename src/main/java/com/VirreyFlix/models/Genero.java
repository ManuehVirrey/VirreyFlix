package com.VirreyFlix.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @ManyToMany(mappedBy = "generos")
    private List<Serie> series;

    public Genero() {
    }

    public Genero(int id, String nombre, List<Serie> series) {
        this.id = id;
        this.nombre = nombre;
        this.series = series;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }
}

