package UI;

import Models.User;
import db.UserDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utils.ScreenManager;

import java.util.ArrayList;
import java.util.List;

public class LoginScreen {
    private final StackPane root;
    private final List<Control> focusableElements = new ArrayList<>();
    private int currentFocusIndex = 0;

    public LoginScreen() {
        root = new StackPane();
        root.getStyleClass().add("root-pane");
        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        VBox card = new VBox(25);
        card.getStyleClass().add("login-card");
        card.setMaxSize(450, 650);
        card.setAlignment(Pos.CENTER);

        // Title and Subtitle
        VBox header = new VBox(8);
        header.setAlignment(Pos.CENTER);
        Label title = new Label("NovelUp");
        title.getStyleClass().add("title-label");
        Label subtitle = new Label("Welcome back! Please login to your account.");
        subtitle.getStyleClass().add("subtitle-label");
        header.getChildren().addAll(title, subtitle);

        // Input Fields
        VBox inputs = new VBox(15);
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username or Email");
        usernameField.setPrefHeight(45);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setPrefHeight(45);
        
        inputs.getChildren().addAll(usernameField, passwordField);

        // Login Button
        Button loginBtn = new Button("Sign In");
        loginBtn.getStyleClass().add("primary-button");
        loginBtn.setMaxWidth(Double.MAX_VALUE);

        // Social Logins
        VBox socialSection = new VBox(12);
        socialSection.setAlignment(Pos.CENTER);
        
        Label orLabel = new Label("OR CONTINUE WITH");
        orLabel.getStyleClass().add("subtitle-label");
        orLabel.setStyle("-fx-font-size: 11px;");

        Button googleBtn = new Button("Continue with Google");
        googleBtn.getStyleClass().addAll("social-button", "google-btn");
        
        Button appleBtn = new Button("Continue with Apple");
        appleBtn.getStyleClass().addAll("social-button", "apple-btn");

        socialSection.getChildren().addAll(orLabel, googleBtn, appleBtn);

        // Footer
        HBox footer = new HBox(5);
        footer.setAlignment(Pos.CENTER);
        Label noAccount = new Label("Don't have an account?");
        noAccount.getStyleClass().add("subtitle-label");
        Button registerBtn = new Button("Register");
        registerBtn.getStyleClass().add("secondary-button");
        footer.getChildren().addAll(noAccount, registerBtn);

        Label message = new Label();
        message.getStyleClass().add("message-label");

        card.getChildren().addAll(header, inputs, loginBtn, socialSection, footer, message);
        root.getChildren().add(card);

        // Add to focusable list for keyboard navigation
        focusableElements.add(usernameField);
        focusableElements.add(passwordField);
        focusableElements.add(loginBtn);
        focusableElements.add(googleBtn);
        focusableElements.add(appleBtn);
        focusableElements.add(registerBtn);

        // ✅ Handle Login
        loginBtn.setOnAction(e -> handleLogin(usernameField.getText(), passwordField.getText(), message));

        // ✅ Handle Social Logins (Mock)
        googleBtn.setOnAction(e -> handleSocialLogin("GoogleUser", "https://api.dicebear.com/7.x/avataaars/png?seed=Google", message));
        appleBtn.setOnAction(e -> handleSocialLogin("AppleUser", "https://api.dicebear.com/7.x/avataaars/png?seed=Apple", message));

        // ✅ Go to register screen
        registerBtn.setOnAction(e -> ScreenManager.showScreen("RegisterScreen"));

        // ✅ Keyboard Navigation (Arrow keys + Enter)
        root.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.RIGHT) {
                navigateFocus(1);
                event.consume();
            } else if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.LEFT) {
                navigateFocus(-1);
                event.consume();
            } else if (event.getCode() == KeyCode.ENTER) {
                Control focused = focusableElements.get(currentFocusIndex);
                if (focused instanceof Button) {
                    ((Button) focused).fire();
                } else {
                    handleLogin(usernameField.getText(), passwordField.getText(), message);
                }
                event.consume();
            }
        });
    }

    private void navigateFocus(int direction) {
        currentFocusIndex = (currentFocusIndex + direction + focusableElements.size()) % focusableElements.size();
        focusableElements.get(currentFocusIndex).requestFocus();
    }

    private void handleLogin(String username, String password, Label message) {
        if (username.isEmpty() || password.isEmpty()) {
            message.setText("Please fill in all fields.");
            return;
        }

        User user = UserDAO.authenticate(username, password);
        if (user != null) {
            proceedToHome(user);
        } else {
            message.setText("Invalid credentials!");
        }
    }

    private void handleSocialLogin(String mockName, String mockAvatar, Label message) {
        // Mocking social login success
        User user = new User(0, mockName, mockName.toLowerCase() + "@social.com", "");
        user.setProfilePicture(mockAvatar);
        proceedToHome(user);
    }

    private void proceedToHome(User user) {
        ScreenManager.addScreen("LandingPage", new LandingPage(user).getRoot());
        ScreenManager.showScreen("LandingPage");
    }

    public Parent getRoot() {
        return root;
    }
}
