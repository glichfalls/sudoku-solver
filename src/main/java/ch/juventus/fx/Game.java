package ch.juventus.fx;

import javafx.application.Application;
import javafx.stage.Stage;

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