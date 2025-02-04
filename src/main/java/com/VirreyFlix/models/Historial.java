package com.VirreyFlix.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Historial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    LocalDateTime fecha_reproduccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_id")
    Perfil perfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "episodio_id")
    Episodio episodio;

    public Historial() {
        this.fecha_reproduccion = LocalDateTime.now();
    }

    public Historial(LocalDateTime fecha_reproduccion) {
        this.fecha_reproduccion = fecha_reproduccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFecha_reproduccion() {
        return fecha_reproduccion;
    }

    public void setFecha_reproduccion(LocalDateTime fecha_reproduccion) {
        this.fecha_reproduccion = fecha_reproduccion;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Episodio getEpisodio() {
        return episodio;
    }

    public void setEpisodio(Episodio episodio) {
        this.episodio = episodio;
    }

    @Override
    public String toString() {
        return "Historial{" +
                "id=" + id +
                ", fecha_reproduccion=" + fecha_reproduccion +
                ", perfil=" + perfil +
                ", episodio=" + episodio +
                '}';
    }
}
