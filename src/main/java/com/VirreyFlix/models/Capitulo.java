package com.VirreyFlix.models;

import com.VirreyFlix.HibernateUtil;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
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

        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        System.out.println("Ingresa el titulo del nuevo Capitulo: ");
        nTitulo = sc.nextLine();

        System.out.println("Ingresa la Duracion del nuevo Capitulo: ");
        nDuracion = sc.nextInt();

        System.out.println("Ingresa el ID de la Serie a la que pertenece: ");
        serieId = sc.nextInt();

        Serie se = s.find(Serie.class,serieId);

        Capitulo c = new Capitulo(nTitulo, nDuracion,se);

        s.persist(c);
        System.out.println("Capitulo creado con éxito");

        tx.commit();
        s.close();
    }

    public void modificarCapitulo() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        System.out.print("ID del Capitulo a modificar: ");
        int idModificar = sc.nextInt();
        sc.nextLine();
        Capitulo capituloModificar = s.find(Capitulo.class, idModificar);
        if (capituloModificar != null) {
            System.out.print("Nuevo Titulo: ");
            capituloModificar.setTitulo(sc.nextLine());
            System.out.print("Nueva Duracion: ");
            capituloModificar.setDuracion(sc.nextInt());
            sc.nextLine();

            s.getTransaction().begin();
            s.merge(capituloModificar);
            s.getTransaction().commit();

            System.out.println("Capitulo modificado con exito.");
        } else {
            System.out.println("Capitulo no encontrado.");
        }
    }

    public void eliminarCapitulo() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        System.out.print("ID del Capitulo a eliminar: ");
        int idEliminar = sc.nextInt();
        sc.nextLine();
        Capitulo capituloEliminar = s.find(Capitulo.class, idEliminar);
        if (capituloEliminar != null) {

            s.getTransaction().begin();
            s.remove(capituloEliminar);
            s.getTransaction().commit();

            System.out.println("Capitulo eliminado con exito.");
        } else {
            System.out.println("Capitulo no encontrado.");
        }
    }

    public void mostrarCapitulos() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Capitulo> capitulos = s.createQuery("FROM Capitulo ", Capitulo.class).getResultList();
        if (!capitulos.isEmpty()) {
            for (Capitulo capitulo : capitulos) {
                System.out.println("ID: " + capitulo.getId() + ", Titulo: " + capitulo.getTitulo() + ", Duracion: " + capitulo.getDuracion());
            }
        } else {
            System.out.println("No hay capitulos disponibles.");
        }
    }
}
