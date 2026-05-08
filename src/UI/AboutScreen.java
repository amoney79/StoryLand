package UI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Models.User;
import UI.ProfileSettingsPane.SceneAware;

public class AboutScreen extends Application implements SceneAware {
    private final User user;

    public AboutScreen(User user) {
        this.user = user;
    }

    public AboutScreen() {
        this.user = null;
    }

    @Override
    public void start(Stage stage) {
        VBox root = buildContent();
        stage.setScene(new Scene(root, 400, 250));
        stage.show();
    }

    @Override
    public Node getContent() {
        return buildContent();
    }

    private VBox buildContent() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(20));

        Label title = new Label("📄 About This App");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label version = new Label("Version: 1.0.0");
        Button terms = new Button("📜 Terms of Service");
        Button privacy = new Button("🔒 Privacy Policy");

        box.getChildren().addAll(title, version, terms, privacy);
        return box;
    }
}