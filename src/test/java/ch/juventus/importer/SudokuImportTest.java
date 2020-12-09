package ch.juventus.importer;

import ch.juventus.exceptions.ImportException;
import ch.juventus.puzzle.sudoku.Sudoku;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuImportTest {

    @Test
    public void testValidTextImport() throws ImportException {
        PuzzleImporter<Sudoku> importer = new SudokuTextImporter();
        Sudoku sudoku = importer.getPuzzleFromFile(getClass().getResource("/solvable.txt").getPath());
        Integer[][] expected = {
            {9,5,8,2,0,0,7,0,0},
            {0,0,0,9,0,4,6,0,0},
            {0,7,0,8,0,0,0,1,0},
            {0,4,7,0,1,9,0,6,8},
            {0,0,5,0,8,0,0,0,0},
            {0,6,0,0,5,0,0,0,0},
            {5,0,0,0,9,8,0,0,3},
            {0,8,1,0,0,0,9,0,0},
            {0,0,0,6,2,0,0,0,0},
        };
        assertArrayEquals(expected, sudoku.get());
    }

    @Test
    public void testValidJsonImport() throws ImportException {
        PuzzleImporter<Sudoku> importer = new SudokuJsonImporter();
        Sudoku sudoku = importer.getPuzzleFromFile(getClass().getResource("/solvable.json").getPath());
        int[][] expected = {
            {7,0,0,0,0,0,0,6,4},
            {2,0,0,3,0,7,0,8,1},
            {0,8,5,0,1,4,0,0,3},
            {0,7,9,0,0,0,2,0,0},
            {0,3,1,9,7,2,0,4,0},
            {6,0,2,0,0,0,1,7,9},
            {3,2,0,0,5,9,8,0,7},
            {4,0,0,7,0,1,0,5,2},
            {0,5,0,2,0,3,0,0,0}
        };
        assertArrayEquals(expected, sudoku.get());
    }

}