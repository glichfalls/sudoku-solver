package ch.juventus.importer;

import ch.juventus.exceptions.ImportException;
import ch.juventus.puzzle.sudoku.Sudoku;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuJsonImporterTest {

    @Test
    public void testSuccessfulImport() throws ImportException {
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

    @Test(expected = ImportException.class)
    public void testWrongFormatImport() throws ImportException {
        PuzzleImporter<Sudoku> importer = new SudokuJsonImporter();
        importer.getPuzzleFromFile(getClass().getResource("/solvable.txt").getPath());
    }

}