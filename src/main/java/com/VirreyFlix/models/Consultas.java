package com.VirreyFlix.models;

import com.VirreyFlix.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;


public class Consultas {

    Scanner sc = new Scanner(System.in);

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
}


