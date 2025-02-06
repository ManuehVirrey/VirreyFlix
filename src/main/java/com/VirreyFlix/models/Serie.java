package com.VirreyFlix.models;

import com.VirreyFlix.HibernateUtil;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Entity
public class Serie {

    public static Scanner sc = new Scanner(System.in);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 200)
    String titulo;

    @Column(length = 100)
    String genero;

    int calificacion;

    int calificacion_edad;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Episodio> episodios;

    public Serie() {
    }

    public Serie(String titulo, String genero, int calificacion, int calificacion_edad) {
        this.titulo = titulo;
        this.genero = genero;
        this.calificacion = calificacion;
        this.calificacion_edad = calificacion_edad;
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

    public int getCalificacion_edad() {
        return calificacion_edad;
    }

    public void setCalificacion_edad(int calificacion_edad) {
        this.calificacion_edad = calificacion_edad;
    }

    public void crearSerie() {
        System.out.println("Ingresa el título de la nueva Serie: ");
        String nTitulo = sc.nextLine();

        System.out.println("Ingresa el género de la nueva Serie: ");
        String nGenero = sc.nextLine();

        System.out.println("Ingresa la calificación de la nueva Serie (1-10): ");
        int nCalificacion = sc.nextInt();

        System.out.println("Ingresa la edad de la nueva Serie : ");
        int nCalificacionEdad = sc.nextInt();
        sc.nextLine();

        Serie nuevaSerie = new Serie(nTitulo, nGenero, nCalificacion,nCalificacionEdad);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(nuevaSerie);

        tx.commit();
        session.close();

        System.out.println("Serie creada exitosamente.");
    }

    public void mostrarSeries() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Serie> listaSeries = session.createQuery("from Serie ", Serie.class).list();

        for (Serie s : listaSeries) {
            System.out.println("Serie: " + s.titulo + " Calificacion: " + s.calificacion + " Genero: " + s.genero);
        }
        session.close();
    }

    public void actualizarSerie() {
        String nTitulo, genero;
        int id, nCalificacion;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("Ingresa el id de la serie a modificar: ");
        id = sc.nextInt();
        for (Serie s : session.createQuery("from Serie ", Serie.class).list()) {
            if (s.getId() == id) {
                System.out.println("Ingresa el nuevo titulo de la Serie: ");
                nTitulo = sc.nextLine();

                System.out.println("Ingresa el nuevo genero de la Serie: ");
                genero = sc.nextLine();

                System.out.println("Ingresa la nueva calificacion: ");
                nCalificacion = sc.nextInt();
                sc.nextLine();

                s.setTitulo(nTitulo);
                s.setCalificacion(nCalificacion);
                s.setGenero(genero);

                session.update(s);
                tx.commit();
                session.close();
                System.out.println("Serie modificada exitosamente");
                return;
            } else {
                System.out.println("Id no encontrado");
                session.close();
                return;
            }
        }
    }

    public void eliminarSerie() {
        System.out.println("Ingresa el ID de la serie a eliminar: ");
        int id = sc.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Serie s = session.get(Serie.class, id);

        if (s != null) {
            String deleteHistorial = "DELETE FROM Historial WHERE episodio.id IN (SELECT id FROM Episodio WHERE serie.id = :serieId)";
            session.createQuery(deleteHistorial)
                    .setParameter("serieId", id)
                    .executeUpdate();

            String deleteEpisodios = "DELETE FROM Episodio WHERE serie.id = :serieId";
            session.createQuery(deleteEpisodios)
                    .setParameter("serieId", id)
                    .executeUpdate();

            session.remove(s);
            tx.commit();
            System.out.println("Serie eliminada exitosamente.");
        } else {
            System.out.println("No se encontró una serie con ese ID.");
        }

        session.close();
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
