package ch.juventus.puzzle.sudoku;

import ch.juventus.exceptions.ImportException;
import ch.juventus.exceptions.InvalidFieldException;
import ch.juventus.importer.ImporterFactory;
import ch.juventus.importer.PuzzleImporter;
import ch.juventus.importer.SudokuJsonImporter;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuTest {

    private Sudoku empty = new Sudoku();
    private Sudoku solvable;
    private Sudoku unsolvable;

    public SudokuTest() throws ImportException {
        PuzzleImporter<Sudoku> importer = new SudokuJsonImporter();
        solvable = importer.getPuzzleFromFile(getClass().getResource("/solvable.json").getPath());
        unsolvable = importer.getPuzzleFromFile(getClass().getResource("/unsolvable.json").getPath());
    }

    @Test
    public void testGetSize() {
        assertEquals(9, empty.getSize());
        assertEquals(9, solvable.getSize());
        assertEquals(9, unsolvable.getSize());
    }

    @Test
    public void testIsFilled() {
        assertTrue(solvable.isFilled(0, 0));
        assertTrue(unsolvable.isFilled(0, 0));
    }

    @Test
    public void testIsNotFilled() {
        assertFalse(empty.isFilled(0, 0));
        assertFalse(solvable.isFilled(0,1));
        assertFalse(unsolvable.isFilled(0, 1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFilledFieldDoesNotExist() {
        empty.isFilled(10, 10);
        solvable.isFilled(10, 10);
        unsolvable.isFilled(10, 10);
    }

    @Test
    public void testExistingGet() {
        assertEquals(7, solvable.get(0, 0).number);
        assertEquals(Sudoku.EMPTY, solvable.get(0, 1).number);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testNotExistingGet() {
        solvable.get(10, 10);
    }

    @Test
    public void testIsNumberPresent() {
        assertTrue(empty.isNumberPresent(0 , 0, Sudoku.EMPTY));
        assertFalse(empty.isNumberPresent(0, 0, 1));
        assertFalse(empty.isNumberPresent(0, 0, -1));
        assertFalse(empty.isNumberPresent(0, 0, 10));

        assertTrue(solvable.isNumberPresent(0, 0, 6));
        assertTrue(solvable.isNumberPresent(0, 0, 2));
        assertTrue(solvable.isNumberPresent(0, 0, 5));
        assertFalse(solvable.isNumberPresent(0, 0, 1));

        assertTrue(unsolvable.isNumberPresent(0, 0, 3));
        assertTrue(unsolvable.isNumberPresent(0, 0, 2));
        assertTrue(unsolvable.isNumberPresent(0, 0, 7));
        assertFalse(solvable.isNumberPresent(0, 0, 1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIsNumberPresentInvalidRow() {
        empty.isNumberPresent(10, 0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIsNumberPresentInvalidColumn() {
        empty.isNumberPresent(0, 10, 1);
    }

    @Test
    public void testIsNumberPresentMoreThanOnce() {
        assertTrue(empty.isNumberPresent(0, 0, Sudoku.EMPTY));
    }

    @Test
    public void setValidNumber() {
        Sudoku sudoku = new Sudoku();
        SudokuValue value = new SudokuValue(0, 0, 2);
        sudoku.set(value);
        assertSame(2, sudoku.get(0, 0).number);
    }

    @Test(expected = InvalidFieldException.class)
    public void testSetNumberTooHigh() {
        Sudoku sudoku = new Sudoku();
        SudokuValue value = new SudokuValue(0, 0, 10);
        sudoku.set(value);
    }

    @Test(expected = InvalidFieldException.class)
    public void testSetNumberTooLow() {
        Sudoku sudoku = new Sudoku();
        SudokuValue value = new SudokuValue(0, 0, -1);
        sudoku.set(value);
    }

    @Test
    public void testSetNumberEmpty() {
        Sudoku sudoku = new Sudoku();
        SudokuValue value = new SudokuValue(0, 0, Sudoku.EMPTY);
        sudoku.set(value);
    }

    @Test
    public void testOverwriteNumber() {
        Sudoku sudoku = new Sudoku();
        SudokuValue value = new SudokuValue(0, 0, 7);
        sudoku.set(value);
        assertSame(7, sudoku.get(0, 0).number);
        value = new SudokuValue(0, 0, 8);
        sudoku.set(value);
        assertSame(8, sudoku.get(0, 0).number);
    }

    @Test
    public void testClear() {
        Sudoku sudoku = new Sudoku();
        SudokuValue value = new SudokuValue(0, 0, 7);
        sudoku.set(value);
        sudoku.clear();
        assertSame(Sudoku.EMPTY, sudoku.get(0, 0).number);
    }

}