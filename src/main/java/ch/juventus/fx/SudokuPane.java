package ch.juventus.fx;

import ch.juventus.puzzle.Sudoku;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

class SudokuPane extends HBox {

    private Sudoku sudoku;
    private GridPane grid = new GridPane();

    SudokuPane() {
        super();
        StackPane stack = new StackPane();
        setAlignment(Pos.CENTER);
        load(new Sudoku());
        grid.getStyleClass().add("sudokuPane");
        stack.getChildren().add(grid);
        getChildren().add(stack);
    }

    void load(Sudoku sudoku) {
        this.sudoku = sudoku;
        for(int x = 0; x < sudoku.getSize(); x++) {
            for(int y = 0; y < sudoku.getSize(); y++) {
                set(x, y, sudoku.get(x, y));
            }
        }
    }

    Sudoku getSudoku() {
        return sudoku;
    }

    private void set(int x, int y, int number) {
        TextField field = new TextField();
        field.setText(number != 0 ? String.valueOf(number) : "");
        grid.add(field, x, y);
    }

}
