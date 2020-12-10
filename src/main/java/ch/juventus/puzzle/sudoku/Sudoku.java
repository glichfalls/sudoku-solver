package ch.juventus.puzzle.sudoku;

import ch.juventus.exceptions.InvalidFieldException;
import ch.juventus.puzzle.Puzzle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sudoku implements Puzzle {

    private Logger logger = LoggerFactory.getLogger(Sudoku.class);

    public static int EMPTY = 0;

    private int size;
    private int[][] puzzle;

    public Sudoku() {
        size = 9;
        puzzle = new int[size][size];
        logger.debug("initialized default sudoku.");
    }

    public Sudoku(int size, int[][] puzzle) {
        this.size = size;
        this.puzzle = puzzle;
        logger.debug("initialized new sudoku with size " + size + ".");
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

    /**
     * Check if a number occurs in a row
     * @param row the row to check
     * @param number the number to check
     * @return boolean
     */
    private boolean isNumberInRow(int row, int number) {
        return countOccurrencesInRow(row, number) > 0;
    }

    /**
     * Count the occurrences of a number in a row
     * @param row the row
     * @param number the number to check
     * @return the number of occurrences in the row
     */
    private int countOccurrencesInRow(int row, int number) {
        int occurrences = 0;
        for(int y = 0; y < 9; y++) {
            if(puzzle[row][y] == number) {
                occurrences++;
            }
        }
        return occurrences;
    }

    /**
     * Check if a number occurs in a column
     * @param column the column to search in
     * @param number the searched number
     * @return boolean
     */
    private boolean isNumberInColumn(int column, int number) {
        return countOccurrencesInColumn(column, number) > 0;
    }

    /**
     * Count all occurrences of a number in a column
     * @param column the column to search in
     * @param number the searched number
     * @return the number of occurrences in the column
     */
    private int countOccurrencesInColumn(int column, int number) {
        int occurrences = 0;
        for(int x = 0; x < 9; x++) {
            if(puzzle[x][column] == number) {
                occurrences++;
            }
        }
        return occurrences;
    }

    /**
     * Check if a number occurs in a square
     * @param x the x point of the searched number
     * @param y the y point of the searched number
     * @param number the number to search
     * @return boolean
     */
    private boolean isNumberInSquare(int x, int y, int number) {
        return countOccurrencesInSquare(x, y, number) > 0;
    }

    /**
     *
     * @param x the x point of the searched number
     * @param y the y point of the searched number
     * @param number the number to count
     * @return the number of occurrences of the number in the square
     */
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

    /**
     * Check if a number occurs more than once in either the row, the column or the square
     * @param x the x point
     * @param y the y point
     * @param number the number to check
     * @return true if it is present more than once
     */
    public boolean isNumberPresentMoreThanOnce(int x, int y, int number) {
        return countOccurrencesInRow(x, number) +
                countOccurrencesInColumn(y, number) +
                countOccurrencesInSquare(x, y, number)
                > 3;
    }

    /**
     *  Set a value in the sudoku matrix
     * @param value the Sudoku value with x and y positions
     * @throws InvalidFieldException if the value does not fit in the sudoku
     */
    public void set(SudokuValue value) throws InvalidFieldException {
        if(value.number > getSize() || value.number < 0) {
            throw new InvalidFieldException("the number " + value.number + " is not a valid sudoku number.");
        }
        System.out.println(value.x + "," + value.y + ":" + value.number);
        logger.debug("Setting value in [" + value.x + "," + value.y + "] from " + puzzle[value.x][value.y] + " to " + value.number + "");
        puzzle[value.x][value.y] = value.number;
    }

    /**
     * Clear a number in the sudoku
     * @param x point
     * @param y point
     */
    public void clear(int x, int y) {
        puzzle[x][y] = Sudoku.EMPTY;
    }

    /**
     * Clear the whole sudoku
     */
    public void clear() {
        puzzle = new int[size][size];
        logger.debug("cleared sudoku.");
    }

    /**
     * print the current state of the sudoku
     */
    public void print() {
        for(int[] row : puzzle) {
            for(int column : row) {
                System.out.print(column);
            }
            System.out.print("\n");
        }
    }

}
