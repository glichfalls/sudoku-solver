package ch.juventus.solver;

import ch.juventus.exceptions.ImportException;
import ch.juventus.exceptions.InvalidFieldException;
import ch.juventus.importer.SudokuImporter;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuSolverTest {

    @Test
    public void testValidSolve() throws InvalidFieldException, ImportException {
        SudokuImporter importer = new SudokuImporter();
        SudokuSolver solver = new SudokuSolver();
        assertTrue(solver.solve(importer.read(getClass().getResource("/test.txt").getPath())));
    }

    @Test
    public void testUnsolvable() throws InvalidFieldException, ImportException {
        SudokuImporter importer = new SudokuImporter();
        SudokuSolver solver = new SudokuSolver();
        assertFalse(solver.solve(importer.read(getClass().getResource("/unsolvable.txt").getPath())));
    }

}