package ch.juventus.fx;

import ch.juventus.controller.GameController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLOutput;

class MainView {

    private Stage stage;
    private GameController controller;
    private BorderPane root = new BorderPane();
    private ButtonPane buttons = new ButtonPane();
    private SudokuPane sudoku = new SudokuPane();

    MainView(Stage stage) {
        this.stage = stage;
        controller = new GameController();
    }

    Scene getMainScene() {
        stage.resizableProperty().setValue(false);
        root.getStyleClass().add("root");
        root.setCenter(sudoku);
        root.setTop(buttons);
        buttons.addButton(getLoadButton());
        buttons.addButton(getSolveButton());
        Scene scene = new Scene(root, 450, 550);
        scene.getStylesheets().add(this.getClass().getResource("/MainViewStyle.css").toExternalForm());
        return scene;
    }

    private Button getLoadButton() {
        Button load = new Button("Load Sudoku");
        load.setOnAction(event -> {
            try {
                sudoku.load(controller.loadFile());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        return load;
    }

    private Button getSolveButton() {
        Button solve = new Button("Solve");
        solve.setOnAction(event -> {
            controller.solveGame(sudoku.getSudoku());
        });
        return solve;
    }

}
