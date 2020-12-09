package ch.juventus.importer;

import ch.juventus.exceptions.UnsupportedFormatException;
import ch.juventus.puzzle.sudoku.Sudoku;
import ch.juventus.util.FileExtension;

import java.io.File;

public class ImporterFactory {

    public PuzzleImporter<Sudoku> getSudokuImporterForFile(File file) throws UnsupportedFormatException {
        String type = FileExtension.getExtensionFromFile(file);
        switch (type) {
            case "json": return new SudokuJsonImporter();
            case "txt": return new SudokuTextImporter();
            default: throw new UnsupportedFormatException("Der Dateityp `" + type + "` wird nicht unterstützt.");
        }
    }

}
