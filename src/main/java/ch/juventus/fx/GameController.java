package ch.juventus.fx;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameController {
    public Label label1;
    public Button button1;

    @FXML
    public void buttonClicked(Event e) {
        label1.setText("Even tho you clicked the Bötttten, it still doesnt look great!");
    }
}
