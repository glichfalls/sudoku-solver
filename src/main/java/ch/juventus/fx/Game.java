package ch.juventus.fx;

import ch.juventus.exceptions.InvalidFieldException;
import ch.juventus.importer.SudokuImport;
import ch.juventus.puzzle.Sudoku;
import ch.juventus.solver.SudokuSolver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Game extends Application {

    public void run() {
        SudokuImport importer = new SudokuImport();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage.setTitle("This is a Title...");
        mainStage.setWidth(600);
        mainStage.setHeight(400);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
