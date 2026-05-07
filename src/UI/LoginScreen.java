package UI;

import Models.User;
import db.UserDAO;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import utils.ScreenManager;

import java.util.ArrayList;
import java.util.List;

public class LoginScreen {

    private final StackPane root;
    private final VBox card;
    private final ProgressIndicator loadingSpinner;
    private final Label capsLockLabel;
    private final Label message;
    private final ImageView profilePic;
    private final Label profileName;

    private int focusIndex = 0;
    private final List<Control> focusables = new ArrayList<>();

    public LoginScreen() {
        root = new StackPane();
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("login-root");
        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        card = new VBox(14);
        card.setPadding(new Insets(28));
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().add("login-card");
        card.setMaxSize(450, 700);

        Label title = new Label("Login to NovelUp");
        title.getStyleClass().add("title-label");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Email or Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        TextField visiblePasswordField = new TextField();
        visiblePasswordField.setPromptText("Password");
        visiblePasswordField.setManaged(false);
        visiblePasswordField.setVisible(false);

        visiblePasswordField.textProperty().bindBidirectional(passwordField.textProperty());

        Button togglePasswordBtn = new Button("👁");
        togglePasswordBtn.getStyleClass().add("secondary-button");

        StackPane passwordStack = new StackPane(passwordField, visiblePasswordField);
        HBox.setHgrow(passwordStack, Priority.ALWAYS);

        HBox passwordBox = new HBox(6, passwordStack, togglePasswordBtn);
        passwordBox.setAlignment(Pos.CENTER);

        togglePasswordBtn.setOnAction(e -> {
            boolean showing = visiblePasswordField.isVisible();
            visiblePasswordField.setVisible(!showing);
            visiblePasswordField.setManaged(!showing);
            passwordField.setVisible(showing);
            passwordField.setManaged(showing);

            if (!showing) {
                visiblePasswordField.requestFocus();
                visiblePasswordField.positionCaret(visiblePasswordField.getText().length());
            } else {
                passwordField.requestFocus();
                passwordField.positionCaret(passwordField.getText().length());
            }
        });

        capsLockLabel = new Label("Caps Lock is ON");
        capsLockLabel.setStyle("-fx-text-fill: #ef4444; -fx-font-size: 12px;");
        capsLockLabel.setVisible(false);

        passwordField.setOnKeyReleased(this::checkCapsLock);
        visiblePasswordField.setOnKeyReleased(this::checkCapsLock);

        Button loginBtn = new Button("Login");
        loginBtn.getStyleClass().add("primary-button");
        loginBtn.setMaxWidth(Double.MAX_VALUE);

        Button registerBtn = new Button("Go to Register");
        registerBtn.getStyleClass().add("secondary-button");

        Button googleBtn = new Button("Continue with Google");
        googleBtn.getStyleClass().addAll("social-button", "google-btn");
        googleBtn.setMaxWidth(Double.MAX_VALUE);
        
        Button appleBtn = new Button("Continue with Apple ID");
        appleBtn.getStyleClass().addAll("social-button", "apple-btn");
        appleBtn.setMaxWidth(Double.MAX_VALUE);

        profilePic = new ImageView();
        profilePic.setFitWidth(56);
        profilePic.setFitHeight(56);
        profilePic.setVisible(false);

        profileName = new Label();
        profileName.setVisible(false);

        VBox profileBox = new VBox(4, profilePic, profileName);
        profileBox.setAlignment(Pos.CENTER);

        loadingSpinner = new ProgressIndicator();
        loadingSpinner.setPrefSize(36, 36);
        loadingSpinner.setVisible(false);

        message = new Label();
        message.setStyle("-fx-text-fill: #ef4444;");

        VBox socialBox = new VBox(8, googleBtn, appleBtn);
        socialBox.setAlignment(Pos.CENTER);

        card.getChildren().addAll(
                title,
                usernameField,
                passwordBox,
                capsLockLabel,
                loginBtn,
                registerBtn,
                loadingSpinner,
                new Separator(),
                socialBox,
                profileBox,
                message
        );
        root.getChildren().add(card);

        focusables.add(usernameField);
        focusables.add(passwordField);
        focusables.add(loginBtn);
        focusables.add(registerBtn);
        focusables.add(googleBtn);
        focusables.add(appleBtn);

        loginBtn.setOnAction(e -> {
            loadingSpinner.setVisible(true);
            message.setText("");

            PauseTransition pause = new PauseTransition(Duration.seconds(1.3));
            pause.setOnFinished(ev -> {
                loadingSpinner.setVisible(false);
                String username = usernameField.getText().trim();
                String password = passwordField.getText();

                User user = UserDAO.authenticate(username, password);
                if (user != null) {
                    message.setText("Login successful! Welcome " + user.getUsername());
                    message.setStyle("-fx-text-fill: #10b981;");
                    playBounceAnimation(loginBtn);
                    ScreenManager.addScreen("LandingPage", new LandingPage(user).getRoot());
                    ScreenManager.showScreen("LandingPage");
                } else {
                    message.setText("Invalid credentials!");
                    message.setStyle("-fx-text-fill: #ef4444;");
                    playShakeAnimation(card);
                }
            });
            pause.play();
        });

        registerBtn.setOnAction(e -> ScreenManager.showScreen("RegisterScreen"));

        googleBtn.setOnAction(e -> {
            User user = new User("John Doe", "johndoe@gmail.com", "https://i.pravatar.cc/150?img=3");
            setSocialProfile(user);
            playBounceAnimation(googleBtn);
            ScreenManager.addScreen("LandingPage", new LandingPage(user).getRoot());
            ScreenManager.showScreen("LandingPage");
        });

        appleBtn.setOnAction(e -> {
            User user = new User("Alice Smith", "alice@icloud.com", "https://i.pravatar.cc/150?img=5");
            setSocialProfile(user);
            playBounceAnimation(appleBtn);
            ScreenManager.addScreen("LandingPage", new LandingPage(user).getRoot());
            ScreenManager.showScreen("LandingPage");
        });

        root.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.DOWN) {
                focusNext();
                event.consume();
            } else if (event.getCode() == KeyCode.UP) {
                focusPrevious();
                event.consume();
            } else if (event.getCode() == KeyCode.ENTER) {
                Node focused = root.getScene() == null ? null : root.getScene().getFocusOwner();
                if (focused instanceof Button button) {
                    button.fire();
                    event.consume();
                }
            }
        });

        animateElements();
    }

    public Parent getRoot() {
        return root;
    }

    private void setSocialProfile(User user) {
        profileName.setText(user.getUsername());
        profileName.setVisible(true);
        try {
            if (user.getProfilePicUrl() != null && !user.getProfilePicUrl().isBlank()) {
                profilePic.setImage(new Image(user.getProfilePicUrl(), 56, 56, true, true));
                profilePic.setVisible(true);
            }
        } catch (Exception ignored) {}
    }

    private void checkCapsLock(KeyEvent event) {
        String text = event.getText();
        if (text == null || text.isEmpty()) return;
        char c = text.charAt(0);
        if (!Character.isLetter(c)) return;
        boolean capsOn = Character.isUpperCase(c) && !event.isShiftDown();
        capsLockLabel.setVisible(capsOn);
    }

    private void focusNext() {
        if (focusables.isEmpty()) return;
        focusIndex = (focusIndex + 1) % focusables.size();
        focusables.get(focusIndex).requestFocus();
    }

    private void focusPrevious() {
        if (focusables.isEmpty()) return;
        focusIndex = (focusIndex - 1 + focusables.size()) % focusables.size();
        focusables.get(focusIndex).requestFocus();
    }

    private void animateElements() {
        double delay = 0.08;
        for (int i = 0; i < card.getChildren().size(); i++) {
            Node node = card.getChildren().get(i);
            FadeTransition fade = new FadeTransition(Duration.seconds(0.45), node);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.setDelay(Duration.seconds(i * delay));
            TranslateTransition slide = new TranslateTransition(Duration.seconds(0.45), node);
            slide.setFromY(20);
            slide.setToY(0);
            slide.setDelay(Duration.seconds(i * delay));
            new ParallelTransition(fade, slide).play();
        }
    }

    private void playShakeAnimation(Node node) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(45), node);
        tt.setByX(8);
        tt.setCycleCount(6);
        tt.setAutoReverse(true);
        tt.play();
    }

    private void playBounceAnimation(Node node) {
        ScaleTransition st = new ScaleTransition(Duration.millis(180), node);
        st.setToX(1.06);
        st.setToY(1.06);
        st.setCycleCount(2);
        st.setAutoReverse(true);
        st.play();
    }
}
