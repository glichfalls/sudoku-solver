package ch.juventus.controller;

import ch.juventus.importer.PuzzleImporter;
import ch.juventus.importer.SudokuImporter;
import ch.juventus.puzzle.Sudoku;
import ch.juventus.solver.SolverInterface;
import ch.juventus.solver.SudokuSolver;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class GameController {

    private Sudoku sudoku;
    private final PuzzleImporter<Sudoku> importer = new SudokuImporter();
    private final SolverInterface<Sudoku> solver = new SudokuSolver();
    private TextField[][] sudokuFields = new TextField[9][9];

    public TextField createTextField(int row, int col) {
        TextField textField = new TextField();
        sudokuFields[row][col] = textField;
        textField.getStyleClass().add("textFields");
        return textField;
    }

    public void loadFile() {
        try {
            File file = getSudokuFile();
            sudoku = importer.read(Objects.requireNonNull(file).getPath());
            sudokuFields = new TextField[sudoku.getSize()][sudoku.getSize()];
            fillValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void solveGame() {
        if (solver.solve(sudoku)) {
            fillValues();
        }
    }

    private File getSudokuFile() {
        Stage mainStage = new Stage();
        FileChooser openDialog = new FileChooser();
        openDialog.setTitle("Please chose a sudoku file");
        openDialog.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Sudoku text files", "*.txt")
        );
        return openDialog.showOpenDialog(mainStage);
    }

    private void fillValues() {
        for (int i = 0; i < sudoku.getSize(); i++) {
            for (int j = 0; j < sudoku.getSize(); j++) {
                sudokuFields[i][j].setText(sudoku.get(j, i) != 0 ? String.valueOf(sudoku.get(j, i)) : "");
            }
        }
    }
}