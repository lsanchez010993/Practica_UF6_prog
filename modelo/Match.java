package modelo;

import java.util.Date;

public class Match {
    private int partit_id;
    private int equipId;
    private Date dataPartit;
    private String matx;
    private String resultat;


    public Match(int partit_id, int equipId, Date dataPartit, String matx, String resultat) {
        this.partit_id = partit_id;
        this.equipId = equipId;
        this.dataPartit = dataPartit;
        this.matx = matx;
        this.resultat = resultat;

    }


    // Getters y setters

    public int getPartit_id() {
        return partit_id;
    }

    public int getEquipId() {
        return equipId;
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


}
