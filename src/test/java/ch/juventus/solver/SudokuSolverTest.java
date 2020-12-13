package ch.juventus.solver;

import ch.juventus.exceptions.ImportException;
import ch.juventus.exceptions.UnsolvableException;
import ch.juventus.exceptions.UnsupportedFormatException;
import ch.juventus.importer.ImporterFactory;
import ch.juventus.puzzle.sudoku.Sudoku;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class SudokuSolverTest {

    ImporterFactory factory = new ImporterFactory();
    Solver<Sudoku> solver = new SudokuSolver();

    @Test
    public void testSolvableSudoku() throws UnsolvableException, ImportException, UnsupportedFormatException {

        File solutionFile = new File(getClass().getResource("/solution.txt").getPath());
        File testFile = new File(getClass().getResource("/solvable.txt").getPath());

        Sudoku solution = factory.getSudokuImporterForFile(solutionFile).getPuzzleFromFile(solutionFile.getPath());
        Sudoku test = factory.getSudokuImporterForFile(testFile).getPuzzleFromFile(testFile.getPath());

        solver.solve(test);

        assertArrayEquals(solution.get(), test.get());

    }

    @Test(expected = UnsolvableException.class)
    public void testUnsolvableSudoku() throws ImportException, UnsolvableException, UnsupportedFormatException {
        File testFile = new File(getClass().getResource("/unsolvable.txt").getPath());
        Sudoku test = factory.getSudokuImporterForFile(testFile).getPuzzleFromFile(testFile.getPath());
        solver.solve(test);
    }

}