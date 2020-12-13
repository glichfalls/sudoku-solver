package ch.juventus.importer;

import ch.juventus.exceptions.ImportException;
import ch.juventus.exceptions.InvalidFieldException;
import ch.juventus.puzzle.sudoku.Sudoku;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class SudokuImporter implements PuzzleImporter<Sudoku> {

    private Logger logger = LoggerFactory.getLogger(SudokuImporter.class);

    protected int size;

    public Sudoku getPuzzleFromFile(String path) throws ImportException {
        try {
            ArrayList<String> lines = read(path);
            size = getDimension(lines);
            return new Sudoku(size, getNumbers(lines));
        } catch (IOException e) {
            logger.error("Failed to import sudoku: {}.", e.getMessage());
            throw new ImportException(e.getMessage());
        }
    }

    public Sudoku getPuzzleFromString(String puzzle) throws ImportException {
        try {
            size = getDimension(puzzle);
            return new Sudoku(size, getNumbers(puzzle));
        } catch (IOException e) {
            logger.error("Failed to import sudoku: {}.", e.getMessage());
            throw new ImportException(e.getMessage());
        }
    }

    private ArrayList<String> read(String path) throws ImportException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (Exception e) {
            throw new ImportException(e.getMessage());
        }
    }

    protected int getNumber(String value) throws IOException {
        try {
            int number = value.equals("") ? 0 : Integer.parseInt(value);
            if(number < 0 || number > size) {
                // catch out of bound
                throw new InvalidFieldException("The number `" + number + "` does not fit in this sudoku.");
            }
            return number;
        } catch (NumberFormatException e) {
            // catch wrong format
            throw new IOException("invalid character `" + value + "` in input file.");
        }
    }

    protected abstract int getDimension(ArrayList<String> lines) throws IOException;

    protected abstract int getDimension(String content) throws IOException;

    protected abstract int[][] getNumbers(ArrayList<String> lines) throws IOException;

    protected abstract int[][] getNumbers(String content) throws IOException;

}
