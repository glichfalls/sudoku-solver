package ch.juventus.puzzle.sudoku;

public class SudokuValue {

    public int x;
    public int y;
    public int number;

    public SudokuValue(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.number = value;
    }

    public String toString() {
        return number != Sudoku.EMPTY ? String.valueOf(number) : "";
    }

}
