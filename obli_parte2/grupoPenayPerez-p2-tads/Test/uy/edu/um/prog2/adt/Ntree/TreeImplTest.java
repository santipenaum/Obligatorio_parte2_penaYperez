package uy.edu.um.prog2.adt.Ntree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeImplTest {
    private TreeImpl<Integer> tree;

    @BeforeEach
    public void setUp() {
        tree = new TreeImpl<>(1);
    }

    @Test
    public void testAddAndGet() {
        tree.add(2, 1);
        tree.add(3, 1);

        Tree<Integer> node2 = tree.searchValue(2);
        Tree<Integer> node3 = tree.searchValue(3);

        assertNotNull(node2);
        assertNotNull(node3);
        assertEquals(2, node2.getValue());
        assertEquals(3, node3.getValue());
    }

    @Test
    public void testSearchValue() {
        tree.add(2, 1);
        tree.add(3, 1);
        tree.add(4, 2);

        Tree<Integer> node4 = tree.searchValue(4);
        assertNotNull(node4);
        assertEquals(4, node4.getValue());

        Tree<Integer> node5 = tree.searchValue(5);
        assertNull(node5);
    }

    @Test
    public void testAddInvalidValue() {
        assertThrows(RuntimeException.class, () -> tree.add(null, 1));
        assertThrows(RuntimeException.class, () -> tree.add(2, null));
    }

    @Test
    public void testAddExistingElement() {
        tree.add(2, 1);
        assertThrows(RuntimeException.class, () -> tree.add(2, 1));
    }

    @Test
    public void testAddParentNotExists() {
        assertThrows(RuntimeException.class, () -> tree.add(2, 5));
    }

    public void testGetChilds() {
        assertTrue(tree.getChilds().length == 0);

        tree.add(2, 1);
        tree.add(3, 1);

        Tree<Integer>[] childs = tree.getChilds();
        assertTrue(childs.length == 2);

        assertEquals(2, childs[0].getValue());
        assertEquals(3, childs[1].getValue());
    }

    @Test
    public void testSetValue() {
        assertEquals(1, tree.getValue());

        tree.setValue(5);
        assertEquals(5, tree.getValue());

        tree.setValue(null);
        assertNull(tree.getValue());
    }

}