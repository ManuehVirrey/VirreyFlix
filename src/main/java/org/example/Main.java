package org.example;

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
//                case 2 ->perfilCRUD();
//                case 3 ->serieCRUD();
//                case 4 ->episodioCRUD();
//                case 5 ->historialCRUD();
//                case 6 ->consultas();
                case 0 -> System.out.println("Saliendo de la aplicacion...");

                default -> System.out.println("Opcion no valida");
            }
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
        } while (opcionsur != 0);
    }
}