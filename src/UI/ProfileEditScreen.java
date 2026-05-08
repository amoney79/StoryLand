package UI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Models.User;
import UI.ProfileSettingsScreen.SceneAware;

public class ProfileEditScreen extends Application implements SceneAware {
    private final User currentUser;

    public ProfileEditScreen(User user) {
        this.currentUser = user;
    }

    public ProfileEditScreen() {
        this.currentUser = null;
    }

    @Override
    public void start(Stage stage) {
        VBox root = buildContent();
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }

    @Override
    public Node getContent() {
        return buildContent();
    }

    private VBox buildContent() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label heading = new Label("✏️ Edit Profile");
        heading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField username = new TextField(currentUser != null ? currentUser.getUsername() : "");
        username.setPromptText("Username");

        TextField email = new TextField(currentUser != null ? currentUser.getEmail() : "");
        email.setPromptText("Email");

        Button save = new Button("💾 Save Changes");
        save.setOnAction(e -> {
            // Save logic (fake for now)
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Profile updated.");
            alert.showAndWait();
        });

        root.getChildren().addAll(heading, username, email, save);
        return root;
    }
}