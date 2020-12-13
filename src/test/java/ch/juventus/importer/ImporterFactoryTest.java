package ch.juventus.importer;

import ch.juventus.exceptions.UnsupportedFormatException;
import ch.juventus.puzzle.sudoku.Sudoku;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ImporterFactoryTest {

    private ImporterFactory factory = new ImporterFactory();

    @Test
    public void testJsonImporter() throws UnsupportedFormatException {
        File json = new File(getClass().getResource("/solvable.json").getPath());
        PuzzleImporter<Sudoku> importer = factory.getSudokuImporterForFile(json);
        assertTrue(importer instanceof SudokuJsonImporter);
    }

    @Test
    public void testTextImporter() throws UnsupportedFormatException {
        File text = new File(getClass().getResource("/solvable.txt").getPath());
        PuzzleImporter<Sudoku> importer = factory.getSudokuImporterForFile(text);
        assertTrue(importer instanceof SudokuTextImporter);
    }

    @Test(expected = UnsupportedFormatException.class)
    public void testUnsupportedFile() throws UnsupportedFormatException {
        File xml = new File(getClass().getResource("/unsupported.xml").getPath());
        factory.getSudokuImporterForFile(xml);
    }

}