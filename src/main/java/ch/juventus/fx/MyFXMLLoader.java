package ch.juventus.fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class MyFXMLLoader {
    public static Parent loadFXML(String fxmlFile, String resourceFolder) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MyFXMLLoader.class.getResource(String.format("/%s/%s.fxml", resourceFolder, fxmlFile)));
        return fxmlLoader.load();
    }
}
