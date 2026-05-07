package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginPage {

    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #f4f7f6;"); // Light grey background

        // Title
        Label titleLabel = new Label("StoryLand");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 36));
        titleLabel.setTextFill(Color.web("#2c3e50"));

        // Subtitle
        Label subTitle = new Label("Welcome back, Explorer!");
        subTitle.setFont(Font.font("System", FontWeight.NORMAL, 16));
        subTitle.setTextFill(Color.web("#7f8c8d"));

        // Login Box
        VBox loginBox = new VBox(15);
        loginBox.setPadding(new Insets(30));
        loginBox.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);");
        loginBox.setMaxWidth(400);

        TextField emailField = new TextField();
        emailField.setPromptText("Email Address");
        emailField.setPrefHeight(40);
        emailField.setStyle("-fx-background-radius: 5;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setPrefHeight(40);
        passwordField.setStyle("-fx-background-radius: 5;");

        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(Double.MAX_VALUE);
        loginButton.setPrefHeight(40);
        loginButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5; -fx-cursor: hand;");
        
        loginButton.setOnAction(e -> {
            // Logic for login (Mock user for now to fix compilation)
            Models.User mockUser = new Models.User(1, "Guest", "guest@example.com", "");
            LandingPage landingPage = new LandingPage(mockUser);
            primaryStage.getScene().setRoot(landingPage.getRoot());
        });

        Hyperlink forgotPassword = new Hyperlink("Forgot Password?");
        forgotPassword.setTextFill(Color.web("#3498db"));

        loginBox.getChildren().addAll(emailField, passwordField, loginButton, forgotPassword);

        // Sign Up Link
        HBox signUpBox = new HBox(5);
        signUpBox.setAlignment(Pos.CENTER);
        Label noAccount = new Label("Don't have an account?");
        Hyperlink signUpLink = new Hyperlink("Sign Up");
        signUpLink.setTextFill(Color.web("#3498db"));
        signUpBox.getChildren().addAll(noAccount, signUpLink);

        root.getChildren().addAll(titleLabel, subTitle, loginBox, signUpBox);

        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("StoryLand - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
