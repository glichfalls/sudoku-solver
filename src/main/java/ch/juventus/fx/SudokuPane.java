package ch.juventus.fx;

import ch.juventus.puzzle.sudoku.Sudoku;
import ch.juventus.puzzle.sudoku.SudokuValue;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

class SudokuPane extends HBox {

    private Sudoku sudoku;
    private final GridPane grid = new GridPane();

    SudokuPane() {
        StackPane stack = new StackPane();
        setAlignment(Pos.CENTER);
        load(new Sudoku());
        grid.getStyleClass().add("sudokuPane");
        stack.getChildren().add(grid);
        getChildren().add(stack);
    }

    Sudoku getSudoku() {
        return sudoku;
    }

    void load(Sudoku sudoku) {
        this.sudoku = sudoku;
        update();
    }

    void reset() {
        sudoku.clear();
        update();
    }

    void update() {
        grid.getChildren().clear();
        for(int x = 0; x < sudoku.getSize(); x++) {
            for(int y = 0; y < sudoku.getSize(); y++) {
                set(sudoku.get(x, y));
            }
        }
    }

    private void set(SudokuValue value) {
        TextField field = new TextField();
        field.setText(value.toString());
        field.setOnKeyReleased(event -> sudoku.set(new SudokuValue(value.getX(), value.getY(), field.getText())));
        field.getStyleClass().add("textFields");
        grid.add(field, value.getX(), value.getY());
    }

}
