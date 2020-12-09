package ch.juventus.puzzle.sudoku;

import ch.juventus.exceptions.InvalidFieldException;
import ch.juventus.puzzle.Puzzle;

public class Sudoku implements Puzzle {

    public static int EMPTY = 0;

    private int size;
    private int[][] puzzle;

    public Sudoku() {
        size = 9;
        puzzle = new int[size][size];
    }

    public Sudoku(int[][] puzzle) {
        this.puzzle = puzzle;
        size = puzzle.length;
    }

    /**
     * Get the dimension of the board
     * @return the size of the board
     */
    public int getSize() {
        return size;
    }

    /**
     * Check if the field is empty
     * @param x row
     * @param y column
     * @return is set
     */
    public boolean isFilled(int x, int y) {
        return get(x, y).number != Sudoku.EMPTY;
    }

    /**
     * Get a number of a two dimensional matrix
     * @param x the row number of the field starting at 0
     * @param y the column number of the field starting at 0
     * @return the number in the selected field or null
     */
    public SudokuValue get(int x, int y) {
        return new SudokuValue(x, y, puzzle[x][y]);
    }

    /**
     * Get the whole board
     * @return the board as array of integers
     */
    public int[][] get() {
        return puzzle;
    }

    private boolean isNumberInRow(int row, int number) {
        return countOccurrencesInRow(row, number) > 0;
    }

    private int countOccurrencesInRow(int x, int number) {
        int occurrences = 0;
        for(int y = 0; y < 9; y++) {
            if(puzzle[x][y] == number) {
                occurrences++;
            }
        }
        return occurrences;
    }

    private boolean isNumberInColumn(int column, int number) {
        return countOccurrencesInColumn(column, number) > 0;
    }

    private int countOccurrencesInColumn(int y, int number) {
        int occurrences = 0;
        for(int x = 0; x < 9; x++) {
            if(puzzle[x][y] == number) {
                occurrences++;
            }
        }
        return occurrences;
    }

    private boolean isNumberInSquare(int x, int y, int number) {
        return countOccurrencesInSquare(x, y, number) > 0;
    }

    private int countOccurrencesInSquare(int x, int y, int number) {
        int occurrences = 0;
        int row = x - x % 3;
        int column = y - y % 3;
        for (int i = row; i < row + 3; i++) {
            for (int j = column; j < column + 3; j++) {
                if (puzzle[i][j] == number) {
                    occurrences++;
                }
            }
        }
        return occurrences;
    }

    /**
     * Check all constraints for the input number
     * @param row the row
     * @param col the column
     * @param number the number to check
     * @return true if the number can be placed in this field or false if not
     */
    public boolean isNumberPresent(int row, int col, int number) {
        return isNumberInRow(row, number) || isNumberInColumn(col, number) || isNumberInSquare(row, col, number);
    }

    public boolean isNumberPresentMoreThanOnce(int x, int y, int number) {
        return countOccurrencesInRow(x, number) +
                countOccurrencesInColumn(y, number) +
                countOccurrencesInSquare(x, y, number)
                > 3;
    }

    /**
     * Set a number on the selected
     * @param x the row where the number should be placed in
     * @param y the column where the number should be placed in
     * @param number the number to place in the coordinates
     * @throws InvalidFieldException if the number is too high or too low
     */
    public void set(SudokuValue value) throws InvalidFieldException {
        if(value.number > getSize() || value.number < 0) {
            throw new InvalidFieldException("the number " + value.number + " is not a valid sudoku number.");
        }
        System.out.println(value.x + "," + value.y + ":" + value.number);
        this.puzzle[value.x][value.y] = value.number;
    }

    public void clear(int x, int y) {
        puzzle[x][y] = Sudoku.EMPTY;
    }

    public void clear() {
        puzzle = new int[size][size];
    }

    public void print() {
        for(int[] row : puzzle) {
            for(int column : row) {
                System.out.print(column);
            }
            System.out.print("\n");
        }
    }

}
