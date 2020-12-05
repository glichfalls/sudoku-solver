package ch.juventus.controller;

import ch.juventus.exceptions.UnsolvableException;
import ch.juventus.importer.PuzzleImporter;
import ch.juventus.importer.SudokuTextImporter;
import ch.juventus.puzzle.Sudoku;
import ch.juventus.solver.Solver;
import ch.juventus.solver.SudokuSolver;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class GameController {

    private Logger logger;
    private final PuzzleImporter<Sudoku> importer = new SudokuTextImporter();
    private final Solver<Sudoku> solver = new SudokuSolver();

    public GameController() {
        logger = LoggerFactory.getLogger(GameController.class);
    }

    public Sudoku loadFile() throws Exception {
        File file = getSudokuFile();
        if(file == null) {
            throw new UnsupportedOperationException("Failed to select file.");
        }
        return importer.getPuzzleFromFile(file.getPath());
    }

    public void solveGame(Sudoku sudoku) {
        try {
            if(sudoku == null) {
                logger.error("No Sudoku was loaded.");
                return;
            }
            solver.solve(sudoku);
        } catch (UnsolvableException e) {
            sudoku.print();
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

}