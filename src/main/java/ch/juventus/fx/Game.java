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

    public void run() {
        SudokuImporter importer = new SudokuImporter();
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
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        mainStage.setTitle("Sudoku solver");
        mainStage.setScene(new MainView(mainStage, 10d, 10d).getScene());
        mainStage.show();
    }

}