package ch.juventus.fx;

import javafx.scene.control.Alert;

public class AlertModal {

    private static Alert create(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        if(!content.equals("")) {
            alert.setHeaderText(header);
            alert.setContentText(content);
        } else {
            alert.setHeaderText(header);
        }
        return alert;
    }

    public static Alert error(String title, String header, String content) {
        return AlertModal.create(Alert.AlertType.ERROR, title, header, content);
    }

    public static Alert ok(String title, String header, String content) {
        return AlertModal.create(Alert.AlertType.INFORMATION, title, header, content);
    }

    public static Alert warning(String title, String header, String content) {
        return AlertModal.create(Alert.AlertType.WARNING, title, header, content);
    }

}
