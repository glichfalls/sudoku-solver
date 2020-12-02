package ch.juventus.fx;

import ch.juventus.controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainView {

    private Stage stage;
    private GameController controller;

    MainView(Stage stage) {
        this.stage = stage;
        controller = new GameController();
    }

    Scene getScene() {
        stage.resizableProperty().setValue(false);
        BorderPane rootPane = new BorderPane();

        rootPane.getStyleClass().add("root");

        rootPane.setTop(buttonPane());
        rootPane.setCenter(sudokuPane());

        Scene mainScene = new Scene(rootPane, 450, 550);
        mainScene.getStylesheets().add(this.getClass().getResource("/MainViewStyle.css").toExternalForm());
        return mainScene;
    }

    private HBox buttonPane() {
        HBox buttonPane = new HBox();

        buttonPane.setAlignment(Pos.CENTER_LEFT);
        buttonPane.getStyleClass().add("buttonPane");

        Button LoadJSONButton = new Button("Load Sudoku");
        LoadJSONButton.setOnAction(event -> controller.loadFile());
        LoadJSONButton.getStyleClass().add("buttons");

        Button SolveButton = new Button("Solve");
        SolveButton.setOnAction(event -> controller.solveGame());
        SolveButton.getStyleClass().add("buttons");

        buttonPane.getChildren().add(LoadJSONButton);
        buttonPane.getChildren().add(SolveButton);
        return buttonPane;
    }
    private HBox sudokuPane() {
        HBox rootPane = new HBox();
        StackPane stackPane = new StackPane();
        GridPane grid = new GridPane();

        rootPane.setAlignment(Pos.CENTER);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = controller.createTextField(i,j);
                grid.add(textField, i, j);
            }
        }

        grid.getStyleClass().add("sudokuPane");
        stackPane.getChildren().add(grid);
        rootPane.getChildren().add(stackPane);

        return rootPane;
    }

}
