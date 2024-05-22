package vista;

import controlador.PlayerController;
import controlador.TeamController;
import controlador.Validaciones;
import modelo.Player;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main2 {
    public static Scanner scan = new Scanner(System.in);
    public static PlayerController playerController = new PlayerController();
    static TeamController teamController = new TeamController();

    public static void main(String[] args) {

    }

    /*
    4.- Inserir un nou jugador a un equip.
Demanarem el nom del jugador i l’equip on el volen inserir, si el jugador ja existeix a la BDD en un equip diferent,
donarem l’opció d’executar el punt Traspassar un judador a un altra equip.
     */
    public static void afegirJugador_a_Equip() {
        String nombreJugador;
        String nombreEquipo;
        String opcion;
        int id_equip;

        System.out.println("Introduce el nombre del jugador:");
        nombreJugador = scan.nextLine();
        if (Validaciones.isValidPlayerName(nombreJugador)) {
            if (playerController.existPlayerName(nombreJugador)) {
                System.out.println("Existe un jugador llamado " + nombreJugador + " ¿Quieres cambiarlo de equipo? (si/no)");
                opcion = scan.nextLine().toLowerCase();
                switch (opcion) {
                    case "si":
                        System.out.println("Has seleccionado cambiar al jugador de equipo.");
                        traspasarJugador_a_Equipo(true);
                        break;
                    case "no":

                        break;
                }
            } else {

                //si el jugador no existe
                System.out.println("¿A qué equipo quieres añadir el nuevo jugador?");
                nombreEquipo = scan.nextLine();
                if (Validaciones.isValidTeamName(nombreEquipo)) {
                    if (teamController.existTeamName(nombreEquipo)) {
                        id_equip = teamController.getTeamId(nombreEquipo);
                        System.out.println("Completa los siguientes datos para insertar al nuevo jugador:");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        try {
                            System.out.print("Introduce el ID del jugador: ");
                            int jugador_id = scan.nextInt();
                            scan.nextLine();

                            System.out.print("Introduce el nombre del jugador: ");
                            String nom = scan.nextLine();

                            System.out.print("Introduce el apellido del jugador: ");
                            String cognom = scan.nextLine();

                            System.out.print("Introduce la fecha de nacimiento del jugador (YYYY-MM-DD): ");
                            String dataNaixementStr = scan.nextLine();
                            Date dataNaixement = dateFormat.parse(dataNaixementStr);

                            System.out.print("Introduce la altura del jugador (por ejemplo, 1.80m o 180cm): ");
                            String alcada = scan.nextLine();

                            System.out.print("Introduce el peso del jugador (por ejemplo, 75kg): ");
                            String pes = scan.nextLine();

                            System.out.print("Introduce el número dorsal del jugador: ");
                            String dorsal = scan.nextLine();

                            System.out.print("Introduce la posición del jugador (por ejemplo, delantero, defensa, portero): ");
                            String posicio = scan.nextLine();
                            //El campo jugador_id es autoincremental
                            jugador_id = playerController.getAllPlayers().size()+1;
                            playerController.createPlayer(jugador_id, nom, cognom, dataNaixement, alcada, pes, dorsal, posicio, id_equip);

                            Player jugador = new Player(jugador_id, nom, cognom, dataNaixement, alcada, pes, dorsal, posicio, id_equip);

                            // Aquí puedes hacer algo con el objeto jugador, como imprimir sus datos o guardarlo en una base de datos

                            System.out.println("Jugador creado exitosamente:");
                            System.out.println("ID: " + jugador.getJugador_id());
                            System.out.println("Nombre: " + jugador.getNom());
                            System.out.println("Apellido: " + jugador.getCognom());
                            System.out.println("Fecha de Nacimiento: " + dateFormat.format(jugador.getDataNaixement()));
                            System.out.println("Altura: " + jugador.getAlcada());
                            System.out.println("Peso: " + jugador.getPes());
                            System.out.println("Dorsal: " + jugador.getDorsal());
                            System.out.println("Posición: " + jugador.getPosicio());
                            System.out.println("ID del Equipo: " + jugador.getEquip_id());



                        } catch (ParseException e) {
                            System.out.println("Error al parsear la fecha de nacimiento. Asegúrate de usar el formato YYYY-MM-DD.");
                        } finally {
                            scan.close();
                        }

                    }
                }
            }
        }


    }

    private static void traspasarJugador_a_Equipo(boolean nombreJugadorCorrecto) {
        String nombreJugador = "";
        String nombreEquipo;
        int equip_id;

        boolean salir = false;

        do {
            if (!nombreJugadorCorrecto) {
                System.out.print("Introduce el nombre del jugador (o escribe 'regresar' para volver al menú): ");
                nombreJugador = scan.nextLine();

                if (nombreJugador.equalsIgnoreCase("regresar")) {
                    return; // Volver al menú principal
                }
                // Comprobar si el nombre del jugador es válido:
                if (Validaciones.isValidPlayerName(nombreJugador)) {
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
            if (!Validaciones.isValidTeamName(nombreEquipo)) {
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
}
