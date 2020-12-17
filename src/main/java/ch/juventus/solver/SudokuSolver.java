package ch.juventus.solver;

import ch.juventus.exceptions.UnsolvableException;
import ch.juventus.puzzle.sudoku.Sudoku;
import ch.juventus.puzzle.sudoku.SudokuValue;

public class SudokuSolver implements Solver<Sudoku> {

    public void solve(Sudoku puzzle) throws UnsolvableException {

        if (!puzzle.isSolvable()) {
            throw new UnsolvableException("The sudoku is unsolvable.");
        }

        // iterate over columns
        for (int x = 0; x < puzzle.getSize(); x++) {

            // iterate over rows
            for (int y = 0; y < puzzle.getSize(); y++) {

                // skip field if it already has a number set
                if (puzzle.isFilled(x, y)) {
                    continue;
                }

                // iterate over all possible numbers
                for (int number = 1; number <= puzzle.getSize(); number++) {

                    // skip number if it violates the constraints
                    if (puzzle.isNumberPresent(x, y, number)) {
                        continue;
                    }

                    try {

                        puzzle.set(new SudokuValue(x, y, number));

                        // try to finish with the new state
                        solve(puzzle);

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

}
