package modelo;

public class Team {
    int id;
    String nombre;
    String acronimo;
    String ciudad;
    String division;



    public Team(int id, String nombre, String acronimo, String ciudad, String division, int any_fundado) {
        this.id = id;
        this.nombre = nombre;
        this.acronimo = acronimo;
        this.ciudad = ciudad;
        this.division = division;

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
