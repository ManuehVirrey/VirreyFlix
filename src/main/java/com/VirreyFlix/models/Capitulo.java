package com.VirreyFlix.models;

import com.VirreyFlix.HibernateUtil;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

@Entity
public class Capitulo {

    public static Scanner sc = new Scanner(System.in);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;
    private int duracion;

    @ManyToOne
    @JoinColumn(name = "serie_id")
    private Serie serie;

    public Capitulo() {
    }

    public Capitulo(String titulo, int duracion) {
        this.titulo = titulo;
        this.duracion = duracion;
    }

    public Capitulo(String titulo, int duracion, Serie serie) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.serie = serie;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getDuracion() { return duracion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }
    public Serie getSerie() { return serie; }
    public void setSerie(Serie serie) { this.serie = serie; }

    public void crearCapitulo() {

        String nTitulo;
        int nDuracion,serieId;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("Ingresa el titulo del nuevo Capitulo: ");
        nTitulo = sc.nextLine();

        System.out.println("Ingresa la Duracion del nuevo Capitulo: ");
        nDuracion = sc.nextInt();

        System.out.println("Ingresa el ID de la Serie a la que pertenece: ");
        serieId = sc.nextInt();

        Serie se = session.find(Serie.class,serieId);

        Capitulo c = new Capitulo(nTitulo, nDuracion,se);

        session.persist(c);
        System.out.println("Capitulo creado con éxito");

        tx.commit();
        session.close();
    }
}
