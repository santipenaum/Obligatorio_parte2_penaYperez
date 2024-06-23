package uy.edu.um.prog2.adt.Hash;


import java.util.ArrayList;
import java.util.List;

public class MyHashImpl<K, V> implements MyHash<K, V> {
    private static final int capacidad_por_defecto = 500;
    private HashNode<K, V>[] tabla;
    private int size;

    public MyHashImpl() {
        tabla = new HashNode[capacidad_por_defecto];
        size = 0;
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);

        if ((double)size / tabla.length > 0.7) {
            resize();
        }
        if (tabla[index] == null) {
            tabla[index] = newNode;
        } else {
            HashNode<K, V> current = tabla[index];
            while (current.getNodoSiguiente() != null) {
                current = current.getNodoSiguiente();
            }
            current.setNodoSiguiente(newNode);
            newNode.setNodoAnterior(current);
        }
        size++;
    }
    private void resize() {
        int newCapacity = tabla.length * 2;

        // Creamos una nueva tabla con la nueva capacidad
        HashNode<K, V>[] newTable = new HashNode[newCapacity];

        // Reinsertamos todos los elementos de la tabla original en la nueva tabla
        for (int i = 0; i < tabla.length; i++) {
            HashNode<K, V> current = tabla[i];
            while (current != null) {
                int newIndex = Math.abs(current.getKey().hashCode()) % newCapacity;
                HashNode<K, V> next = current.getNodoSiguiente();
                if (newTable[newIndex] == null) {
                    newTable[newIndex] = current;
                    current.setNodoAnterior(null);
                    current.setNodoSiguiente(null);
                } else {
                    current.setNodoAnterior(null);
                    current.setNodoSiguiente(newTable[newIndex]);
                    newTable[newIndex].setNodoAnterior(current);
                    newTable[newIndex] = current;
                }
                current = next;
            }
        }
        tabla = newTable;
    }

    public V get(K key) {
        int index = hash(key);

        while (tabla[index] != null && !tabla[index].getKey().equals(key)) {
            index = (index + 1) % tabla.length;
        }

        if (tabla[index] != null && tabla[index].getKey().equals(key)) {
            return tabla[index].getValue();
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        HashNode<K, V> current = tabla[index];

        while (current != null) {
            if (current.getKey().equals(key)) {
                if (current.getNodoAnterior() == null) {
                    tabla[index] = current.getNodoSiguiente();
                } else {
                    current.getNodoAnterior().setNodoSiguiente(current.getNodoSiguiente());
                }
                if (current.getNodoSiguiente() != null) {
                    current.getNodoSiguiente().setNodoAnterior(current.getNodoAnterior());
                }
                size--;


                return;
            }
            current = current.getNodoSiguiente();
        }
    }

    public boolean containsKey(K key) {
        int index = hash(key);
        HashNode<K, V> current = tabla[index];

        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.getNodoSiguiente();
        }

        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public List<V> values() {
        List<V> valores = new ArrayList<>();
        for (HashNode<K, V> nodo : tabla) {
            while (nodo != null) {
                valores.add(nodo.getValue());
                nodo = nodo.getNodoSiguiente();
            }
        }
        return valores;
    }

    public int size() {
        return size;
    }

    public List<K> keys() {
        List<K> claves = new ArrayList<>();
        for (HashNode<K, V> nodo : tabla) {
            while (nodo != null) {
                claves.add(nodo.getKey());
                nodo = nodo.getNodoSiguiente();
            }
        }
        return claves;
    }


    private int hash(K key) {
        return Math.abs(key.hashCode()) % tabla.length;
    }


}
