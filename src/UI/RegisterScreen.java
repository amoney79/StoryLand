package UI;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import utils.ScreenManager;

public class RegisterScreen {
    private final StackPane root;

    public RegisterScreen() {
        root = new StackPane();
        root.getStyleClass().add("root-pane");
        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        VBox card = new VBox(25);
        card.getStyleClass().add("login-card");
        card.setMaxSize(450, 600);
        card.setAlignment(Pos.CENTER);

        Label title = new Label("Join NovelUp");
        title.getStyleClass().add("title-label");

        TextField userField = new TextField();
        userField.setPromptText("Username");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        PasswordField passField = new PasswordField();
        passField.setPromptText("Password");

        Button regBtn = new Button("Create Account");
        regBtn.getStyleClass().add("primary-button");
        regBtn.setMaxWidth(Double.MAX_VALUE);

        Button backBtn = new Button("Back to Login");
        backBtn.getStyleClass().add("secondary-button");
        backBtn.setOnAction(e -> ScreenManager.showScreen("LoginScreen"));

        card.getChildren().addAll(title, userField, emailField, passField, regBtn, backBtn);
        root.getChildren().add(card);
    }

    public Parent getRoot() {
        return root;
    }
}
