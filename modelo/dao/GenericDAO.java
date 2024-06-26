package modelo.dao;

import java.util.List;

public interface GenericDAO<T, ID> {
    /*
     * La T representa el tipo de objeto que se va a manejar (Jugador, Partido, etc).
     * La ID representa el tipo de dato que se va a utilizar para identificar a los objetos (int, String, etc).
     * */

    T findById(ID id); // Busca y devuelve una entidad por su identificador único.
    List<T> findAll(); // Devuelve todas las entidades.
    boolean insert(T entity); // Inserta una entidad.
    boolean update(T entity); // Actualiza una entidad.
    boolean delete(ID id); // Elimina una entidad por su identificador único.
}