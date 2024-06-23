package uy.edu.um.prog2.adt.Hash;

import java.util.Objects;

public class HashNode<K,V> {

    private K key;
    private V value;
    private HashNode<K,V> nodoAnterior;
    private HashNode<K,V> nodoSiguiente;

    public HashNode(K key, V value){
        this.key=key;
        this.value=value;
    }

    public HashNode<K, V> getNodoAnterior() {
        return nodoAnterior;
    }

    public void setNodoAnterior(HashNode<K, V> nodoAnterior) {
        this.nodoAnterior = nodoAnterior;
    }

    public HashNode<K, V> getNodoSiguiente() {
        return nodoSiguiente;
    }

    public void setNodoSiguiente(HashNode<K, V> nodoSiguiente) {
        this.nodoSiguiente = nodoSiguiente;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashNode<?, ?> hashNode = (HashNode<?, ?>) o;
        return Objects.equals(key, hashNode.key);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key);
    }
}