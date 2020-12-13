package ch.juventus.puzzle.sudoku;

import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuValueTest {

    @Test
    public void testConstructionFromInt() {
        SudokuValue value = new SudokuValue(7, 4, 6);
        assertEquals(7, value.x);
        assertEquals(4, value.y);
        assertEquals(6, value.number);
    }

    @Test
    public void testConstructionFromString() {
        SudokuValue value = new SudokuValue(0, 1, "4");
        assertEquals(0, value.x);
        assertEquals(1, value.y);
        assertEquals(4, value.number);
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