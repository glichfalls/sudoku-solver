package ch.juventus.fx;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

class ButtonPane extends HBox {

    ButtonPane() {
        super();
        setAlignment(Pos.CENTER_LEFT);
        getStyleClass().add("buttonPane");
    }

    void addButton(Button button) {
        button.getStyleClass().add("buttons");
        getChildren().add(button);
    }

}
