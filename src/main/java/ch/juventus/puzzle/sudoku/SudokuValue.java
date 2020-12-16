package ch.juventus.puzzle.sudoku;

public class SudokuValue {

    private final int x;
    private final int y;
    private final int number;

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNumber() {
        return number;
    }

    public String toString() {
        return getNumber() != Sudoku.EMPTY ? String.valueOf(getNumber()) : "";
    }

}
