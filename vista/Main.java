package vista;

import controlador.PlayerController;
import modelo.Player;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static PlayerController playerController = new PlayerController(); // Crear una instancia del controlador

    public static void main(String[] args) {
        mostrarMenuInicial();
    }

    public static void mostrarMenuInicial() {
        int opcion = -1;
        do {
            try {
                System.out.println("\n--------MENU INICIAL--------");
                System.out.println("1. Llistar tots els jugadors d'un equip.");
                System.out.println("2. Calcular la mitjana de punts, rebots, asistències,... d'un jugador.");
                System.out.println("3. Llistar tots els partirs jugats per un equip amb el seu resultat.");
                System.out.println("4. Inserir un nou jugador a un equip.");
                System.out.println("5. Traspassar un jugador a un altra equip.");
                System.out.println("6. Actualitzar les dades de jugadors o equips després d'un partit.");
                System.out.println("7. Modificar les estadístiques d'un jugador.");
                System.out.println("8. Retirar (Eliminar) un jugador.");
                System.out.println("9. Canviar nom franquícia d'un equip.");
                System.out.println("0. Sortir de l'aplicació.");
                System.out.print("\nElige qué quieres hacer: ");

                opcion = scan.nextInt();
                scan.nextLine(); // Limpiar el búfer

                switch (opcion) {
                    case 1:
                        listarJugadoresDeUnEquipo();
                        break;
                    case 2:
                        // Otra funcionalidad
                        break;
                    case 3:
                        // Otra funcionalidad
                        break;
                    case 4:
                        // Otra funcionalidad
                        break;
                    case 5:
                        // Otra funcionalidad
                        break;
                    case 6:
                        // Otra funcionalidad
                        break;
                    case 7:
                        // Otra funcionalidad
                        break;
                    case 8:
                        // Otra funcionalidad
                        break;
                    case 9:
                        // Otra funcionalidad
                        break;
                    case 0:
                        System.out.println("Saliendo de la aplicación...");
                        break;
                    default:
                        System.out.println("¡ATENCIÓN! Debe ser un valor entre 0 y 9.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, introduce un número entero.");
                scan.nextLine(); // Limpiar el búfer
            } catch (Exception e) {
                System.out.println("Ocurrió un error genérico.");
                e.printStackTrace();
            }
        } while (opcion != 0);
    }

    private static void listarJugadoresDeUnEquipo() {
        String nombreEquipo;
        List<Player> jugadores = null;

        do {
            System.out.print("Introduce el nombre del equipo (o escribe 'regresar' para volver al menú): ");
            nombreEquipo = scan.nextLine();

            if (nombreEquipo.equalsIgnoreCase("regresar")) {
                return; // Volver al menú principal
            }

            // Validación del nombre del equipo
            if (!isValidTeamName(nombreEquipo)) {
                System.out.println("Nombre de equipo inválido. No debe contener números y no puede estar vacío. Inténtalo de nuevo.");
                continue; // Repetir el bucle si el nombre del equipo es inválido
            }

            jugadores = playerController.getPlayersByTeamName(nombreEquipo);

            if (jugadores.isEmpty()) {
                System.out.println("No se encontraron jugadores para el equipo " + nombreEquipo + ". Inténtalo de nuevo.");
            }
        } while (jugadores == null || jugadores.isEmpty());

        mostrarJugadores(nombreEquipo, jugadores);
    }

    private static void mostrarJugadores(String teamName, List<Player> players) {
        System.out.println("Jugadores del equipo " + teamName + ":");
        // Recorrer la lista de jugadores y mostrar sus nombres y apellidos.
        for (Player player : players) {
            System.out.println(player.getNom() + " " + player.getCognom());
        }
    }

    private static boolean isValidTeamName(String teamName) {
        // Verificar que el nombre del equipo no contenga números y no esté vacío
        if (teamName == null || teamName.trim().isEmpty()) {
            return false;
        }
        // Hacemos un recorrido por cada carácter del nombre del equipo para comprobar si hay algún dígito.
        for (char c : teamName.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
                // isDigit es un método de la clase Character que devuelve true si el carácter es un dígito.
            }
        }
        return true;
    }
}
