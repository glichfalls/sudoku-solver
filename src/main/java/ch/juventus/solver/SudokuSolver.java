package ch.juventus.solver;

import ch.juventus.exceptions.InvalidFieldException;
import ch.juventus.puzzle.Sudoku;

public class SudokuSolver {

    public boolean solve(Sudoku puzzle) throws InvalidFieldException {
        for (int row = 0; row < puzzle.getSize(); row++) {
            for (int col = 0; col < puzzle.getSize(); col++) {
                // check if the position is not set already
                if (puzzle.get(row, col) == Sudoku.EMPTY) {
                    for (int number = 1; number <= puzzle.getSize(); number++) {
                        // check if the current number can be placed at the current position
                        if (puzzle.isNumberNotPresent(row, col, number)) {
                            puzzle.set(row, col, number);
                            // try to finish with the set value
                            if (solve(puzzle)) {
                                return true;
                            } else {
                                // reset and go to next number if it cannot be solved with the number set
                                puzzle.set(row, col, Sudoku.EMPTY);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

}
