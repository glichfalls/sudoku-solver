package ch.juventus.importer;

import ch.juventus.puzzle.Sudoku;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SudokuImportTest {

    @Test
    public void testValidImport() throws IOException {
        SudokuImporter importer = new SudokuImporter();
        Sudoku sudoku = importer.read("test.txt");
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

}