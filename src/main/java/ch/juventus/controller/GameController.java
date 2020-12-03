package ch.juventus.controller;

import ch.juventus.exceptions.UnsolvableException;
import ch.juventus.importer.PuzzleImporter;
import ch.juventus.importer.SudokuImporter;
import ch.juventus.puzzle.Sudoku;
import ch.juventus.solver.SolverInterface;
import ch.juventus.solver.SudokuSolver;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class GameController {

    private Logger logger;
    private Sudoku sudoku;
    private final PuzzleImporter<Sudoku> importer = new SudokuImporter();
    private final SolverInterface<Sudoku> solver = new SudokuSolver();
    private TextField[][] sudokuFields = new TextField[9][9];

    public GameController() {
        logger = LoggerFactory.getLogger(GameController.class);
    }

    public TextField createTextField(int row, int col) {
        TextField textField = new TextField();
        sudokuFields[row][col] = textField;
        textField.getStyleClass().add("textFields");
        return textField;
    }

    public TextField createTextField(int row, int col, int value) {
        TextField textField = createTextField(row, col);
        textField.setText(value != 0 ? String.valueOf(value) : "");
        return textField;
    }

    public Sudoku loadFile() throws IOException {
        File file = getSudokuFile();
        sudoku = importer.read(Objects.requireNonNull(file).getPath());
        sudokuFields = new TextField[sudoku.getSize()][sudoku.getSize()];
        return sudoku;
    }

    public void solveGame() {
        try {
            if(sudoku == null) {
                logger.error("No Sudoku was loaded.");
                return;
            }
            solver.solve(sudoku);
            fillValues();
        } catch (UnsolvableException e) {
            logger.error("Failed to solve sudoku: " + e.getMessage());
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
        sudoku.print();
        for (int i = 0; i < sudoku.getSize(); i++) {
            for (int j = 0; j < sudoku.getSize(); j++) {
                System.out.println(i + ", " + j);
                int field = sudoku.get(i, j);
                System.out.println(field);
                System.out.println(sudokuFields[i][j]);
                sudokuFields[i][j].setText(field != 0 ? String.valueOf(field) : "");
            }
        }
    }
}