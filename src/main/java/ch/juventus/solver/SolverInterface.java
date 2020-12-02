package ch.juventus.solver;

import ch.juventus.exceptions.UnsolvableException;
import ch.juventus.puzzle.PuzzleInterface;

public interface SolverInterface<T extends PuzzleInterface> {

    /**
     * Solve a puzzle
     * @param puzzle the puzzle to solve
     */
    void solve(T puzzle) throws UnsolvableException;

}
