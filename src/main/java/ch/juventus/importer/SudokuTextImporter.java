package ch.juventus.importer;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.ArrayList;

public class SudokuTextImporter extends SudokuImporter {

    @Override
    protected int getDimension(ArrayList<String> lines) {
        int max = 0;
        int lineCount = 0;
        for(String line : lines) {
            lineCount++;
            int len = line.split(";", -1).length;
            if(len > max) {
                max = len;
            }
        }
        // because a sudoku has always to be a square, return the higher number
        return Math.max(lineCount, max);
    }

    @Override
    protected int getDimension(String content) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    protected int[][] getNumbers(ArrayList<String> lines) throws IOException {
        int[][] puzzle = new int[size][];
        for(int x = 0; x < size; x++) {
            puzzle[x] = getNumbersFromLine(lines.get(x));
        }
        return puzzle;
    }

    @Override
    protected int[][] getNumbers(String content) throws IOException {
        throw new NotImplementedException();
    }

    private int[] getNumbersFromLine(String line) throws IOException {
        String[] values = line.split(";", -1);
        int[] numbers = new int[size];
        for(int i = 0; i < size; i++) {
            numbers[i] = getNumber(values.length >= i ? values[i] : "");
        }
        return numbers;
    }

}
