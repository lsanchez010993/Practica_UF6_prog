package modelo;

public class PlayerStats {
    private int jugador_id;
    private int partit_id;
    private double minutsJugats;
    private int punts;
    private int tirsAnotats;
    private int tirsTirats;
    private int tirsTriplesAnotats;
    private int tirsTriplesTirats;
    private int tirsLliuresAnotats;
    private int tirsLliuresTirats;
    private int rebotsOfensius;
    private int rebotsDefensius;
    private int assistencies;
    private int robades;
    private int bloqueigs;

    // Constructor
    public PlayerStats(int jugador_id, int partit_id, double minutsJugats, int punts, int tirsAnotats, int tirsTirats, int tirsTriplesAnotats, int tirsTriplesTirats, int tirsLliuresAnotats, int tirsLliuresTirats, int rebotsOfensius, int rebotsDefensius, int assistencies, int robades, int bloqueigs) {
        this.jugador_id = jugador_id;
        this.partit_id = partit_id;
        this.minutsJugats = minutsJugats;
        this.punts = punts;
        this.tirsAnotats = tirsAnotats;
        this.tirsTirats = tirsTirats;
        this.tirsTriplesAnotats = tirsTriplesAnotats;
        this.tirsTriplesTirats = tirsTriplesTirats;
        this.tirsLliuresAnotats = tirsLliuresAnotats;
        this.tirsLliuresTirats = tirsLliuresTirats;
        this.rebotsOfensius = rebotsOfensius;
        this.rebotsDefensius = rebotsDefensius;
        this.assistencies = assistencies;
        this.robades = robades;
        this.bloqueigs = bloqueigs;
    }

    public int getJugador_id() {
        return jugador_id;
    }

    public void setJugador_id(int jugador_id) {
        this.jugador_id = jugador_id;
    }

    public int getpartit_id() {
        return partit_id;
    }

    public void setpartit_id(int partit_id) {
        this.partit_id = partit_id;
    }

    public double getMinutsJugats() {
        return minutsJugats;
    }

    public void setMinutsJugats(double minutsJugats) {
        this.minutsJugats = minutsJugats;
    }

    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    public int getTirsAnotats() {
        return tirsAnotats;
    }

    public void setTirsAnotats(int tirsAnotats) {
        this.tirsAnotats = tirsAnotats;
    }

    public int getTirsTirats() {
        return tirsTirats;
    }

    public void setTirsTirats(int tirsTirats) {
        this.tirsTirats = tirsTirats;
    }

    public int getTirsTriplesAnotats() {
        return tirsTriplesAnotats;
    }

    public void setTirsTriplesAnotats(int tirsTriplesAnotats) {
        this.tirsTriplesAnotats = tirsTriplesAnotats;
    }

    public int getTirsTriplesTirats() {
        return tirsTriplesTirats;
    }

    public void setTirsTriplesTirats(int tirsTriplesTirats) {
        this.tirsTriplesTirats = tirsTriplesTirats;
    }

    public int getTirsLliuresAnotats() {
        return tirsLliuresAnotats;
    }

    public void setTirsLliuresAnotats(int tirsLliuresAnotats) {
        this.tirsLliuresAnotats = tirsLliuresAnotats;
    }

    public int getTirsLliuresTirats() {
        return tirsLliuresTirats;
    }

    public void setTirsLliuresTirats(int tirsLliuresTirats) {
        this.tirsLliuresTirats = tirsLliuresTirats;
    }

    public int getRebotsOfensius() {
        return rebotsOfensius;
    }

    public void setRebotsOfensius(int rebotsOfensius) {
        this.rebotsOfensius = rebotsOfensius;
    }

    public int getRebotsDefensius() {
        return rebotsDefensius;
    }

    public void setRebotsDefensius(int rebotsDefensius) {
        this.rebotsDefensius = rebotsDefensius;
    }

    public int getAssistencies() {
        return assistencies;
    }

    public void setAssistencies(int assistencies) {
        this.assistencies = assistencies;
    }

    public int getRobades() {
        return robades;
    }

    public void setRobades(int robades) {
        this.robades = robades;
    }

    public int getBloqueigs() {
        return bloqueigs;
    }

    public void setBloqueigs(int bloqueigs) {
        this.bloqueigs = bloqueigs;
    }
}
