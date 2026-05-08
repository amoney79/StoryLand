package UI;

import javafx.application.Application;
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

public class ProfileSettingsScreen extends Application {
    private final User currentUser;

    public ProfileSettingsScreen(User user) {
        this.currentUser = user;
    }

    public ProfileSettingsScreen() {
        this.currentUser = null;
    }

    private BorderPane mainLayout;
    private VBox sideBar;
    private StackPane contentPane;

    @Override
    public void start(Stage stage) {
        mainLayout = new BorderPane();

        // Top profile
        HBox profileBar = createProfileHeader();
        mainLayout.setTop(profileBar);

        // Left sidebar
        sideBar = createSideBar();
        mainLayout.setLeft(sideBar);

        // Right content pane
        contentPane = new StackPane();
        contentPane.setPadding(new Insets(20));
        mainLayout.setCenter(contentPane);

        Scene scene = new Scene(mainLayout, 900, 650);
        ThemeManager.applyTheme(scene);
        scene.getStylesheets().add(
                getClass().getResource("/styles/ios_switch.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.setTitle("Profile Settings");
        stage.show();
    }

    private HBox createProfileHeader() {
        HBox profileBar = new HBox(10);
        profileBar.setPadding(new Insets(15));
        profileBar.setAlignment(Pos.CENTER_LEFT);
        profileBar.setStyle("-fx-background-color: -fx-box-border;");

        if (currentUser != null) {
            ImageView profilePic = new ImageView(new Image("/images/default_profile.jpg"));
            profilePic.setFitWidth(60);
            profilePic.setFitHeight(60);

            Label username = new Label(currentUser.getUsername());
            username.setFont(Font.font(18));

            profileBar.getChildren().addAll(profilePic, username);
        } else {
            Button signIn = new Button("Sign In / Sign Up");
            signIn.setOnAction(e -> {
                try {
                    new LoginScreen().start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            profileBar.getChildren().add(signIn);
        }

        return profileBar;
    }

    private VBox createSideBar() {
        VBox menu = new VBox(10);
        menu.setPadding(new Insets(15));
        menu.setPrefWidth(220);
        menu.setStyle("-fx-background-color: #F0F0F0;");

        Button editProfile = new Button("✏ Edit Profile");
        editProfile.setMaxWidth(Double.MAX_VALUE);
        editProfile.setOnAction(e -> showContent(new ProfileEditScreen(currentUser)));

        Button history = new Button("📖 History");
        history.setMaxWidth(Double.MAX_VALUE);
        history.setOnAction(e -> showContent(new HistoryScreen(currentUser)));

        Button downloads = new Button("📥 Downloads");
        downloads.setMaxWidth(Double.MAX_VALUE);
        downloads.setOnAction(e -> showContent(new DownloadedNovelsScreen(currentUser)));

        Button preferences = new Button("📚 Preferences");
        preferences.setMaxWidth(Double.MAX_VALUE);
        preferences.setOnAction(e -> showContent(new ReadingPreferencesScreen(currentUser)));

        Button notifications = new Button("🔔 Notifications");
        notifications.setMaxWidth(Double.MAX_VALUE);
        notifications.setOnAction(e -> showContent(new NotificationScreen(currentUser)));

        Button about = new Button("📄 About");
        about.setMaxWidth(Double.MAX_VALUE);
        about.setOnAction(e -> showContent(new AboutScreen(currentUser)));

        Button help = new Button("❓ Help & Feedback");
        help.setMaxWidth(Double.MAX_VALUE);
        help.setOnAction(e -> showContent(new HelpFeedbackScreen(currentUser)));

        Button rateUs = new Button("⭐ Rate Us");
        rateUs.setMaxWidth(Double.MAX_VALUE);
        rateUs.setOnAction(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(
                        new java.net.URI("https://play.google.com/store")
                );
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Dark mode row
        HBox darkModeRow = new HBox(10);
        darkModeRow.setAlignment(Pos.CENTER_LEFT);

        Label darkLabel = new Label("🌙 Dark Mode");
        CheckBox darkToggle = new CheckBox();
        darkToggle.getStyleClass().add("switch");

        boolean isDark = "dark".equalsIgnoreCase(ThemeManager.getCurrentTheme());
        darkToggle.setSelected(isDark);

        darkToggle.setOnAction(e -> {
            ThemeManager.toggleDarkModeAndRefresh(mainLayout.getScene());
            darkToggle.setSelected(
                    "dark".equalsIgnoreCase(ThemeManager.getCurrentTheme())
            );
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
            Button adminPanel = new Button("⚙ Admin Panel");
            adminPanel.setMaxWidth(Double.MAX_VALUE);
            adminPanel.setOnAction(e -> showContent(new NovelAdminScreen(currentUser)));

            menu.getChildren().addAll(new Separator(), adminPanel);
        }

        return menu;
    }

    private void showContent(SceneAware screen) {
        Node content = screen.getContent();
        contentPane.getChildren().setAll(content);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public interface SceneAware {
        Node getContent();
    }
}