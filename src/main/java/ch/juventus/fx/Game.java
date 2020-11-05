package ch.juventus.fx;

import ch.juventus.importer.SudokuImport;
import ch.juventus.puzzle.Sudoku;

import java.io.IOException;

public class Game {

    public void run() {
        SudokuImport importer = new SudokuImport();
        try {
            Sudoku sudoku = importer.read("test.txt");
            sudoku.print();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }

}
