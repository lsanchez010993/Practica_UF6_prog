package controlador;

import modelo.Player;
import vista.Main;
import vista.Main2;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static vista.Main2.scan;


public class Validaciones {

    public static PlayerController playerController = new PlayerController();

    public static boolean isValidPlayerName(String playerName) {
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

    public static boolean isValidTeamName(String teamName) {
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

    public static boolean verificarJugador(String nombreJugador, int ejercicio) {
        boolean existe = false;
        List<Player> jugadores;
        int opcion;
        if (Validaciones.isValidPlayerName(nombreJugador)) {
            // Comprobar si existe en la BD:
            if (playerController.existPlayerName(nombreJugador)) {

                String[] nombrePartes = nombreJugador.split(" ");
                existe = true;
                if (nombrePartes.length == 1) {
                    jugadores = playerController.getPlayersByName(nombreJugador);
                    Validaciones.elegirJugadorDeLista(jugadores, ejercicio);

                } else if (nombrePartes.length == 2) {
                    jugadores = playerController.getPlayersByFullName(nombrePartes[0], nombrePartes[1]);
                    Validaciones.elegirJugadorDeLista(jugadores, ejercicio);
                } else {
                    System.out.println("Por favor, introduce solo el nombre o el nombre y el apellido del jugador.");

                }
            }else System.out.println("El nombre del jugador introducido no existe");
        }else System.out.println("El nombre introducido no es valido");


        return existe;
    }
    public static String elegirPosicion(Scanner scan) {
        String posicio;
        System.out.println("""
            1. Guard.
            2. Forward
            3. Center
            4. Forward-Guard
            5. Center-Forward
            6. Forward-Center
            7. Guard-Forward
            """);

        while (true) {
            try {
                int opcio = Integer.parseInt(scan.nextLine());

                switch (opcio) {
                    case 1:
                        posicio = "Guard";
                        break;
                    case 2:
                        posicio = "Forward";
                        break;
                    case 3:
                        posicio = "Center";
                        break;
                    case 4:
                        posicio = "Forward-Guard";
                        break;
                    case 5:
                        posicio = "Center-Forward";
                        break;
                    case 6:
                        posicio = "Forward-Center";
                        break;
                    case 7:
                        posicio = "Guard-Forward";
                        break;
                    default:
                        System.out.println("Opción no válida. Se asignará la posición por defecto.");
                        posicio = "Guard";
                        break;
                }


                break;

            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número válido.");
            }
        }

        return posicio;
    }

    public static void elegirJugadorDeLista(List<Player> players, int ejercicio) {
        System.out.println("Selecciona el jugador en la siguiente lista:");

        Map<Integer, String> teamNames = Main2.teamController.getAllTeamNames(); // Cargar todos los nombres de equipos en un Map
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            String teamName = teamNames.getOrDefault(player.getEquip_id(), "Desconocido"); // Obtener el nombre del equipo
            System.out.println((i + 1) + ". " + player.getNom() + " " + player.getCognom() + " (Equipo: " + teamName + ")");
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
                    switch (ejercicio) {
                        case 2:
                            Main.mostrarMediaJugador(players.get(opcion - 1));
                            break;
                        case 4:
                            Main2.traspasarJugador_a_Equipo(players.get(opcion - 1));
                            break;
                        case 5:
                            //ejercicio5
                            Main2.traspasarJugador_a_Equipo(players.get(opcion - 1));
                    }

                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, introduce un número entero.");
            }
        } while (opcion == -1);
    }
}



