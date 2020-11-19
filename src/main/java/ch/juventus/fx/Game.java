package ch.juventus.fx;

import ch.juventus.exceptions.InvalidFieldException;
import ch.juventus.importer.SudokuImporter;
import ch.juventus.puzzle.Sudoku;
import ch.juventus.solver.SudokuSolver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Game extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        mainStage.setTitle("Sudoku solver");
        mainStage.setScene(new MainView(mainStage).getScene());
        mainStage.show();
    }

}