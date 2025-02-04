package com.VirreyFlix.models;

import jakarta.persistence.*;

@Entity
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 100)
    String titulo;

    int duracion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serie_id")
    Serie serie;


    public Episodio() {
    }

    public Episodio(String titulo, int duracion) {
        this.titulo = titulo;
        this.duracion = duracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return "Episodio{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", duracion=" + duracion +
                '}';
    }
}
