package UI;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.UIManager; // ✅ correct import

public class Toast {
    public static void show(String msg) {
        Stage primaryStage = UIManager.getPrimaryStage(); // ✅ your app's primary stage
        if (primaryStage == null) {
            System.err.println("Error: Primary stage is not set in UIManager.");
            return;
        }

        Popup popup = new Popup();
        Label label = new Label(msg);
        label.setStyle("-fx-background-color: #323232; -fx-text-fill: white; -fx-padding: 10; -fx-background-radius: 10;");
        popup.getContent().add(label);
        popup.setAutoFix(true);
        popup.setAutoHide(true);
        popup.show(primaryStage);

        FadeTransition fade = new FadeTransition(Duration.seconds(3), label);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setOnFinished(e -> popup.hide());
        fade.play();
    }
}