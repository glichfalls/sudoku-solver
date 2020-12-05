package ch.juventus.solver;

import ch.juventus.exceptions.UnsolvableException;
import ch.juventus.importer.SudokuImporter;
import ch.juventus.puzzle.Sudoku;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SudokuSolverTest {

    @Test
    public void testValidSolve() throws IOException, UnsolvableException {

        SudokuImporter importer = new SudokuImporter();
        SudokuSolver solver = new SudokuSolver();

        Sudoku solution = importer.read(getClass().getResource("/solution.txt").getPath());
        Sudoku test = importer.read(getClass().getResource("/test.txt").getPath());

        solver.solve(test);

        test.print();

        assertArrayEquals(solution.get(), test.get());

    }

}