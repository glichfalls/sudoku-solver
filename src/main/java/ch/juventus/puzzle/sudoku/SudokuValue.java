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

    public SudokuValue(int x, int y, String value) {
        this.x = x;
        this.y = y;
        this.number = value.equals("") ? Sudoku.EMPTY : Integer.parseInt(value);
    }

    public String toString() {
        return number != Sudoku.EMPTY ? String.valueOf(number) : "";
    }

}
