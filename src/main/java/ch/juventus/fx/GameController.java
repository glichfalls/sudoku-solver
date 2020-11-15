package ch.juventus.fx;

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
    private SudokuImporter importer = new SudokuImporter();
    private SudokuSolver solver = new SudokuSolver();
    private TextField[][] sudokuFields = new TextField[9][9];
    private Sudoku sudoku;

    public TextField createTextField(int row, int col) {
        TextField textField = new TextField();
        sudokuFields[row][col] = textField;
        textField.getStyleClass().add("textFields");
        return textField;
    }

    public void loadJSON() {
        File sudokufile = showFileChooser();
        try {
            sudoku = decodeFile(sudokufile);
            fillValues();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void solveGame() {
        try {
            if (solver(sudoku)) {
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

    private boolean getValues() throws InvalidFieldException {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku.set(i,j,Integer.valueOf(sudokuFields[j][i].getText()));
            }
        }
        return true;
    }

    private void fillValues() {
        for (int i = 0; i < sudoku.getSize(); i++) {
            for (int j = 0; j < sudoku.getSize(); j++) {
                sudokuFields[i][j].setText(String.valueOf(sudoku.get(j,i)));
            }
        }
    }

    private boolean solver(Sudoku puzzle) throws InvalidFieldException {
        if(solver.solve(puzzle)) {
            return true;
        }
        return false;
    }

    private Sudoku decodeFile(File sourceFile) throws IOException {
        return importer.read(sourceFile.getPath());
    }

}