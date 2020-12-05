package ch.juventus.solver;

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

        if(!isSolvable(puzzle)) {
            throw new UnsolvableException("The sudoku is unsolvable.");
        }

        // iterate over columns
        for (int x = 0; x < puzzle.getSize(); x++) {

            // iterate over rows
            for (int y = 0; y < puzzle.getSize(); y++) {

                if(puzzle.isFilled(x, y)) {
                    continue;
                }

                // iterate over all possible numbers
                for (int number = 1; number <= puzzle.getSize(); number++) {

                    if (puzzle.isNumberPresent(x, y, number)) {
                        continue;
                    }

                    try {
                        // try to finish with the added number
                        puzzle.set(x, y, number);
                        solve(puzzle);
                        logger.debug("sudoku is solvable.");
                        return;
                    } catch (UnsolvableException e) {
                        puzzle.clear(x, y);
                    }

                }

                // if no number possible
                throw new UnsolvableException("The sudoku can't be solved");

            }
        }
    }

    private boolean isSolvable(Sudoku sudoku) {
        for(int x = 0; x < sudoku.getSize(); x++) {
            for(int y = 0; y < sudoku.getSize(); y++) {
                if(sudoku.isFilled(x, y) && sudoku.isNumberPresentMoreThanOnce(x, y, sudoku.get(x, y))) {
                    logger.warn("The number " + sudoku.get(x, y) + " in " + x + ", " + y + " occurs more than once.");
                    return false;
                }
            }
        }
        return true;
    }

}
