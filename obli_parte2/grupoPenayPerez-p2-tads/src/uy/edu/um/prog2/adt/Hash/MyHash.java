package uy.edu.um.prog2.adt.Hash;

import uy.edu.um.prog2.adt.LinkedList.MyLinkedListImpl;

import java.util.List;

public interface MyHash<K, V> {

    /**
     * Inserta un nuevo par clave-valor en la tabla hash.
     * Si la clave ya existe, el valor asociado se actualiza.
     * @param key   la clave a insertar
     * @param value el valor asociado a la clave
     */
    void put(K key, V value);

    /**
     * Obtiene el valor asociado a una clave específica.
     * @param key la clave cuyo valor se desea obtener
     * @return el valor asociado a la clave, o {@code null} si la clave no está presente
     */
    V get(K key);

    /**
     * Elimina una clave y su valor asociado de la tabla hash.
     * @param key la clave a eliminar
     */
    void remove(K key);

    /**
     * Verifica si la tabla hash contiene una clave específica.
     * @param key la clave a buscar
     * @return {@code true} si la tabla contiene la clave, {@code false} de lo contrario
     */
    boolean containsKey(K key);

    /**
     * Obtiene el número de pares clave-valor almacenados en la tabla hash.
     * @return el número de pares clave-valor en la tabla hash
     */
    int size();

    List<K> keys();

}

