package controlador;

import modelo.Player;
import vista.Main;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class Validaciones {
    public static PlayerStatsController playerStatsController = new PlayerStatsController();
    public static PlayerController playerController = new PlayerController();
    public static TeamController teamController = new TeamController();
    public static MatchController matchController = new MatchController();
    public static Scanner scan = new Scanner(System.in);
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
            } else {
                System.out.println("El nombre del jugador introducido no existe");
            }
        } else {
            System.out.println("El nombre introducido no es valido");
        }

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
                        System.out.println("Opción no válida. Se asignará la posición 'Guard' por defecto.");
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

    public static Player elegirJugadorDeLista(List<Player> players, int ejercicio) {
        System.out.println("Selecciona el jugador en la siguiente lista:");

        Map<Integer, String> teamNames = teamController.getAllTeamNames(); // Cargar todos los nombres de equipos en un Map
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
                    return null; // Volver al menú principal
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
                        case 4, 5:
                            Main.traspasarJugador_a_Equipo(players.get(opcion - 1));
                            break;
                        case 7:
                            return players.get(opcion - 1);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, introduce un número entero.");
            }
        } while (opcion == -1);
        return null;
    }

    public static boolean leerArchivo(String nomArchivo) {
        String rutaArchivo = "./actualizarDatos/" + nomArchivo;

        FileInputStream inputStream = null;
        BufferedReader reader = null;
        try {
            File file = new File(rutaArchivo);

            if (file.exists()) {

                inputStream = new FileInputStream(file);
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String linea;
                while ((linea = reader.readLine()) != null) {

                    if (linea.equals("estadistiquesJugador")) {
                        System.out.println("Actualizando datos de jugadores. Espera");
                        String valor = reader.readLine();
                        String[] estadisticasJugadores = valor.split(", ");
                        int[] valorInt = new int[estadisticasJugadores.length];
                        for (int i = 0; i < estadisticasJugadores.length; i++) {
                            if (i != 2) {
                                valorInt[i] = Integer.parseInt(estadisticasJugadores[i]);
                                if (valorInt[i] < 0) {
                                    System.out.println("Error: No se permiten valores negativos. Línea: " + valor);
                                    return false;
                                }
                            }
                        }
                        double minutosJugados = Double.parseDouble(estadisticasJugadores[2]);
                        if (minutosJugados < 0) {
                            System.out.println("Error: No se permiten valores negativos. Línea: " + valor);
                            return false;
                        }
                        boolean actualizado = playerStatsController.updatePlayerStats(
                                valorInt[0], valorInt[1], minutosJugados, valorInt[3], valorInt[4],
                                valorInt[5], valorInt[6], valorInt[7], valorInt[8], valorInt[9],
                                valorInt[10], valorInt[11], valorInt[12], valorInt[13], valorInt[14]
                        );
                        if (!actualizado) {
                            System.out.println("Error al actualizar estadísticas del jugador.");

                        }
                    } else if (linea.equals("partits")) {
                        System.out.println("Actualizando datos de partidos. Espera");
                        String valor = reader.readLine();
                        String[] estadisticasPartido = valor.split(", ");
                        Date fechaPartido = parseDate(estadisticasPartido[2]);
                        boolean actualizado = matchController.updateMatch(
                                Integer.parseInt(estadisticasPartido[0]),
                                Integer.parseInt(estadisticasPartido[1]),
                                fechaPartido,
                                estadisticasPartido[3],
                                estadisticasPartido[4]
                        );
                        if (!actualizado) {
                            System.out.println("Error al actualizar datos del partido.");
                            return false;
                        }
                    }

                }
            } else {
                System.out.println("Archivo no encontrado");
                return false;
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return false;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return true;
    }

    private static Date parseDate(String dataNaixement) {
        SimpleDateFormat[] formatters = {
                new SimpleDateFormat("yyyy-MM-dd"),
                new SimpleDateFormat("yyyy-M-d")
        };
        for (SimpleDateFormat formatter : formatters) {
            try {
                formatter.setLenient(false);
                return formatter.parse(dataNaixement);
            } catch (ParseException e) {
                // Ignorar y probar el siguiente formato
            }
        }
        return null; // Si no se pudo analizar, devolver null
    }
}
