package org.example;

import com.VirreyFlix.models.Historial;
import com.VirreyFlix.models.Perfil;
import com.VirreyFlix.models.Serie;
import com.VirreyFlix.models.Usuario;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        int opcion;
        do {
            System.out.println("Menu:");
            System.out.println("1. CRUD Usuario");
            System.out.println("2. CRUD Perfil");
            System.out.println("3. CRUD Serie");
            System.out.println("4. CRUD Episodio");
            System.out.println("5. CRUD Historial");
            System.out.println("6. Consultas");
            System.out.println("0. Salir");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 ->usuarioCRUD();
                case 2 ->perfilCRUD();
                case 3 ->serieCRUD();
//                case 4 ->episodioCRUD();
                case 5 ->historialCRUD();
//                case 6 ->consultas();
                case 0 -> System.out.println("Saliendo de la aplicacion...");
                default -> System.out.println("Opcion no valida");
            }
            sc.nextLine();
        }while (opcion != 0);
    }

    public static void usuarioCRUD() {
        Usuario u = new Usuario();
        int opcionsur;
        do {
            System.out.println("Menu:");
            System.out.println("1. Crear Usuario");
            System.out.println("2. Mostrar Usuarios");
            System.out.println("3. Actualizar Usuario");
            System.out.println("4. Eliminar Usuario");
            System.out.println("0. Volver al menu principal");
            opcionsur = sc.nextInt();

            switch (opcionsur) {
                case 1 -> u.crearUsuario();
                case 2 -> u.mostrarUsuarios();
                case 3 -> u.actualizarUsuario();
                case 4 -> u.eliminarUsuario();
                case 0 -> menuPrincipal();
                default -> System.out.println("Opcion no valida");
            }
            sc.nextLine();
        } while (opcionsur != 0);
    }
    public static void perfilCRUD() {
        Perfil p = new Perfil();
        int opcionper;
        do {
            System.out.println("Menu:");
            System.out.println("1. Crear Perfil");
            System.out.println("2. Mostrar Perfil");
            System.out.println("3. Actualizar Perfil");
            System.out.println("4. Eliminar Perfil");
            System.out.println("0. Volver al menu principal");
            opcionper = sc.nextInt();

            switch (opcionper) {
                case 1 -> p.crearPerfil();
                case 2 -> p.mostrarPerfil();
                case 3 -> p.actualizarPerfil();
                case 4 -> p.eliminarPerfil();
                case 0 -> menuPrincipal();
                default -> System.out.println("Opcion no valida");
            }
            sc.nextLine();
        } while (opcionper != 0);
    }
    public static void serieCRUD() {
        Serie s = new Serie();
        int opcionser;
        do {
            System.out.println("Menu:");
            System.out.println("1. Crear Serie");
            System.out.println("2. Mostrar Serie");
            System.out.println("3. Actualizar Serie");
            System.out.println("4. Eliminar Serie");
            System.out.println("0. Volver al menu principal");
            opcionser = sc.nextInt();

            switch (opcionser) {
                case 1 -> s.crearSerie();
                case 2 -> s.mostrarSeries();
                case 3 -> s.actualizarSerie();
                case 4 -> s.eliminarSerie();
                case 0 -> menuPrincipal();
                default -> System.out.println("Opcion no valida");
            }
            sc.nextLine();
        } while (opcionser != 0);
    }
    public static void historialCRUD() {
        Historial h = new Historial();
        int opcionhis;
        do {
            System.out.println("Menu:");
            System.out.println("1. Crear Historial");
            System.out.println("2. Mostrar Historial");
            System.out.println("3. Actualizar Historial");
            System.out.println("4. Eliminar Historial");
            System.out.println("0. Volver al menu principal");
            opcionhis = sc.nextInt();

            switch (opcionhis) {
                case 1 -> h.crearHistorial();
                case 2 -> h.mostrarHistorial();
                case 3 -> h.actualizarHistorial();
                case 4 -> h.eliminarHistorial();
                case 0 -> menuPrincipal();
                default -> System.out.println("Opcion no valida");
            }
            sc.nextLine();
        } while (opcionhis != 0);
    }
}