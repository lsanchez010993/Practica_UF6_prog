package modelo;
public class Match {
    private String id;
    private int equipId;
    private int temporadaId;
    private String dataPartit;
    private String matx;
    private String resultat;
    private int punts;
    private int tirsAnotats;
    private int tirsIntentats;
    private int triplesAnotats;
    private int triplesIntentats;
    private int rebotsOfensius;
    private int rebotsDefensius;
    private int rebots;
    private int assistencies;
    private int robades;
    private int bloqueigs;



    public Match(String id, int equipId, int temporadaId, String dataPartit, String matx, String resultat, int punts,
                 int tirsAnotats, int tirsIntentats, int triplesAnotats, int triplesIntentats, int rebotsOfensius,
                 int rebotsDefensius, int rebots, int assistencies, int robades, int bloqueigs) {
        this.id = id;
        this.equipId = equipId;
        this.temporadaId = temporadaId;
        this.dataPartit = dataPartit;
        this.matx = matx;
        this.resultat = resultat;
        this.punts = punts;
        this.tirsAnotats = tirsAnotats;
        this.tirsIntentats = tirsIntentats;
        this.triplesAnotats = triplesAnotats;
        this.triplesIntentats = triplesIntentats;
        this.rebotsOfensius = rebotsOfensius;
        this.rebotsDefensius = rebotsDefensius;
        this.rebots = rebots;
        this.assistencies = assistencies;
        this.robades = robades;
        this.bloqueigs = bloqueigs;
    }

    public Match(String partitId, String dataPartit, String matx, String resultat, String ciutat, String nomEquip) {
    }


    // Getters y setters

    public String getId() {
        return id;
    }

    public int getEquipId() {
        return equipId;
    }

    public int getTemporadaId() {
        return temporadaId;
    }

    public String getDataPartit() {
        return dataPartit;
    }

    public String getMatx() {
        return matx;
    }

    public String getResultat() {
        return resultat;
    }

    public int getPunts() {
        return punts;
    }

    public int getTirsAnotats() {
        return tirsAnotats;
    }

    public int getTirsIntentats() {
        return tirsIntentats;
    }

    public int getTriplesAnotats() {
        return triplesAnotats;
    }

    public int getTriplesIntentats() {
        return triplesIntentats;
    }

    public int getRebotsOfensius() {
        return rebotsOfensius;
    }

    public int getRebotsDefensius() {
        return rebotsDefensius;
    }

    public int getRebots() {
        return rebots;
    }

    public int getAssistencies() {
        return assistencies;
    }

    public int getRobades() {
        return robades;
    }

    public int getBloqueigs() {
        return bloqueigs;
    }
}
