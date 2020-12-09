package ch.juventus.importer;

import ch.juventus.exceptions.ImportException;
import ch.juventus.exceptions.InvalidFieldException;
import ch.juventus.puzzle.sudoku.Sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class SudokuImporter implements PuzzleImporter<Sudoku> {

    protected int size;

    public Sudoku getPuzzleFromFile(String path) throws ImportException {
        try {
            ArrayList<String> lines = read(path);
            size = getDimensionFromFileContent(lines);
            return new Sudoku(getNumbersFromFileContent(lines));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ImportException(e.getMessage());
        }
    }

    private ArrayList<String> read(String path) {
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

    protected int getNumber(String value) throws IOException, InvalidFieldException {
        try {
            int number = value.equals("") ? 0 : Integer.parseInt(value);
            if(number < 0 || number > size) {
                // catch out of bound
                throw new InvalidFieldException("The number `" + number + "` does not fit in this sudoku.");
            }
            return number;
        } catch (NumberFormatException e) {
            // catch wrong format
            throw new IOException("invalid character `" + value + "` in input file");
        }
    }

    protected abstract int getDimensionFromFileContent(ArrayList<String> lines) throws IOException;

    protected abstract int[][] getNumbersFromFileContent(ArrayList<String> lines) throws IOException;

}
