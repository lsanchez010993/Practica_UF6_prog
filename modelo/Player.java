package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Player {
    private int jugador_id;
    private String nom;
    private String cognom;
    private Date dataNaixement;
    private String alcada;
    private String pes;
    private String dorsal; // Agregado campo dorsal
    private String posicio;
    private int equip_id;

    public Player(int jugador_id, String nom, String cognom, Date dataNaixement, String alcada, String pes, String dorsal, String posicio, int equip_id) {

        this.jugador_id = jugador_id;
        this.nom = nom;
        this.cognom = cognom;
        this.dataNaixement = dataNaixement;
        this.alcada = alcada;
        this.pes = pes;
        this.dorsal = dorsal;
        this.posicio = posicio;
        this.equip_id = equip_id;
    }
    public Player(boolean nuevoJugador, int jugador_id, String nom, String cognom, Date dataNaixement, String alcada, String pes, String dorsal, String posicio, int equip_id) {
        if (!isValidJugadorId(jugador_id)) {
            throw new IllegalArgumentException("Invalid jugador_id");
        }
        if (!isValidNom(nom)) {
            throw new IllegalArgumentException("Invalid nom");
        }
        if (!isValidCognom(cognom)) {
            throw new IllegalArgumentException("Invalid cognom");
        }
        if (!isValidDataNaixement(dataNaixement)) {
            throw new IllegalArgumentException("Invalid dataNaixement");
        }
        if (!isValidAlcada(alcada)) {
            throw new IllegalArgumentException("Invalid alcada");
        }
        if (!isValidPes(pes)) {
            throw new IllegalArgumentException("Invalid pes");
        }
        if (!isValidDorsal(dorsal)) {
            throw new IllegalArgumentException("Invalid dorsal");
        }
        if (!isValidPosicio(posicio)) {
            throw new IllegalArgumentException("Invalid posicio");
        }
        if (!isValidEquipId(equip_id)) {
            throw new IllegalArgumentException("Invalid equip_id");
        }

        this.jugador_id = jugador_id;
        this.nom = nom;
        this.cognom = cognom;
        this.dataNaixement = dataNaixement;
        this.alcada = alcada;
        this.pes = pes;
        this.dorsal = dorsal;
        this.posicio = posicio;
        this.equip_id = equip_id;
    }

    private boolean isValidJugadorId(int jugador_id) {
        return jugador_id > 0;
    }

    private boolean isValidNom(String nom) {
        return nom != null && !nom.trim().isEmpty();
    }

    private boolean isValidCognom(String cognom) {
        return cognom != null && !cognom.trim().isEmpty();
    }

    private boolean isValidDataNaixement(Date dataNaixement) {
        return dataNaixement != null && dataNaixement.before(new Date());
    }

    private boolean isValidAlcada(String alcada) {
        try {
            double height = Double.parseDouble(alcada);
            return height > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidPes(String pes) {
        try {
            double weight = Double.parseDouble(pes);
            return weight > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidDorsal(String dorsal) {
        try {
            int d = Integer.parseInt(dorsal);
            return d > 0 && d < 100; // Asumiendo que los números de dorsal válidos están entre 1 y 99
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidPosicio(String posicio) {
        String[] validPositions = {"Porter", "Defensa", "Migcampista", "Davanter"};
        for (String pos : validPositions) {
            if (pos.equalsIgnoreCase(posicio)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidEquipId(int equip_id) {
        return equip_id > 0;
    }


    // Getters y Setters
    public int getJugador_id() {
        return jugador_id;
    }

    public void setJugador_id(int jugador_id) {
        this.jugador_id = jugador_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public Date getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(Date dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public String getAlcada() {
        return alcada;
    }

    public void setAlcada(String alcada) {
        this.alcada = alcada;
    }

    public String getPes() {
        return pes;
    }

    public void setPes(String pes) {
        this.pes = pes;
    }

    public String getDorsal() { // Getter para dorsal
        return dorsal;
    }

    public void setDorsal(String dorsal) { // Setter para dorsal
        this.dorsal = dorsal;
    }

    public String getPosicio() {
        return posicio;
    }

    public void setPosicio(String posicio) {
        this.posicio = posicio;
    }

    public int getEquip_id() {
        return equip_id;
    }

    public void setEquip_id(int equip_id) {
        this.equip_id = equip_id;
    }
}
