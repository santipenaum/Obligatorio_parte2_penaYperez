package uy.edu.um.prog2.adt.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.Queue.EmptyQueueException;
import uy.edu.um.prog2.adt.Stack.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListImplTest {

    private MyLinkedListImpl<Object> list;

    @BeforeEach
    public void setUp() {
        list = new MyLinkedListImpl<>();
    }
    @Test
    public void testAddAndGet() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testSize() {
        assertEquals(0, list.size());

        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(3, list.size());
    }
    @Test
    public void testContains() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.contains(2));
        assertFalse(list.contains(56));
    }
    @Test
    public void testRemove() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(2);
        assertFalse(list.contains(2));
        assertEquals(2, list.size());

        list.remove(1);
        assertFalse(list.contains(1));
        assertEquals(1, list.size());
    }
    @Test
    public void testQueueOperaciones() throws EmptyQueueException {
        list.enqueue(1);
        list.enqueue(2);
        list.enqueue(3);

        assertEquals(1, list.dequeue());
        assertEquals(2, list.dequeue());
        assertEquals(3, list.dequeue());
        assertTrue(list.size() == 0);
    }
    @Test
    public void testStackOperations() throws EmptyStackException {
        list.push(1);
        list.push(2);
        list.push(3);

        assertEquals(3, list.pop());
        assertEquals(2, list.pop());
        assertEquals(1, list.pop());
        assertTrue(list.size() == 0);
    }
    @Test
    public void testPeek() {
        assertNull(list.peek());

        list.push(1);
        assertEquals(1, list.peek());

        list.push(2);
        assertEquals(2, list.peek());

        try {
            list.pop();
        } catch (EmptyStackException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, list.peek());
    }
}