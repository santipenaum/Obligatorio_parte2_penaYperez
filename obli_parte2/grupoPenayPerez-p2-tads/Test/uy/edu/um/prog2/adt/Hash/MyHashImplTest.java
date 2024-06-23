package uy.edu.um.prog2.adt.Hash;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyHashImplTest {

    private MyHash<Integer, String> TablaHash;

    @BeforeEach
    public void setUp() {
        TablaHash = new MyHashImpl<>();
    }

    @Test
    public void testPutAndGet() {
        TablaHash.put(1, "Hola");
        TablaHash.put(2, "Estoy");
        TablaHash.put(3, "Probando");

        assertEquals("Hola", TablaHash.get(1));
        assertEquals("Estoy", TablaHash.get(2));
        assertEquals("Probando", TablaHash.get(3));
    }
    @Test
    public void testResize() {
        // Insertamos suficientes elementos para activar el resize
        for (int i = 0; i < 20; i++) {
            TablaHash.put(i, "prueba" + i);
        }

        // Verificamos que la tabla haya sido redimensionada
        assertTrue(TablaHash.size() > 10);
    }

    @Test
    public void testRemove() {
        TablaHash.put(1, "Hola");
        TablaHash.put(2, "Estoy");
        TablaHash.put(3, "Probando");

        TablaHash.remove(2);

        assertNull(TablaHash.get(2));
        assertEquals(2, TablaHash.size());
    }

    @Test
    public void testContainsKey() {
        TablaHash.put(1, "Hola");
        TablaHash.put(2, "Estoy");
        TablaHash.put(3, "Probando");

        assertTrue(TablaHash.containsKey(1));
        assertFalse(TablaHash.containsKey(4));
    }

    @Test
    public void testSize() {
        TablaHash.put(1, "Hola");
        TablaHash.put(2, "Estoy");
        TablaHash.put(3, "Probando");

        assertEquals(3, TablaHash.size());

        TablaHash.remove(2);

        assertEquals(2, TablaHash.size());
    }

}
