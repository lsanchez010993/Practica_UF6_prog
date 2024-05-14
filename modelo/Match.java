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

    // Getters y setters
}
