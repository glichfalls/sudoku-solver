package ch.juventus.solver;

import ch.juventus.puzzle.PuzzleInterface;

public interface SolverInterface<T extends PuzzleInterface> {

    /**
     * Solve a puzzle
     * @param puzzle the puzzle to solve
     * @return true if it can be solved or false if not
     */
    boolean solve(T puzzle);

}
