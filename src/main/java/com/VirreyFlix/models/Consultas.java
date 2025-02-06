package com.VirreyFlix.models;

import com.VirreyFlix.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class Consultas {

    public static Scanner sc = new Scanner(System.in);

    public void mostrarUsuariosPerfil() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Object[]> listaUsuariosPerfiles = s.createQuery(
                "SELECT u.nombre, u.email, p.nombre, p.edad " +
                        "FROM Usuario u " +
                        "LEFT JOIN u.perfil p",
                Object[].class).getResultList();
        for (Object[] o : listaUsuariosPerfiles) {
            System.out.println("Nombre Usuario: " + o[0]);
            System.out.println("Email Usuario: " + o[1]);
            System.out.println("Perfiles: ");
            if (o[2] != null) {
                System.out.println("Nombre Perfil: " + o[2]);
                System.out.println("Edad Perfil: " + o[3]);
            } else {
                System.out.println("No tiene perfil asociado.");
            }
            System.out.println("-----------------------------");
        }
    }

    public void mostrarSeries() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Object[]> listaSeries = s.createQuery(
                "SELECT s.titulo,s.calificacion,s.genero " +
                        "FROM Serie s", Object[].class).getResultList();
        for (Object[] o : listaSeries) {
            System.out.println("Titulo: " + o[0]);
            System.out.println("Calificacion: " + o[1]);
            System.out.println("Genero: " + o[2]);
            System.out.println("-----------------------------");
        }
    }

    public void mostrarSeriesFiltro() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        System.out.print("Para que edad quieres ver las series?: ");
        int edad = sc.nextInt();
        sc.nextLine();

        List<Serie> series = s.createQuery(
                "SELECT s FROM Serie s WHERE s.calificacion_edad >= :edad", Serie.class
        ).setParameter("edad", edad).getResultList();

        if (series.isEmpty()) {
            System.out.println("No hay series disponibles para la edad ingresada.");
        } else {
            for (Serie serie : series) {
                System.out.println("ID: " + serie.getId());
                System.out.println("Titulo: " + serie.getTitulo());
                System.out.println("Calificacion Edad: " + serie.calificacion_edad);
                System.out.println("-----------------------------");
            }
        }


    }

    public void mostrarCapitulosSerie() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        System.out.print("Ingresa el ID de la serie: ");
        int idSerie = sc.nextInt();
        sc.nextLine();

        List<Object[]> capitulos = s.createQuery(
                "SELECT c.titulo, c.serie"+
                        " FROM Capitulo c"+
                        " WHERE c.serie.id = :serie", Object[].class
        ).setParameter("serie", idSerie).getResultList();
        if (capitulos.isEmpty()) {
            System.out.println("No hay capitulos disponiblesen esta serie.");
        } else {
            for (Object[] o : capitulos) {
                System.out.println("Titulo: " + o[0]);
                System.out.println("-----------------------------");
            }
        }
    }

    public void mostrarCapitulosVistos() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        System.out.print("Ingrese el ID del usuario: ");
        int usuarioId = sc.nextInt();
        sc.nextLine();

        String hql = "SELECT s.titulo, e.titulo, e.duracion, h.episodio.duracion " +
                "FROM Historial h " +
                "JOIN h.episodio e " +
                "JOIN e.serie s " +
                "JOIN h.perfil p " +
                "JOIN p.usuario u " +
                "WHERE u.id = :usuarioId";

        List<Object[]> resultados = s.createQuery(hql, Object[].class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();

        if (resultados.isEmpty()) {
            System.out.println("El usuario no ha visto ningún capítulo.");
        } else {
            System.out.println("Capítulos vistos por el usuario:");
            for (Object[] row : resultados) {
                System.out.println("Serie: " + row[0] + " | Capítulo: " + row[1] +
                        " | Duración: " + row[2] + " min | Fecha: " + row[3]);
            }
        }
    }

    public void agregarCapitulosAHistorial() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        System.out.print("ID del Usuario: ");
        int usuarioId = sc.nextInt();
        sc.nextLine();
        System.out.print("ID de la Serie: ");
        int serieId = sc.nextInt();
        sc.nextLine();

        Usuario usuario = s.find(Usuario.class, usuarioId);
        Serie serie = s.find(Serie.class, serieId);
        if (usuario == null || serie == null) {
            System.out.println("Usuario o Serie no encontrados.");
            s.close();
            return;
        }

        Perfil perfil = usuario.getPerfil();
        if (perfil == null) {
            System.out.println("El usuario no tiene un perfil.");
            s.close();
            return;
        }

        Set<Episodio> capitulos = serie.getEpisodios();
        if (capitulos.isEmpty()) {
            System.out.println("No hay capítulos en esta serie.");
            s.close();
            return;
        }

        s.getTransaction().begin();

        for (Episodio capitulo : capitulos) {
            Historial historialExistente = s.createQuery(
                            "SELECT h FROM Historial h WHERE h.perfil = :perfil AND h.episodio = :episodio", Historial.class)
                    .setParameter("perfil", perfil)
                    .setParameter("episodio", capitulo)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (historialExistente != null) {
                historialExistente.setFecha_reproduccion(new Date());
                s.merge(historialExistente);
            } else {
                Historial nuevoHistorial = new Historial();
                nuevoHistorial.setPerfil(perfil);
                nuevoHistorial.setEpisodio(capitulo);
                nuevoHistorial.setFecha_reproduccion(new Date());
                s.persist(nuevoHistorial);
            }
        }

        s.getTransaction().commit();
        s.close();

        System.out.println("Capítulos añadidos al historial con su nueva fecha.");
    }
}



