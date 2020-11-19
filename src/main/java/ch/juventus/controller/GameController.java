package ch.juventus.controller;

import ch.juventus.exceptions.InvalidFieldException;
import ch.juventus.importer.SudokuImporter;
import ch.juventus.puzzle.Sudoku;
import ch.juventus.solver.SudokuSolver;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class GameController {
    private Sudoku sudoku;
    private final SudokuImporter importer = new SudokuImporter();
    private final SudokuSolver solver = new SudokuSolver();
    private TextField[][] sudokuFields = new TextField[9][9];

    public TextField createTextField(int row, int col) {
        TextField textField = new TextField();
        sudokuFields[row][col] = textField;
        textField.getStyleClass().add("textFields");
        return textField;
    }

    public void loadFile() {
        File sudokufile = showFileChooser();
        try {
            sudoku = importer.read(sudokufile.getPath());
            fillValues();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void solveGame() {
        try {
            if (solver.solve(sudoku)) {
                fillValues();
            }
        } catch (InvalidFieldException e) {
            e.printStackTrace();
        }
    }

    private File showFileChooser() {
        Stage mainStage = new Stage();
        FileChooser openDialog = new FileChooser();

        openDialog.setTitle("Öffne Sudokudatei");
        openDialog.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Sudoku text files", "*.txt")
        );

        File file = openDialog.showOpenDialog(mainStage);
        if (file != null) {
            return file;
        }

        return null;
    }

    private void fillValues() {
        for (int i = 0; i < sudoku.getSize(); i++) {
            for (int j = 0; j < sudoku.getSize(); j++) {
                sudokuFields[i][j].setText(sudoku.get(j, i) != 0 ? sudoku.get(j, i).toString() : "");
            }
        }
    }
}