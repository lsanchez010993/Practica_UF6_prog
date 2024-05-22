package vista;

import controlador.MatchController;
import controlador.PlayerController;
import controlador.PlayerStatsController;
import controlador.TeamController;
import modelo.Match;
import modelo.Player;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static MatchController matchController = new MatchController();
    static Scanner scan = new Scanner(System.in);
    static TeamController teamController = new TeamController();
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
                System.out.println("1. Llistar tots els jugadors d'un equip.");//Hecho
                System.out.println("2. Calcular la mitjana de punts, rebots, asistències,... d'un jugador.");//Hecho
                System.out.println("3. Llistar tots els partirs jugats per un equip amb el seu resultat."); //Falta
                System.out.println("4. Inserir un nou jugador a un equip.");//Falta
                System.out.println("5. Traspassar un jugador a un altra equip.");//Hecho
                System.out.println("6. Actualitzar les dades de jugadors o equips després d'un partit.");//Falta
                System.out.println("7. Modificar les estadístiques d'un jugador.");//Falta
                System.out.println("8. Retirar (Eliminar) un jugador.");//Hecho
                System.out.println("9. Canviar nom franquícia d'un equip."); //Hecho
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
                        listarPartidoPorEquipo();
                        break;
                    case 4:
                        // Otra funcionalidad
                        break;
                    case 5:
                        traspasarJugador_a_Equipo();
                        break;
                    case 6:
                        // Otra funcionalidad
                        break;
                    case 7:
                        // Otra funcionalidad
                        break;
                    case 8:
                        retirarJugador();
                        break;
                    case 9:
                        actualizarNombreEquipo();
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

            String[] nombrePartes = nombreJugador.split(" ");
            if (nombrePartes.length == 1) {
                jugadores = playerController.getPlayersByName(nombreJugador);
            } else if (nombrePartes.length == 2) {
                jugadores = playerController.getPlayersByFullName(nombrePartes[0], nombrePartes[1]);
            } else {
                System.out.println("Por favor, introduce solo el nombre o el nombre y el apellido del jugador.");
                continue;
            }

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

        List<String> teamNames = new ArrayList<>();
        for (Player player : players) {
            String teamName = teamController.getTeamNameById(player.getEquip_id()); // Obtener el nombre del equipo
            teamNames.add(teamName);
        }

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.println((i + 1) + ". " + player.getNom() + " " + player.getCognom() + " (Equipo: " + teamNames.get(i) + ")");
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
        double[] medias = playerStatsController.calcularMediasJugador(player.getJugador_id());

        if (medias == null) {
            System.out.println("No se encontraron estadísticas para el jugador " + player.getNom() + " " + player.getCognom());
            return;
        }

        System.out.println("Estadísticas promedio de " + player.getNom() + " " + player.getCognom() + ":");
        System.out.println("Minutos Jugados: " + String.format("%.2f", medias[0]));
        System.out.println("Puntos: " + String.format("%.2f", medias[1]));
        System.out.println("Tiros Anotados: " + String.format("%.2f", medias[2]));
        System.out.println("Tiros Tirados: " + String.format("%.2f", medias[3]));
        System.out.println("Triples Anotados: " + String.format("%.2f", medias[4]));
        System.out.println("Triples Tirados: " + String.format("%.2f", medias[5]));
        System.out.println("Tiros Libres Anotados: " + String.format("%.2f", medias[6]));
        System.out.println("Tiros Libres Tirados: " + String.format("%.2f", medias[7]));
        System.out.println("Rebotes Ofensivos: " + String.format("%.2f", medias[8]));
        System.out.println("Rebotes Defensivos: " + String.format("%.2f", medias[9]));
        System.out.println("Asistencias: " + String.format("%.2f", medias[10]));
        System.out.println("Robos: " + String.format("%.2f", medias[11]));
        System.out.println("Bloqueos: " + String.format("%.2f", medias[12]));
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

    private static void actualizarNombreEquipo() {
        String nombreEquipoActual;
        String nombreEquipoNuevo;
        boolean salir = false;
        do {
            System.out.print("Introduce el nombre del equipo (o escribe 'regresar' para volver al menú): ");
            nombreEquipoActual = scan.nextLine();

            if (nombreEquipoActual.equalsIgnoreCase("regresar")) {
                return; // Volver al menú principal
            }

            // Validación del nombre del equipo
            if (!isValidTeamName(nombreEquipoActual)) {
                System.out.println("Nombre de equipo inválido. No debe contener números y no puede estar vacío. Inténtalo de nuevo.");
                continue; // Repetir el bucle si el nombre del equipo es inválido
            }
            System.out.println("Introduce el nuevo nombre del equipo:");
            nombreEquipoNuevo = scan.nextLine();
            if (!nombreEquipoNuevo.isEmpty()) {
                if (teamController.changeNameTeam(nombreEquipoActual, nombreEquipoNuevo)) {
                    System.out.println("Nombre del equipo actualizado exitosamente.");
                    salir = true;
                } else {
                    System.out.println("No se pudo encontrar un equipo con ese nombre. Inténtalo de nuevo.");
                }
            }
        } while (!salir);
    }

    /*
    3.- Llistar tots els partits jugats per un equip amb el seu resultat.
    Demanarem el nom d’un equip en concret i posteriorment llistarem tots els partits i resultats que ha obtingut,
    mostrant la informació amb una estructura semblant a aquestes:

    Los Angeles Lakers – Seattle Supersonics: 114 – 92

    Los Angeles Lakers: 114
    Seattle Supersonics: 92
     */
    private static void listarPartidoPorEquipo() {
        String nomEquip;
        int equip_id;
        boolean salir = false;
        List<Match> resultados;

        do {
            System.out.println("Introduce el nombre del equipo: ");
            nomEquip = scan.nextLine();

            if (isValidTeamName(nomEquip)) {
                try {
                    if (teamController.existTeamName(nomEquip)) {
                        equip_id = teamController.getTeamId(nomEquip);
                        resultados = matchController.getAllTMatchForTeam(equip_id);

                        for (Match resultado : resultados) {
                            System.out.println(resultado.getResultat());
                        }

                        salir = true;
                    } else {
                        System.out.println("No se ha encontrado el nombre del equipo.");
                    }
                } catch (Exception e) {
                    System.out.println("Ha ocurrido un error: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                System.out.println("Debes introducir un nombre de equipo válido.");
            }
        } while (!salir);
    }


    private static void traspasarJugador_a_Equipo() {
        String nombreJugador = "";
        String nombreEquipo;
        int equip_id;
        boolean nombreJugadorCorrecto = false;
        boolean salir = false;

        do {
            if (!nombreJugadorCorrecto) {
                System.out.print("Introduce el nombre del jugador (o escribe 'regresar' para volver al menú): ");
                nombreJugador = scan.nextLine();

                if (nombreJugador.equalsIgnoreCase("regresar")) {
                    return; // Volver al menú principal
                }
                // Comprobar si el nombre del jugador es válido:
                if (isValidPlayerName(nombreJugador)) {
                    // Comprobar si existe en la BD:
                    if (playerController.existPlayerName(nombreJugador)) {
                        nombreJugadorCorrecto = true;
                    } else {
                        System.out.println("El jugador introducido no existe.");
                        continue; // Repetir el bucle si el nombre del jugador no existe
                    }
                } else {
                    System.out.println("Nombre de jugador no válido. Inténtalo de nuevo.");
                    continue; // Repetir el bucle si el nombre del jugador es inválido
                }
            }

            System.out.print("Introduce el nombre del equipo al que quieres cambiar el jugador: (o escribe 'regresar' para volver al menú): ");
            nombreEquipo = scan.nextLine();
            if (nombreJugador.equalsIgnoreCase("regresar")) {
                return; // Volver al menú principal
            }
            if (!isValidTeamName(nombreEquipo)) {
                System.out.println("El nombre del equipo no es válido. Inténtalo de nuevo.");
            } else {
                // Si es un nombre válido, comprueba si existe:
                if (teamController.existTeamName(nombreEquipo)) {
                    equip_id = teamController.getTeamId(nombreEquipo);
                    if (playerController.changeNameTeamOfPlayer(nombreJugador, equip_id)) {
                        System.out.println("El jugador ha sido traspasado exitosamente al equipo.");
                        salir = true;
                    } else {
                        System.out.println("Se ha producido un error al traspasar el jugador.");
                    }
                } else {
                    // Si no existe:
                    System.out.println("El nombre del equipo no existe. Inténtalo de nuevo.");
                }
            }
        } while (!salir);
    }

    private static void retirarJugador() {
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
            confirmarRetiroJugador(jugadores.get(0));
        } else {
            elegirJugadorDeListaParaRetirar(jugadores);
        }
    }

    private static void elegirJugadorDeListaParaRetirar(List<Player> players) {
        System.out.println("Se encontraron varios jugadores con el mismo nombre:");
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.println((i + 1) + ". " + player.getNom() + " " + player.getCognom() + " (Equipo: " + player.getEquip_id() + ", ID: " + player.getJugador_id() + ")");
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
                    confirmarRetiroJugador(players.get(opcion - 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, introduce un número entero.");
            }
        } while (opcion == -1);
    }

    private static void confirmarRetiroJugador(Player player) {
        String confirmacion;
        boolean confirmacionValida;

        do {
            System.out.print("¿Estás seguro de que deseas retirar al jugador " + player.getNom() + " " + player.getCognom() + " (ID: " + player.getJugador_id() + ")? (sí/no): ");
            confirmacion = scan.nextLine().trim().toLowerCase();

            confirmacionValida = isValidConfirmation(confirmacion);

            if (!confirmacionValida) {
                System.out.println("Entrada inválida. Por favor, introduce 'sí' o 'no'.");
            }
        } while (!confirmacionValida);

        if (confirmacion.equals("sí") || confirmacion.equals("si")) {
            System.out.println("El proceso de retiro del jugador puede tardar unos 5 minutos. Por favor, espera...");
            if (playerController.retirarJugador(player.getJugador_id())) {
                System.out.println("El jugador ha sido retirado exitosamente.");
            } else {
                System.out.println("Se ha producido un error al retirar el jugador.");
            }
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private static boolean isValidConfirmation(String confirmacion) {
        if (confirmacion == null || confirmacion.isEmpty()) {
            return false;
        }
        if (confirmacion.equals("sí") || confirmacion.equals("si") || confirmacion.equals("no")) {
            return true;
        }
        for (char c : confirmacion.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
            }
        }
        return false;
    }
}