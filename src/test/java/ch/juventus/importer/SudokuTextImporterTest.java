package ch.juventus.importer;

import ch.juventus.exceptions.ImportException;
import ch.juventus.puzzle.sudoku.Sudoku;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuTextImporterTest {

    @Test
    public void testSuccessfulImport() throws ImportException {
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

    @Test(expected = ImportException.class)
    public void testWrongFormatImport() throws ImportException {
        PuzzleImporter<Sudoku> importer = new SudokuTextImporter();
        importer.getPuzzleFromFile(getClass().getResource("/solvable.json").getPath());
    }

}