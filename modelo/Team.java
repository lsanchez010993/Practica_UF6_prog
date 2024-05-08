package modelo;

public class Team {
    int id;
    String nombre;
    String acronimo;
    String ciudad;
    String estado;
    int any_fundado;

    public Team(int id, String nombre, String acronimo, String ciudad, String estado, int any_fundado) {
        this.id = id;
        this.nombre = nombre;
        this.acronimo = acronimo;
        this.ciudad = ciudad;
        this.estado = estado;
        this.any_fundado = any_fundado;
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

    public String getEstado() {
        return estado;
    }

    public int getAny_fundado() {
        return any_fundado;
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

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setAny_fundado(int any_fundado) {
        this.any_fundado = any_fundado;
    }
}
