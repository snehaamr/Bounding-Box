package com.example;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class BoundingBoxTest {

    @Test
    public void testSingleAsterisk() {
        String[] grid = {
            "----",
            "--*--",
            "----"
        };

        char[][] charGrid = toCharGrid(grid);
        List<int[]> boxes = BoundingBox.findBoundingBoxes(charGrid);

        assertEquals(1, boxes.size());
        assertArrayEquals(new int[]{2, 3, 2, 3}, boxes.get(0)); // 1-based indexing
    }

    @Test
    public void testMultipleIsolatedAsterisks() {
        String[] grid = {
            "*---*",
            "-----",
            "*---*"
        };

        char[][] charGrid = toCharGrid(grid);
        List<int[]> boxes = BoundingBox.findBoundingBoxes(charGrid);

        assertEquals(4, boxes.size());
        assertTrue(boxes.stream().anyMatch(b -> arrayEquals(b, new int[]{1, 1, 1, 1})));
        assertTrue(boxes.stream().anyMatch(b -> arrayEquals(b, new int[]{1, 5, 1, 5})));
        assertTrue(boxes.stream().anyMatch(b -> arrayEquals(b, new int[]{3, 1, 3, 1})));
        assertTrue(boxes.stream().anyMatch(b -> arrayEquals(b, new int[]{3, 5, 3, 5})));
    }

    @Test
    public void testConnectedRegion() {
        String[] grid = {
            "--**",
            "--**",
            "----"
        };

        char[][] charGrid = toCharGrid(grid);
        List<int[]> boxes = BoundingBox.findBoundingBoxes(charGrid);

        assertEquals(1, boxes.size());
        assertArrayEquals(new int[]{1, 3, 2, 4}, boxes.get(0));
    }

    @Test
    public void testNoAsterisk() {
        String[] grid = {
            "----",
            "----",
            "----"
        };

        char[][] charGrid = toCharGrid(grid);
        List<int[]> boxes = BoundingBox.findBoundingBoxes(charGrid);

        assertEquals(0, boxes.size());
    }

    @Test
    public void testAllAsterisks() {
        String[] grid = {
            "***",
            "***",
            "***"
        };

        char[][] charGrid = toCharGrid(grid);
        List<int[]> boxes = BoundingBox.findBoundingBoxes(charGrid);

        assertEquals(1, boxes.size());
        assertArrayEquals(new int[]{1, 1, 3, 3}, boxes.get(0));
    }

    @Test
    public void testLShapedRegion() {
        String[] grid = {
            "*--",
            "*--",
            "***"
        };

        char[][] charGrid = toCharGrid(grid);
        List<int[]> boxes = BoundingBox.findBoundingBoxes(charGrid);

        assertEquals(1, boxes.size());
        assertArrayEquals(new int[]{1, 1, 3, 3}, boxes.get(0));
    }

    private char[][] toCharGrid(String[] grid) {
        char[][] charGrid = new char[grid.length][grid[0].length()];
        for (int i = 0; i < grid.length; i++) {
            charGrid[i] = grid[i].toCharArray();
        }
        return charGrid;
    }

    // Helper method for comparing int[] arrays
    private boolean arrayEquals(int[] a, int[] b) {
        return java.util.Arrays.equals(a, b);
    }
}
