package modelo;

public class Team {
    int equip_id;
    String nom;
    String ciutat;
    String acronim;
    String divisio;
    int guanyades;
    int perdudes;


    public Team(int equip_id, String nom, String acronim, String ciutat, String divisio, int guanyades, int perdudes) {
        this.equip_id = equip_id;
        this.nom = nom;
        this.acronim = acronim;
        this.ciutat = ciutat;
        this.divisio = divisio;
        this.guanyades = guanyades;
        this.perdudes = perdudes;

    }

    public int getEquip_id() {
        return equip_id;
    }

    public String getNom() {
        return nom;
    }

    public String getAcronim() {
        return acronim;
    }

    public String getCiutat() {
        return ciutat;
    }

    public String getDivisio() {
        return divisio;
    }

    public void setDivisio(String divisio) {
        this.divisio = divisio;
    }

    public void setGuanyades(int guanyades) {
        this.guanyades = guanyades;
    }

    public void setPerdudes(int perdudes) {
        this.perdudes = perdudes;
    }

    public int getGuanyades() {
        return guanyades;
    }

    public int getPerdudes() {
        return perdudes;
    }

    public void setEquip_id(int equip_id) {
        this.equip_id = equip_id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAcronim(String acronim) {
        this.acronim = acronim;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }


}
