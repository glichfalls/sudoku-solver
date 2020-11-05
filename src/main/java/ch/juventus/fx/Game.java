package ch.juventus.fx;

import ch.juventus.exceptions.InvalidFieldException;
import ch.juventus.importer.SudokuImport;
import ch.juventus.puzzle.Sudoku;
import ch.juventus.solver.SudokuSolver;

import java.io.IOException;

public class Game {

    public void run() {
        SudokuImport importer = new SudokuImport();
        try {
            Sudoku sudoku = importer.read("test.txt");
            SudokuSolver solver = new SudokuSolver();
            if(solver.solve(sudoku)) {
                System.out.println("solved!");
            } else {
                System.out.println("failed!");
            }
            sudoku.print();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InvalidFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }

}
