package org.example;

import com.VirreyFlix.models.*;

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
            System.out.println("6. CRUD Capitulos");
            System.out.println("7. Consultas");
            System.out.println("0. Salir");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 ->usuarioCRUD();
                case 2 ->perfilCRUD();
                case 3 ->serieCRUD();
                case 4 ->episodioCRUD();
                case 5 ->historialCRUD();
                case 6 ->capituloCRUD();
                case 7 ->consultas();
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
    public static void episodioCRUD() {
        Episodio e = new Episodio();
        int opcionepi;
        do {
            System.out.println("Menu:");
            System.out.println("1. Crear Episodio");
            System.out.println("2. Mostrar Episodio");
            System.out.println("3. Actualizar Episodio");
            System.out.println("4. Eliminar Episodio");
            System.out.println("0. Volver al menu principal");
            opcionepi = sc.nextInt();

            switch (opcionepi) {
                case 1 -> e.crearEpisodio();
                case 2 -> e.mostrarEpisodios();
                case 3 -> e.actualizarEpisodio();
                case 4 -> e.eliminarEpisodio();
                case 0 -> menuPrincipal();
                default -> System.out.println("Opcion no valida");
            }
            sc.nextLine();
        } while (opcionepi != 0);
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
    private static void capituloCRUD() {
        Capitulo c = new Capitulo();
        int opcion;

        do {
            System.out.println("CRUD Capitulo:");
            System.out.println("1. Agregar Capitulo");
            System.out.println("2. Modificar Capitulo");
            System.out.println("3. Eliminar Capitulo");
            System.out.println("4. Mostrar Capitulo");
            System.out.println("0. Volver al menu principal");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1-> c.crearCapitulo();
                case 2-> c.modificarCapitulo();
                case 3-> c.eliminarCapitulo();
                case 4-> c.mostrarCapitulos();
                case 0-> System.out.println("Volviendo al menu principal...");
                default->System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }
    public static void consultas() {
        Consultas c = new Consultas();
        int opcioncon;
        do {
            System.out.println("Menu:");
            System.out.println("1. Mostrar Usuarios y Perfiles");
            System.out.println("2. Mostrar Series");
            System.out.println("3. Mostrar Series Con Filtro");
            System.out.println("4. Mostrar Capitulos por Serie");
            System.out.println("5. Mostrar Capitulos por ID");
            System.out.println("6. Agregar un Episodio al Historial");
            System.out.println("0. Volver al menu principal");
            opcioncon = sc.nextInt();

            switch (opcioncon) {
                case 1 -> c.mostrarUsuariosPerfil();
                case 2 -> c.mostrarSeries();
                case 3 -> c.mostrarSeriesFiltro();
                case 4 -> c.mostrarCapitulosSerie();
                case 5 -> c.mostrarCapitulosVistos();
                case 6 -> c.agregarCapitulosAHistorial();
                case 0 -> menuPrincipal();
                default -> System.out.println("Opcion no valida");
            }
            sc.nextLine();
        } while (opcioncon != 0);
    }
}