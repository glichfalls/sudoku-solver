package ch.juventus.puzzle;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

public class Sudoku {

    public Integer[][] puzzle;

    public Sudoku(Integer[][] puzzle) {
        this.puzzle = puzzle;
    }

    public Integer get(int x, int y) {
        return puzzle[x][y];
    }

    public Integer[] get(int x) {
        return puzzle[x];
    }

    public void set(int x, int y, int number) {
        if(number > 9 || number < 0) {
            throw new ValueException("the number " + number + " is not a valid sudoku number.");
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
