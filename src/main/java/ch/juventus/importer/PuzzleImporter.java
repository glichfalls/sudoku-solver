package ch.juventus.importer;

import ch.juventus.exceptions.ImportException;
import ch.juventus.puzzle.PuzzleInterface;

import java.io.IOException;

public interface PuzzleImporter<T extends PuzzleInterface> {

    T read(String path) throws IOException, ImportException;

}
