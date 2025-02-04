package com.VirreyFlix.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 100)
    String nombre;

    int edad;

    @OneToOne(fetch = FetchType.LAZY)
    Usuario usuario;

    @OneToMany(mappedBy = "perfil")
    Set<Historial> historiales = new HashSet<Historial>();

    public Perfil() {

    }

    public Perfil(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Historial> getHistoriales() {
        return historiales;
    }

    public void setHistoriales(Set<Historial> historiales) {
        this.historiales = historiales;
    }
}
