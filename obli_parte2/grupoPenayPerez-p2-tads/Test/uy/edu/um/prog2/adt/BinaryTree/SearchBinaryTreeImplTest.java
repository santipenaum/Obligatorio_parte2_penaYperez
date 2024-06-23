package uy.edu.um.prog2.adt.BinaryTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchBinaryTreeImplTest {

    private SearchBinaryTreeImpl<Integer> tree;

    @BeforeEach
    public void setUp() {
        tree = new SearchBinaryTreeImpl<>();
    }

    @Test
    public void testAddAndContains() {
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        assertTrue(tree.contains(50));
        assertTrue(tree.contains(30));
        assertTrue(tree.contains(70));
        assertTrue(tree.contains(20));
        assertTrue(tree.contains(40));
        assertTrue(tree.contains(60));
        assertTrue(tree.contains(80));

        assertFalse(tree.contains(10));
        assertFalse(tree.contains(90));
    }

    @Test
    public void testRemove() {
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        assertTrue(tree.contains(30));
        tree.remove(30);
        assertFalse(tree.contains(30));

        assertTrue(tree.contains(50));
        tree.remove(50);
        assertFalse(tree.contains(50));

        assertTrue(tree.contains(70));
        tree.remove(70);
        assertFalse(tree.contains(70));
    }

    @Test
    public void testFind() {
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        assertEquals(Integer.valueOf(50), tree.find(50));
        assertEquals(Integer.valueOf(30), tree.find(30));
        assertEquals(Integer.valueOf(70), tree.find(70));
        assertEquals(Integer.valueOf(20), tree.find(20));
        assertEquals(Integer.valueOf(40), tree.find(40));
        assertEquals(Integer.valueOf(60), tree.find(60));
        assertEquals(Integer.valueOf(80), tree.find(80));

        assertNull(tree.find(10));
        assertNull(tree.find(90));
    }

    @Test
    public void testPreOrder() {
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        List<Integer> preOrder = tree.preOrder();
        assertEquals(50, preOrder.get(0));
        assertEquals(30, preOrder.get(1));
        assertEquals(20, preOrder.get(2));
        assertEquals(40, preOrder.get(3));
        assertEquals(70, preOrder.get(4));
        assertEquals(60, preOrder.get(5));
        assertEquals(80, preOrder.get(6));
    }

    @Test
    public void testPosOrder() {
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        List<Integer> posOrder = tree.posOrder();
        assertEquals(20, posOrder.get(0));
        assertEquals(40, posOrder.get(1));
        assertEquals(30, posOrder.get(2));
        assertEquals(60, posOrder.get(3));
        assertEquals(80, posOrder.get(4));
        assertEquals(70, posOrder.get(5));
        assertEquals(50, posOrder.get(6));
    }

    @Test
    public void testInOrder() {
        tree.add(50);
        tree.add(30);
        tree.add(70);
        tree.add(20);
        tree.add(40);
        tree.add(60);
        tree.add(80);

        List<Integer> inOrder = tree.inOrder();
        assertEquals(20, inOrder.get(0));
        assertEquals(30, inOrder.get(1));
        assertEquals(40, inOrder.get(2));
        assertEquals(50, inOrder.get(3));
        assertEquals(60, inOrder.get(4));
        assertEquals(70, inOrder.get(5));
        assertEquals(80, inOrder.get(6));
    }
}