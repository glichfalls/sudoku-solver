package ch.juventus.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;

public class Game extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage mainStage) throws Exception {
        GameController controller = new GameController();
        Parent loader = MyFXMLLoader.loadFXML("MainScene","");

        Scene scene = new Scene(loader);

        //Stage stuff
        mainStage.setTitle("This is a Title...");
        mainStage.setWidth(600);
        mainStage.setHeight(400);
        mainStage.setScene(scene);

        mainStage.show();
    }
}
