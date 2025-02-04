package com.VirreyFlix.models;

import com.VirreyFlix.HibernateUtil;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Entity
public class Episodio {

    private static final Scanner sc = new Scanner(System.in);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String titulo;
    int duracion;

    @ManyToOne
    @JoinColumn(name = "serie_id", nullable = false)
    Serie serie;

    @OneToMany(mappedBy = "episodio", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Historial> historial;


    public Episodio() {
    }

    public Episodio(String titulo, int duracion) {
        this.titulo = titulo;
        this.duracion = duracion;
    }

    public Episodio(String titulo, int duracion, Serie serie) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.serie = serie;
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

    public void crearEpisodio() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            System.out.print("Ingresa el título del episodio: ");
            String titulo = sc.nextLine();

            System.out.print("Ingresa la duración del episodio (en minutos): ");
            int duracion = sc.nextInt();
            sc.nextLine();

            System.out.print("Ingresa el ID de la serie a la que pertenece: ");
            int serieId = sc.nextInt();
            Serie serie = session.get(Serie.class, serieId);
            if (serie == null) {
                System.out.println("Serie no encontrada.");
                return;
            }

            Episodio episodio = new Episodio(titulo, duracion, serie);
            session.persist(episodio);
            tx.commit();

            System.out.println("Episodio creado correctamente.");
        }
    }

    public void mostrarEpisodios() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Episodio> episodios = session.createQuery("FROM Episodio", Episodio.class).list();
            if (episodios.isEmpty()) {
                System.out.println("No hay episodios registrados.");
                return;
            }
            for (Episodio e : episodios) {
                System.out.println("ID: " + e.getId() +
                        ", Título: " + e.getTitulo() +
                        ", Duración: " + e.getDuracion() + " minutos" +
                        ", Serie: " + e.getSerie().getTitulo());
            }
        }
    }

    public void actualizarEpisodio() {
        System.out.print("Ingresa el ID del episodio a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Episodio episodio = session.get(Episodio.class, id);
            if (episodio == null) {
                System.out.println("Episodio no encontrado.");
                return;
            }

            System.out.print("Ingresa el nuevo título del episodio: ");
            String nuevoTitulo = sc.nextLine();
            episodio.setTitulo(nuevoTitulo);

            System.out.print("Ingresa la nueva duración del episodio: ");
            int nuevaDuracion = sc.nextInt();
            episodio.setDuracion(nuevaDuracion);

            sc.nextLine();
            System.out.print("Ingresa el nuevo ID de la serie: ");
            int serieId = sc.nextInt();
            Serie nuevaSerie = session.get(Serie.class, serieId);
            if (nuevaSerie == null) {
                System.out.println("Serie no encontrada.");
                return;
            }
            episodio.setSerie(nuevaSerie);

            session.merge(episodio);
            tx.commit();
            System.out.println("Episodio actualizado correctamente.");
        }
    }

    public void eliminarEpisodio() {
        System.out.print("Ingresa el ID del episodio a eliminar: ");
        int id = sc.nextInt();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Episodio episodio = session.get(Episodio.class, id);
            if (episodio == null) {
                System.out.println("Episodio no encontrado.");
                return;
            }
            session.remove(episodio);
            tx.commit();
            System.out.println("Episodio eliminado correctamente.");
        }
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
