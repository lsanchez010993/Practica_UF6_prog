package modelo;

import java.util.Date;

public class PlayerHistoric {
    private int jugador_id;
    private String nom;
    private String cognom;
    private Date dataNaixement;
    private String alcada;
    private String pes;
    private String dorsal;
    private String posicio;
    private int equip_id;

    public PlayerHistoric(int jugador_id, String nom, String cognom, Date dataNaixement, String alcada, String pes, String dorsal, String posicio, int equip_id) {
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

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
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
