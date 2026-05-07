package utils;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.HashMap;

public class ScreenManager {
    private static Stage primaryStage;
    private static final HashMap<String, Parent> screens = new HashMap<>();
    private static StackPane rootPane = new StackPane();

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
        Scene scene = new Scene(rootPane, 1100, 800);
        primaryStage.setScene(scene);
    }

    public static void addScreen(String name, Parent screen) {
        screens.put(name, screen);
    }

    public static void showScreen(String name) {
        Parent screen = screens.get(name);
        if (screen != null) {
            rootPane.getChildren().setAll(screen);
        }
    }
}
