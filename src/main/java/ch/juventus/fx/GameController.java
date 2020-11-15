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
    private TextField[][] sudokuFields = new TextField[9][9];

    public TextField createTextField(int row, int col) {
        TextField textField = new TextField();
        sudokuFields[row][col] = textField;
        textField.getStyleClass().add("textFields");
        return textField;
    }

    public void loadJSON() {
        File sudokufile = showFileChooser();
        try {
            fillValues(decodeFile(sudokufile));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void solveGame() {
        try {
            if (solver(getValues())) {
                System.out.println(solver(getValues()));
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

    private Sudoku getValues() {
        Integer[][] puzzle = new Integer[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                puzzle[i][j] = Integer.valueOf(sudokuFields[j][i].getText());
            }
        }
        return new Sudoku(puzzle);
    }

    private void fillValues(Sudoku values) {
        for (int i = 0; i < values.getSize(); i++) {
            for (int j = 0; j < values.getSize(); j++) {
                sudokuFields[i][j].setText(String.valueOf(values.get(j,i)));
            }
        }
    }

    private boolean solver(Sudoku puzzle) throws InvalidFieldException {
        SudokuSolver solver = new SudokuSolver();
        if(solver.solve(puzzle)) {
            return true;
        }
        return false;
    }

    private Sudoku decodeFile(File sourceFile) throws IOException {
        return importer.read(sourceFile.getPath());
    }


}