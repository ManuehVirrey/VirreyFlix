package com.VirreyFlix.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 200)
    String titulo;

    @Column(length = 100)
    String genero;

    int calificacion;

    @OneToMany(mappedBy = "serie")
    Set <Episodio> episodios= new HashSet<Episodio>();

    public Serie() {
    }

    public Serie(String titulo, String genero, int calificacion) {
        this.titulo = titulo;
        this.genero = genero;
        this.calificacion = calificacion;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Set<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(Set<Episodio> episodios) {
        this.episodios = episodios;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", calificacion=" + calificacion +
                '}';
    }
}
