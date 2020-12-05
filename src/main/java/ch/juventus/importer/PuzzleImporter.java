package ch.juventus.importer;

import ch.juventus.exceptions.ImportException;
import ch.juventus.puzzle.Puzzle;

public interface PuzzleImporter<T extends Puzzle> {

    T getPuzzleFromFile(String path) throws ImportException;

}
