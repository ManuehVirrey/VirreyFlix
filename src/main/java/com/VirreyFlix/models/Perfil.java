package com.VirreyFlix.models;

import com.VirreyFlix.HibernateUtil;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Entity
public class Perfil {
    public static Scanner sc = new Scanner(System.in);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private int edad;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL)
    private Set<Historial> historiales;


    public Perfil() {

    }

    public Perfil(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public Perfil(int id, String nombre, int edad,Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
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

    public void crearPerfil() {

        String nNombre;
        int nEdad;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("Ingresa el nombre del nuevo Perfil: ");
        nNombre = sc.nextLine();

        System.out.println("Ingresa la edad del nuevo Perfil: ");
        nEdad = sc.nextInt();

        Perfil p = new Perfil(nNombre, nEdad);

        session.persist(p);
        System.out.println("Perfil creado con éxito");

        tx.commit();
        session.close();
    }

    public void mostrarPerfil() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Perfil> listaPerfil = session.createQuery("from Perfil", Perfil.class).list();

        for (Perfil p : listaPerfil) {
            System.out.println("Usuarios: " + p.nombre + " Edad: " + p.edad);
        }
        session.close();
    }

    public void actualizarPerfil() {
        String nNombre;
        int id, nEdad, uId;
        Usuario u;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("Ingresa el id del usuario a modificar: ");
        id = sc.nextInt();
        for (Perfil p : session.createQuery("from Perfil", Perfil.class).list()) {
            if (p.getId() == id) {
                System.out.println("Ingresa el nuevo nombre del Perfil: ");
                nNombre = sc.next();
                sc.nextLine();

                System.out.println("Ingresa la nueva edad recomendada: ");
                nEdad = sc.nextInt();
                sc.nextLine();

                p.setNombre(nNombre);
                p.setEdad(nEdad);

                session.update(p);
                tx.commit();
                session.close();
                System.out.println("Perfil modificado exitosamente");
                return;
            } else {
                System.out.println("Id no encontrado");
                session.close();
                return;
            }
        }
    }

    public void eliminarPerfil() {
        int id;
        System.out.println("Ingresa el id del perfil a eliminar: ");
        id = sc.nextInt();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Perfil p = session.get(Perfil.class, id);
        session.remove(p);
        tx.commit();
        session.close();
        System.out.println("Perfil eliminado exitosamente");
    }
}
