package ch.juventus.puzzle;

public class Sudoku {

    public int[][] puzzle;

    public Sudoku(int[][] puzzle) {
        this.puzzle = puzzle;
    }

    public int get(int x, int y) {
        return puzzle[x][y];
    }

    public void set(int x, int y, int number) {
        this.puzzle[x][y] = number;
    }

}
