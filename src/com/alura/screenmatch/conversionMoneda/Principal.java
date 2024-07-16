package com.alura.screenmatch.conversionMoneda;
import menu.MenuHandler;
import java.util.Scanner;
import java.util.InputMismatchException;

import static java.awt.SystemColor.menu;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("\n********************************************************************");
        System.out.println("Convertidor de Monedas- Challenge Backend ONE.");
        try {
            Convertir conversion = new Convertir(); // Crear una instancia de Conversion
            do {
                MenuHandler.mostrarMenu();
                int opcion = lectura.nextInt();
                MenuHandler.ejecutarOpcion(opcion, conversion, lectura); // Pasar Conversion y Scanner
            } while (true);
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un número válido.");
        } finally {
            lectura.close();
        }
    }
}

