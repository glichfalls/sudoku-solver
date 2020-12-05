package ch.juventus.solver;

import ch.juventus.exceptions.ImportException;
import ch.juventus.exceptions.InvalidFieldException;
import ch.juventus.exceptions.UnsolvableException;
import ch.juventus.importer.PuzzleImporter;
import ch.juventus.importer.SudokuImporter;
import ch.juventus.puzzle.Sudoku;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SudokuSolverTest {

    PuzzleImporter<Sudoku> importer = new SudokuImporter();
    SolverInterface<Sudoku> solver = new SudokuSolver();

    @Test
    public void testSolvableSudoku() throws UnsolvableException, ImportException, InvalidFieldException, IOException {

        Sudoku solution = importer.read(getClass().getResource("/solution.txt").getPath());
        Sudoku test = importer.read(getClass().getResource("/solvable.txt").getPath());

        solver.solve(test);

        assertArrayEquals(solution.get(), test.get());

    }

    @Test(expected = UnsolvableException.class)
    public void testUnsolvableSudoku() throws IOException, ImportException, UnsolvableException {
        Sudoku test = importer.read(getClass().getResource("/unsolvable.txt").getPath());
        solver.solve(test);
    }

}