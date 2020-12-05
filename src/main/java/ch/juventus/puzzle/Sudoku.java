package ch.juventus.puzzle;

import ch.juventus.exceptions.InvalidFieldException;

public class Sudoku implements PuzzleInterface {

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
     * Get a number of a two dimensional matrix
     * @param x the row number of the field starting at 0
     * @param y the column number of the field starting at 0
     * @return the number in the selected field or null
     */
    public int get(int x, int y) {
        return puzzle[x][y];
    }

    /**
     * Get the whole board
     * @return the board as array of integers
     */
    public int[][] get() {
        return puzzle;
    }

    private boolean isNumberInRow(int row, int number) {
        for(int y = 0; y < 9; y++) {
            if(puzzle[row][y] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInColumn(int column, int number) {
        for(int x = 0; x < 9; x++) {
            if(puzzle[x][column] == number) {
                return true;
            }
        }
        return false;
    }


    private boolean isNumberInSquare(int x, int y, int number) {
        int row = x - x % 3;
        int column = y - y % 3;
        for (int i = row; i < row+3; i++) {
            for (int j = column; j < column+3; j++) {
                if (puzzle[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
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
     * Set a number on the selected
     * @param x the row where the number should be placed in
     * @param y the column where the number should be placed in
     * @param number the number to place in the coordinates
     * @throws InvalidFieldException if the number is too high or too low
     */
    public void set(int x, int y, int number) throws InvalidFieldException {
        if(number > 9 || number < 0) {
            throw new InvalidFieldException("the number " + number + " is not a valid sudoku number.");
        }
        this.puzzle[x][y] = number;
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
