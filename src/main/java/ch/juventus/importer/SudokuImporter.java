package ch.juventus.importer;

import ch.juventus.exceptions.ImportException;
import ch.juventus.exceptions.InvalidFieldException;
import ch.juventus.puzzle.Sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SudokuImporter {

    private int size;

    public Sudoku read(String path) throws ImportException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            setSizeFromFile();
            return new Sudoku(getNumbersFromFile(reader));
        } catch (Exception e) {
            throw new ImportException(e.getMessage());
        }
    }

    private void setSizeFromFile() {
        size = 9;
    }

    private Integer[][] getNumbersFromFile(BufferedReader reader) throws IOException, InvalidFieldException {
        Integer[][] puzzle = new Integer[size][size];
        for(int x = 0; x < size; x++) {
            puzzle[x] = getNumbersFromLine(reader.readLine());
        }
        return puzzle;
    }

    private Integer[] getNumbersFromLine(String line) throws IOException, InvalidFieldException {
        String[] values = line.split(";");
        Integer[] numbers = new Integer[size];
        for(int i = 0; i < size; i++) {
            numbers[i] = getNumber(values[i]);
        }
        return numbers;
    }

    private Integer getNumber(String value) throws IOException, InvalidFieldException {
        try {
            int number = value.equals("") ? 0 : Integer.parseInt(value);
            if(number <= 0 || number > size) {
                // catch out of bound
                throw new InvalidFieldException("The number " + value + " does not fit in this sudoku.");
            }
            return number;
        } catch (NumberFormatException e) {
            // catch wrong format
            throw new IOException("invalid character `" + value + "` in input file");
        } catch (IndexOutOfBoundsException e) {
            // catch empty index after last number
            return 0;
        }
    }

}
