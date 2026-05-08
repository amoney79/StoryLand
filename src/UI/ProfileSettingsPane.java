package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Models.User;
import utils.ThemeManager;

public class ProfileSettingsPane {
    private final User currentUser;

    public ProfileSettingsPane(User user) {
        this.currentUser = user;
    }

    public ProfileSettingsPane() {
        this.currentUser = null;
    }

    private BorderPane mainLayout;
    private VBox sideBar;
    private StackPane contentPane;

    public Region getPane() {
        mainLayout = new BorderPane();
        mainLayout.getStyleClass().add("profile-settings-root");

        // Top profile
        HBox profileBar = createProfileHeader();
        mainLayout.setTop(profileBar);

        // Left sidebar
        sideBar = createSideBar();
        mainLayout.setLeft(sideBar);

        // Right content pane
        contentArea = new StackPane();
        contentArea.setPadding(new Insets(20));
        mainLayout.setCenter(contentArea);

        // Default content
        showContent(new ProfileEditScreen(currentUser));

        return mainLayout;
    }

    private StackPane contentArea;

    private HBox createProfileHeader() {
        HBox profileBar = new HBox(10);
        profileBar.setPadding(new Insets(15));
        profileBar.setAlignment(Pos.CENTER_LEFT);
        profileBar.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 10;");

        if (currentUser != null) {
            ImageView profilePic = new ImageView();
            try {
                profilePic.setImage(new Image(currentUser.getProfilePicUrl() != null ? currentUser.getProfilePicUrl() : "https://ui-avatars.com/api/?name=" + currentUser.getUsername()));
            } catch (Exception e) {
                profilePic.setImage(new Image("https://ui-avatars.com/api/?name=User"));
            }
            profilePic.setFitWidth(50);
            profilePic.setFitHeight(50);

            Label username = new Label(currentUser.getUsername());
            username.setFont(Font.font("Arial", 18));
            username.setTextFill(javafx.scene.paint.Color.WHITE);

            profileBar.getChildren().addAll(profilePic, username);
        } else {
            Button signIn = new Button("Sign In / Sign Up");
            signIn.getStyleClass().add("primary-button");
            signIn.setOnAction(e -> {
                utils.ScreenManager.showScreen("LoginScreen");
            });
            profileBar.getChildren().add(signIn);
        }

        return profileBar;
    }

    private VBox createSideBar() {
        VBox menu = new VBox(10);
        menu.setPadding(new Insets(15));
        menu.setPrefWidth(220);
        menu.getStyleClass().add("sidebar");

        Button editProfile = createMenuButton("✏ Edit Profile", e -> showContent(new ProfileEditScreen(currentUser)));
        Button history = createMenuButton("📖 History", e -> showContent(new HistoryScreenPane(currentUser)));
        Button downloads = createMenuButton("📥 Downloads", e -> showContent(new DownloadedNovelsScreen(currentUser)));
        Button preferences = createMenuButton("📚 Preferences", e -> showContent(new ReadingPreferencesScreen(currentUser)));
        Button notifications = createMenuButton("🔔 Notifications", e -> showContent(new NotificationScreenPane(currentUser)));
        Button about = createMenuButton("📄 About", e -> showContent(new AboutScreen(currentUser)));
        Button help = createMenuButton("❓ Help & Feedback", e -> showContent(new HelpFeedbackScreen(currentUser)));

        Button rateUs = createMenuButton("⭐ Rate Us", e -> {
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI("https://play.google.com/store"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Dark mode row
        HBox darkModeRow = new HBox(10);
        darkModeRow.setAlignment(Pos.CENTER_LEFT);
        darkModeRow.setPadding(new Insets(10, 15, 10, 15));

        Label darkLabel = new Label("🌙 Dark Mode");
        darkLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        CheckBox darkToggle = new CheckBox();
        darkToggle.getStyleClass().add("switch");

        boolean isDark = ThemeManager.isDarkMode();
        darkToggle.setSelected(isDark);

        darkToggle.setOnAction(e -> {
            ThemeManager.toggleDarkModeAndRefresh(mainLayout.getScene());
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        darkModeRow.getChildren().addAll(darkLabel, spacer, darkToggle);

        menu.getChildren().addAll(
                editProfile, new Separator(),
                history,
                downloads, new Separator(),
                preferences,
                darkModeRow, new Separator(),
                notifications, new Separator(),
                about,
                help,
                rateUs
        );

        // Admin panel
        if (currentUser != null && "admin".equalsIgnoreCase(currentUser.getRole())) {
            Button adminPanel = createMenuButton("⚙ Admin Panel", e -> showContent(new NovelAdminScreen(currentUser)));
            menu.getChildren().addAll(new Separator(), adminPanel);
        }

        return menu;
    }

    private Button createMenuButton(String text, javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.getStyleClass().add("nav-button");
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setOnAction(handler);
        return btn;
    }

    public void showContent(SceneAware screen) {
        Node content = screen.getContent();
        contentArea.getChildren().setAll(content);
    }

    public interface SceneAware {
        Node getContent();
    }
}