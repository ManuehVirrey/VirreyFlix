package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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

        }
        while (opcion != 0);

        sc.close();
    }
}