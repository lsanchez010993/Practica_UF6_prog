package modelo;

import java.util.Date;

public class Player {
    private int jugador_Id;
    private String nom;
    private String cognom;
    private Date dataNaixement;
    private String alcada; //Altura
    private String pes;
    private String posicio;
    private int equipId;

    // Constructor
    public Player(int jugador_Id, String nom, String cognom, Date dataNaixement, String alcada, String pes, String posicio, int equipId) {
        this.jugador_Id = jugador_Id;
        this.nom = nom;
        this.cognom = cognom;
        this.dataNaixement = dataNaixement;
        this.alcada = alcada;
        this.pes = pes;
        this.posicio = posicio;
        this.equipId = equipId;
    }

    // Getters y Setters
    public int getJugador_Id() {
        return jugador_Id;
    }

    public void setJugador_Id(int jugador_Id) {
        this.jugador_Id = jugador_Id;
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

    public String getPosicio() {
        return posicio;
    }

    public void setPosicio(String posicio) {
        this.posicio = posicio;
    }

    public int getEquipId() {
        return equipId;
    }

    public void setEquipId(int equipId) {
        this.equipId = equipId;
    }
}
