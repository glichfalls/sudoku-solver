package ch.juventus.puzzle.sudoku;

import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuValueTest {

    @Test
    public void testConstructionFromInt() {
        SudokuValue value = new SudokuValue(7, 4, 6);
        assertEquals(7, value.getX());
        assertEquals(4, value.getY());
        assertEquals(6, value.getNumber());
    }

    @Test
    public void testConstructionFromString() {
        SudokuValue value = new SudokuValue(0, 1, "4");
        assertEquals(0, value.getX());
        assertEquals(1, value.getY());
        assertEquals(4, value.getNumber());
    }

    @Test
    public void testNumberToString() {
        SudokuValue value = new SudokuValue(3, 6, 6);
        assertEquals("6", value.toString());
    }

    @Test
    public void testEmptyToString() {
        SudokuValue value = new SudokuValue(2, 4, "");
        assertEquals("", value.toString());
    }

    @Test
    public void testZeroToString() {
        SudokuValue value = new SudokuValue(4, 8, 0);
        assertEquals("", value.toString());
    }

}