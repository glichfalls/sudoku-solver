package ch.juventus.fx;

import ch.juventus.controller.GameController;
import ch.juventus.exceptions.ImportException;
import ch.juventus.exceptions.UnsolvableException;
import ch.juventus.exceptions.UnsupportedFormatException;
import ch.juventus.puzzle.sudoku.Sudoku;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

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
        buttons.addButton(getResetButton());
        buttons.addButton(getRandomSudokuButton());
        Scene scene = new Scene(root, 450, 550);
        scene.getStylesheets().add(this.getClass().getResource("/MainViewStyle.css").toExternalForm());
        return scene;
    }

    private Button getLoadButton() {
        Button load = new Button("Load Sudoku");
        load.setOnAction(event -> {
            try {
                Sudoku loadedSudoku = controller.getSelectedSudoku();
                if(loadedSudoku != null) {
                    sudoku.load(loadedSudoku);
                }
            } catch (UnsupportedFormatException | ImportException e) {
                AlertModal.error(
                    "Fehler beim Laden",
                    "Die Sudoku Datei konnte nicht geladen werden.",
                    e.getMessage()
                ).showAndWait();
            }
        });
        return load;
    }

    private Button getSolveButton() {
        Button solve = new Button("Solve");
        solve.setOnAction(event -> {
            try {
                controller.solveGame(sudoku.getSudoku());
                sudoku.update();
            } catch (UnsolvableException e) {
                AlertModal.error(
                    "Fehler beim lösen",
                    "Das Sudoku konnte nicht gelöst werden",
                    e.getMessage()
                ).showAndWait();
            }
        });
        return solve;
    }

    private Button getResetButton() {
        Button reset = new Button("Reset");
        reset.setOnAction(event -> sudoku.reset());
        return reset;
    }

    private Button getRandomSudokuButton() {
        Button random = new Button("Random Sudoku");
        random.setOnAction(event -> {
            try {
                sudoku.load(controller.getRandomSudoku());
            } catch (ImportException e) {
                AlertModal.error(
                    "Fehler beim Laden",
                    "Es konnte kein Sudoku geladen werden.",
                    e.getMessage()
                ).showAndWait();
            }
        });
        return random;
    }

}
