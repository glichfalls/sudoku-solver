package ch.juventus.puzzle;

import ch.juventus.exceptions.InvalidFieldException;

public class Sudoku implements PuzzleInterface {

    public static int EMPTY = 0;

    private int size;
    private Integer[][] puzzle;

    public Sudoku(Integer[][] puzzle) {
        this.puzzle = puzzle;
        this.size = puzzle.length;
    }

    public int getSize() {
        return size;
    }

    public Integer get(int x, int y) {
        return puzzle[x][y];
    }

    public Integer[][] get() {
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

    public boolean isNumberNotPresent(int row, int col, int number) {
        return !isNumberInRow(row, number) && !isNumberInColumn(col, number) && !isNumberInSquare(row, col, number);
    }

    public void set(int x, int y, int number) throws InvalidFieldException {
        if(number > 9 || number < 0) {
            throw new InvalidFieldException("the number " + number + " is not a valid sudoku number.");
        }
        this.puzzle[x][y] = number;
    }

    public void print() {
        for(Integer[] row : puzzle) {
            for(Integer column : row) {
                System.out.print(column);
            }
            System.out.print("\n");
        }
    }

}
