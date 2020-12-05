package ch.juventus.solver;

import ch.juventus.exceptions.UnsolvableException;
import ch.juventus.puzzle.Puzzle;

public interface Solver<T extends Puzzle> {

    /**
     * Solve a puzzle
     * @param puzzle the puzzle to solve
     */
    void solve(T puzzle) throws UnsolvableException;

}
