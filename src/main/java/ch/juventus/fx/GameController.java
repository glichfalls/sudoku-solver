package ch.juventus.fx;

import ch.juventus.importer.SudokuImporter;
import ch.juventus.puzzle.Sudoku;
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
        File file = showFileChooser();
        try {
            fillValues(getValues(file));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void solveGame() {
        System.out.println("Solve game pressed.");
    }


    private void fillValues(Sudoku values) {
        for (int i = 0; i < values.getSize(); i++) {
            for (int j = 0; j < values.getSize(); j++) {
                sudokuFields[i][j].setText(String.valueOf(values.get(j,i)));
            }
        }
    }

    private File showFileChooser() {
        Stage mainStage = new Stage();
        FileChooser openDialog = new FileChooser();

        openDialog.setTitle("Öffne Sudokudatei");
        openDialog.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Sudoku files", "*.json"),
                new FileChooser.ExtensionFilter("Sudoku text files", "*.txt")
        );

        File file = openDialog.showOpenDialog(mainStage);
        if (file != null) {
            return file;
        }

        return null;
    }

    private Sudoku getValues(File sourceFile) throws IOException {
        return importer.read(sourceFile.getPath());
    }
}
