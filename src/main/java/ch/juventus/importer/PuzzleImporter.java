package ch.juventus.importer;

import ch.juventus.exceptions.ImportException;
import ch.juventus.puzzle.Puzzle;

public interface PuzzleImporter<T extends Puzzle> {

    /**
     * Load a puzzle from a file
     * @param path the path to the file
     * @return the created puzzle
     * @throws ImportException if the puzzle cannot be created
     */
    T getPuzzleFromFile(String path) throws ImportException;

    /**
     * Load a puzzle from a JSON string
     * @param puzzle decoded json data
     * @return the created puzzle
     * @throws ImportException if the puzzle cannot be created
     */
    T getPuzzleFromString(String puzzle) throws ImportException;

}
