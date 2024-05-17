package vista;

import controlador.PlayerController;
import controlador.PlayerStatsController;
import modelo.Player;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static PlayerController playerController = new PlayerController(); // Crear una instancia del controlador
    static PlayerStatsController playerStatsController = new PlayerStatsController(); // Crear una instancia del controlador de estadísticas

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
                        calcularMediaJugador();
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
        for (Player player : players) {
            System.out.println(player.getNom() + " " + player.getCognom());
        }
    }

    private static boolean isValidTeamName(String teamName) {
        if (teamName == null || teamName.trim().isEmpty()) {
            return false;
        }
        for (char c : teamName.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static void calcularMediaJugador() {
        String nombreJugador;
        List<Player> jugadores = null;

        do {
            System.out.print("Introduce el nombre del jugador (o escribe 'regresar' para volver al menú): ");
            nombreJugador = scan.nextLine();

            if (nombreJugador.equalsIgnoreCase("regresar")) {
                return; // Volver al menú principal
            }

            // Validación del nombre del jugador
            if (!isValidPlayerName(nombreJugador)) {
                System.out.println("Nombre de jugador inválido. No debe contener números y no puede estar vacío. Inténtalo de nuevo.");
                continue; // Repetir el bucle si el nombre del jugador es inválido
            }

            jugadores = playerController.getPlayersByName(nombreJugador);

            if (jugadores.isEmpty()) {
                System.out.println("No se encontraron jugadores con el nombre " + nombreJugador + ". Inténtalo de nuevo.");
            }
        } while (jugadores == null || jugadores.isEmpty());

        if (jugadores.size() == 1) {
            mostrarMediaJugador(jugadores.get(0));
        } else {
            elegirJugadorDeLista(jugadores);
        }
    }

    private static void elegirJugadorDeLista(List<Player> players) {
        System.out.println("Se encontraron varios jugadores con el mismo nombre:");
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.println((i + 1) + ". " + player.getNom() + " " + player.getCognom() + " (ID: " + player.getJugadorId() + ")");
        }

        int opcion = -1;
        do {
            try {
                System.out.print("Elige el número del jugador (o escribe 'regresar' para volver al menú): ");
                String input = scan.nextLine();

                if (input.equalsIgnoreCase("regresar")) {
                    return; // Volver al menú principal
                }

                opcion = Integer.parseInt(input);

                if (opcion < 1 || opcion > players.size()) {
                    System.out.println("Opción inválida. Debe ser un número entre 1 y " + players.size());
                    opcion = -1; // Restablecer opción para mantener el bucle
                } else {
                    mostrarMediaJugador(players.get(opcion - 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, introduce un número entero.");
            }
        } while (opcion == -1);
    }

    private static void mostrarMediaJugador(Player player) {
        double[] medias = playerStatsController.calcularMediasJugador(player.getJugadorId());

        if (medias == null) {
            System.out.println("No se encontraron estadísticas para el jugador " + player.getNom() + " " + player.getCognom());
            return;
        }

        System.out.println("Estadísticas promedio de " + player.getNom() + " " + player.getCognom() + ":");
        System.out.println("Minutos Jugados: " + medias[0]);
        System.out.println("Puntos: " + medias[1]);
        System.out.println("Tiros Anotados: " + medias[2]);
        System.out.println("Tiros Tirados: " + medias[3]);
        System.out.println("Triples Anotados: " + medias[4]);
        System.out.println("Triples Tirados: " + medias[5]);
        System.out.println("Tiros Libres Anotados: " + medias[6]);
        System.out.println("Tiros Libres Tirados: " + medias[7]);
        System.out.println("Rebotes Ofensivos: " + medias[8]);
        System.out.println("Rebotes Defensivos: " + medias[9]);
        System.out.println("Asistencias: " + medias[10]);
        System.out.println("Robos: " + medias[11]);
        System.out.println("Bloqueos: " + medias[12]);
    }

    private static boolean isValidPlayerName(String playerName) {
        if (playerName == null || playerName.trim().isEmpty()) {
            return false;
        }
        for (char c : playerName.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
