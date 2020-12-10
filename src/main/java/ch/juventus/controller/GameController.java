package ch.juventus.controller;

import ch.juventus.exceptions.UnsolvableException;
import ch.juventus.exceptions.UnsupportedFormatException;
import ch.juventus.importer.ImporterFactory;
import ch.juventus.puzzle.sudoku.Sudoku;
import ch.juventus.solver.Solver;
import ch.juventus.solver.SudokuSolver;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class GameController {

    private Logger logger;
    private final ImporterFactory importerFactory = new ImporterFactory();
    private final Solver<Sudoku> solver = new SudokuSolver();

    public GameController() {
        logger = LoggerFactory.getLogger(GameController.class);
    }

    public Sudoku getSelectedSudoku() throws UnsupportedFormatException {
        File file = getSudokuFile();
        if(file == null) {
            logger.warn("File selection was canceled.");
            return null;
        }
        return importerFactory.getSudokuImporterForFile(file).getPuzzleFromFile(file.getPath());
    }

    public void solveGame(Sudoku sudoku) throws UnsolvableException {
        if(sudoku == null) {
            logger.error("No Sudoku was loaded.");
            throw new UnsolvableException("No sudoku available to solve.");
        }
        solver.solve(sudoku);
    }

    private File getSudokuFile() {
        Stage mainStage = new Stage();
        FileChooser openDialog = new FileChooser();
        openDialog.setTitle("Please chose a sudoku file");
        openDialog.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Sudoku text files", "*.txt", "*.json")
        );
        return openDialog.showOpenDialog(mainStage);
    }

}