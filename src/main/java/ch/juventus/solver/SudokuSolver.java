package ch.juventus.solver;

import ch.juventus.exceptions.InvalidFieldException;
import ch.juventus.exceptions.UnsolvableException;
import ch.juventus.puzzle.Sudoku;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SudokuSolver implements SolverInterface<Sudoku> {

    private Logger logger;

    public SudokuSolver() {
        logger = LoggerFactory.getLogger(SudokuSolver.class);
    }

    public void solve(Sudoku puzzle) throws UnsolvableException {
        for (int x = 0; x < puzzle.getSize(); x++) {
            solveColumn(puzzle, x);
        }
    }

    private void solveColumn(Sudoku puzzle, int x) throws UnsolvableException {
        for (int y = 0; y < puzzle.getSize(); y++) {
            // check if the position is not set already
            if (puzzle.get(x, y) == Sudoku.EMPTY) {
                tryNumbersForField(puzzle, x, y);
            }
        }
    }

    private void tryNumbersForField(Sudoku puzzle, int x, int y) throws UnsolvableException {
        for (int number = 1; number <= puzzle.getSize(); number++) {
            try {
                setNumber(puzzle, x, y, number);
            } catch (InvalidFieldException e) {
                logger.error(e.getMessage());
            }
        }
        throw new UnsolvableException("The sudoku can't be solved");
    }

    private void setNumber(Sudoku puzzle, int x, int y, int number) throws InvalidFieldException {
        // check if the current number can be placed at the current position
        if (!puzzle.isNumberPresent(x, y, number)) {
            puzzle.set(x, y, number);
            // try to finish with the set value
            try {
                solve(puzzle);
            } catch (UnsolvableException e) {
                // reset and go to next number if it cannot be solved with the number set
                puzzle.set(x, y, Sudoku.EMPTY);
            }
        }
    }

}
