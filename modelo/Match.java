package modelo;

import java.util.Date;

public class Match {
    private int partit_id;
    private int equip_id;
    private Date data_partit;
    private String matx;
    private String resultat;


    public Match(int partit_id, int equip_id, Date data_partit, String matx, String resultat) {
        this.partit_id = partit_id;
        this.equip_id = equip_id;
        this.data_partit = data_partit;
        this.matx = matx;
        this.resultat = resultat;

    }
    public Match(String resultat) {
        this.resultat = resultat;
    }

    public Match(String partitId, String dataPartit, String matx, String resultat, String ciutat, String nomEquip) {
    }


    // Getters y setters

    public int getPartit_id() {
        return partit_id;
    }

    public int getEquip_id() {
        return equip_id;
    }


    public Date getData_partit() {
        return data_partit;
    }

    public String getMatx() {
        return matx;
    }

    public String getResultat() {
        return resultat;
    }


}
