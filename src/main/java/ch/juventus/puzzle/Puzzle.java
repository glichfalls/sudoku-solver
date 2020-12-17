package ch.juventus.puzzle;

public interface Puzzle {

    /**
     * Check if the puzzle in the current state is solvable
     * @return boolean
     */
    boolean isSolvable();

    /**
     * Print the current state of the board
     */
    void print();

}
