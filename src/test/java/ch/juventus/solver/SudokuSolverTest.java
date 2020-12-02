package ch.juventus.solver;

import ch.juventus.exceptions.InvalidFieldException;
import ch.juventus.exceptions.UnsolvableException;
import ch.juventus.importer.SudokuImporter;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SudokuSolverTest {

    @Test
    public void testValidSolve() throws IOException, UnsolvableException {
        SudokuImporter importer = new SudokuImporter();
        SudokuSolver solver = new SudokuSolver();
        solver.solve(importer.read(getClass().getResource("/test.txt").getPath()));
        assertTrue(true);
    }

}