package ch.juventus.solver;

import ch.juventus.exceptions.ImportException;
import ch.juventus.exceptions.InvalidFieldException;
import ch.juventus.exceptions.UnsolvableException;
import ch.juventus.importer.PuzzleImporter;
import ch.juventus.importer.SudokuTextImporter;
import ch.juventus.puzzle.Sudoku;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuSolverTest {

    PuzzleImporter<Sudoku> importer = new SudokuTextImporter();
    Solver<Sudoku> solver = new SudokuSolver();

    @Test
    public void testSolvableSudoku() throws UnsolvableException, ImportException, InvalidFieldException {

        Sudoku solution = importer.getPuzzleFromFile(getClass().getResource("/solution.txt").getPath());
        Sudoku test = importer.getPuzzleFromFile(getClass().getResource("/solvable.txt").getPath());

        solver.solve(test);

        assertArrayEquals(solution.get(), test.get());

    }

    @Test(expected = UnsolvableException.class)
    public void testUnsolvableSudoku() throws ImportException, UnsolvableException {
        Sudoku test = importer.getPuzzleFromFile(getClass().getResource("/unsolvable.txt").getPath());
        solver.solve(test);
    }

}