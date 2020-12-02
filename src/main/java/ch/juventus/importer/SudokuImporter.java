package ch.juventus.importer;

import ch.juventus.puzzle.Sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SudokuImporter implements PuzzleImporter<Sudoku> {

    public Sudoku read(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        int[][] puzzle = new int[9][9];
        String line;
        for(int x = 0; x < 9; x++) {
            line = reader.readLine();
            String[] values = line.split(";");
            int[] numbers = new int[9];
            for(int i = 0; i < 9; i++) {
                try {
                    numbers[i] = values[i].equals("") ? 0 : Integer.parseInt(values[i]);
                } catch (NumberFormatException e) {
                    // catch wrong format
                    throw new IOException("invalid character `" + values[i] + "` in input file");
                } catch (IndexOutOfBoundsException e) {
                    // catch empty index after last number
                    numbers[i] = 0;
                }
            }
            puzzle[x] = numbers;
        }
        reader.close();
        return new Sudoku(puzzle);
    }

}
