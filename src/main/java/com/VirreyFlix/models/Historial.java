package com.VirreyFlix.models;

import com.VirreyFlix.HibernateUtil;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Entity
public class Historial {

    private static final Scanner sc = new Scanner(System.in);

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

    public void crearHistorial() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Ingresa el ID del perfil: ");
        int perfilId = sc.nextInt();
        Perfil perfil = session.get(Perfil.class, perfilId);
        if (perfil == null) {
            System.out.println("Perfil no encontrado.");
            session.close();
            return;
        }

        System.out.print("Ingresa el ID del episodio: ");
        int episodioId = sc.nextInt();
        Episodio episodio = session.get(Episodio.class, episodioId);
        if (episodio == null) {
            System.out.println("Episodio no encontrado.");
            session.close();
            return;
        }

        Historial historial = new Historial();
        historial.setPerfil(perfil);
        historial.setEpisodio(episodio);
        historial.setFecha_reproduccion(LocalDateTime.now());

        session.persist(historial);
        tx.commit();
        session.close();
        System.out.println("Historial registrado correctamente.");
    }

    public void mostrarHistorial() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Historial> listaHistorial = session.createQuery("FROM Historial", Historial.class).list();
        session.close();
        for (Historial h : listaHistorial) {
            System.out.println("ID: " + h.getId() +
                    " Perfil: " + h.getPerfil().getNombre() +
                    " Episodio: " + h.getEpisodio().getTitulo() +
                    " Fecha: " + h.getFecha_reproduccion());
        }
    }

    public void actualizarHistorial() {
        System.out.print("Ingrese el ID del historial a actualizar: ");
        int id = sc.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Historial historial = session.get(Historial.class, id);

        if (historial != null) {
            System.out.print("Ingresa el nuevo ID del perfil: ");
            int perfilId = sc.nextInt();
            Perfil nuevoPerfil = session.get(Perfil.class, perfilId);
            if (nuevoPerfil == null) {
                System.out.println("Perfil no encontrado.");
                session.close();
                return;
            }

            System.out.print("Ingresa el nuevo ID del episodio: ");
            int episodioId = sc.nextInt();
            Episodio nuevoEpisodio = session.get(Episodio.class, episodioId);
            if (nuevoEpisodio == null) {
                System.out.println("Episodio no encontrado.");
                session.close();
                return;
            }

            historial.setPerfil(nuevoPerfil);
            historial.setEpisodio(nuevoEpisodio);
            historial.setFecha_reproduccion(LocalDateTime.now());

            session.merge(historial);
            tx.commit();
            System.out.println("Historial actualizado exitosamente.");
        } else {
            System.out.println("Historial no encontrado.");
        }
        session.close();
    }

    public void eliminarHistorial() {
        System.out.print("Ingresa el ID del historial a eliminar: ");
        int id = sc.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Historial historial = session.get(Historial.class, id);

        if (historial != null) {
            session.remove(historial);
            tx.commit();
            System.out.println("Historial eliminado correctamente.");
        } else {
            System.out.println("Historial no encontrado.");
        }

        session.close();
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