package ch.juventus.fx;

import ch.juventus.puzzle.Sudoku;
import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class MainView {

    private Stage stage = new Stage();
    private GameController controller;

    private Double sudokuRow;
    private Double sudokuCol;

    public MainView(Stage stage, Double col, Double row) {
        this.stage = stage;
        this.sudokuRow = row;
        this.sudokuCol = col;
        controller = new GameController();
    }


    public Scene getScene() {
        stage.resizableProperty().setValue(false);
        BorderPane rootPane = new BorderPane();

        rootPane.setStyle("root");

        rootPane.setTop(buttonPane());
        rootPane.setCenter(sudokuPane());

        Scene mainScene = new Scene(rootPane, 450, 550);
        mainScene.getStylesheets().add(this.getClass().getResource("/MainViewStyle.css").toExternalForm());
        return mainScene;
    }

    private HBox sudokuPane() {
        HBox rootPane = new HBox();
        StackPane stackPane = new StackPane();
        GridPane grid = new GridPane();

        rootPane.setAlignment(Pos.CENTER);

        for (int i = 0; i < sudokuRow -1; i++) {
            for (int j = 0; j < sudokuCol -1; j++) {
                TextField textField = controller.createTextField(i,j);
                grid.add(textField, i, j);
            }
        }

        grid.getStyleClass().add("sudokuPane");
        stackPane.getChildren().add(grid);
        rootPane.getChildren().add(stackPane);

        return rootPane;
    }

    private HBox buttonPane() {
        HBox buttonPane = new HBox();
        buttonPane.setAlignment(Pos.CENTER_LEFT);
        buttonPane.getStyleClass().add("buttonPane");

        Button LoadJSONButton = new Button("Load Sudoku");
        LoadJSONButton.setOnAction(event -> controller.loadJSON());
        LoadJSONButton.getStyleClass().add("buttons");

        Button SolveButton = new Button("Solve");
        SolveButton.setOnAction(event -> controller.solveGame());
        SolveButton.getStyleClass().add("buttons");

        buttonPane.getChildren().add(LoadJSONButton);
        buttonPane.getChildren().add(SolveButton);
        return buttonPane;
    }
}
