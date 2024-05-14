package modelo.dao;


import java.util.List;

public interface GenericDAO<T, ID> {
    /*
    * La T representa el tipo de objeto que se va a manejar (Jugador, Partido, etc).
    * La ID representa el tipo de dato que se va a utilizar para identificar a los objetos (int, String, etc).
    * */


    T findById(ID id); //Busca y devuelve una entidad por su identificador único.
    List<T> findAll(); // Busca y devuelve una entidad por su identificador único.
    boolean insert(T entity); // Busca y devuelve una entidad por su identificador único.
    boolean update(T entity); // Busca y devuelve una entidad por su identificador único.
    boolean delete(ID id); // Busca y devuelve una entidad por su identificador único.
}
