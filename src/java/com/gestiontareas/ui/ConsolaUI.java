package java.com.gestiontareas.ui;

import java.com.gestiontareas.model.Tarea;
import java.com.gestiontareas.service.TareaService;
import java.com.gestiontareas.service.exception.TareaNoEncontradaException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsolaUI {
    private final TareaService gestor;
    private final Scanner scanner;

    public ConsolaUI(TareaService gestor, Scanner scanner) {
        this.gestor = gestor;
        this.scanner = scanner;
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 4);
    }

    private void mostrarMenu() {
        System.out.println("\n--- Gestor de Tareas V2 (Clean Code) ---");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Listar tareas");
        System.out.println("3. Completar tarea");
        System.out.println("4. Salir");
        System.out.print("Opción: ");
    }

    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 :
                agregarTareaInteractivo();
                break;
            case 2 :
                listarTareasInteractivo();
                break;
            case 3 :
                completarTareaInteractivo();
                break;
            case 4 :
                System.out.println("¡Hasta luego!");
                break;
            default :
                System.out.println("Opción inválida.");
                break;
        }
    }

    private void agregarTareaInteractivo() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Fecha límite (YYYY-MM-DD): ");
        String fechaStr = scanner.nextLine();
        try {
            LocalDate fecha = LocalDate.parse(fechaStr);
            Tarea tarea = gestor.agregarTarea(titulo, fecha);
            System.out.println("Tarea agregada con ID: " + tarea.getId());
        } catch (DateTimeParseException e) {
            System.out.println("Error: formato de fecha inválido. Use YYYY-MM-DD.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarTareasInteractivo() {
        java.util.List<Tarea> tareas = gestor.listarTareas();
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
        } else {
            tareas.forEach(System.out::println);
        }
    }

    private void completarTareaInteractivo() {
        System.out.print("ID de la tarea a completar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            gestor.marcarComoCompletada(id);
            System.out.println("Tarea marcada como completada.");
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un número.");
        } catch (TareaNoEncontradaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
