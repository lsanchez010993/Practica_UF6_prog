package modelo;

public class Team {
    int id;
    String nombre;
    String acronimo;
    String ciudad;
    String division;
    int guanyades;
    int perdudes;




    public Team(int id, String nombre, String acronimo, String ciudad, String division, int guanyades, int perdudes) {
        this.id = id;
        this.nombre = nombre;
        this.acronimo = acronimo;
        this.ciudad = ciudad;
        this.division = division;
        this.guanyades=guanyades;
        this.perdudes=perdudes;

    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }


}
