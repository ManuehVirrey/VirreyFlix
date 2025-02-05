package com.VirreyFlix.models;

import com.VirreyFlix.HibernateUtil;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

@Entity
public class Usuario {

    public static Scanner sc = new Scanner(System.in);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 100)
    String nombre;
    @Column(length = 100, unique = true)
    String email;
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Perfil perfil;

    public Usuario() {

    }

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public Usuario(String nombre, String email, Perfil perfil) {
        this.nombre = nombre;
        this.email = email;
        this.perfil = perfil;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void crearUsuario() {

        String nNombre, nEmail;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("Ingresa el nombre del nuevo Usuario: ");
        nNombre = sc.next();

        System.out.println("Ingresa el email del nuevo Usuario: ");
        nEmail = sc.next();

        Usuario u = new Usuario(nNombre, nEmail);
        session.persist(u);

        tx.commit();
        session.close();
    }

    public void mostrarUsuarios() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Usuario> listaUsuarios = session.createQuery("from Usuario", Usuario.class).list();

        for (Usuario u : listaUsuarios) {
            System.out.println("Usuarios: " + u.nombre + " Mail: " + u.email);
        }
        session.close();
    }

    public void actualizarUsuario() {
        String nNombre, nEmail;
        int id;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        System.out.println("Ingresa el id del usuario a modificar: ");
        id = sc.nextInt();
        for (Usuario u : session.createQuery("from Usuario", Usuario.class).list()) {
            if (u.getId() == id) {
                System.out.println("Ingresa el nuevo nombre: ");
                nNombre = sc.next();

                System.out.println("Ingresa el nuevo email: ");
                nEmail = sc.next();

                u.setNombre(nNombre);
                u.setEmail(nEmail);

                session.update(u);
                tx.commit();
                session.close();
                System.out.println("Usuario modificado exitosamente");
                return;
            } else {
                System.out.println("Id no encontrado");
                session.close();
                return;
            }
        }
    }

    public void eliminarUsuario() {
        int id;
        System.out.println("Ingresa el id del usuario a eliminar: ");
        id = sc.nextInt();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Usuario u = session.get(Usuario.class, id);
        session.remove(u);
        tx.commit();
        session.close();
        System.out.println("Usuario eliminado exitosamente");
    }
}