package UI;

import Models.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LandingPage {

    private BorderPane mainLayout;
    private StackPane contentArea;
    private User user;

    public LandingPage(User user) {
        this.user = user;
        initialize();
    }

    private void initialize() {
        mainLayout = new BorderPane();
        mainLayout.getStyleClass().add("main-root");
        mainLayout.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());

        // Header
        HBox header = createHeader();
        mainLayout.setTop(header);

        // Sidebar Navigation
        VBox sidebar = createSidebar();
        mainLayout.setLeft(sidebar);

        // Content Area
        contentArea = new StackPane();
        contentArea.setPadding(new Insets(30));
        
        // Show Dashboard by default
        showDashboard();

        mainLayout.setCenter(contentArea);
    }

    private HBox createHeader() {
        HBox header = new HBox(20);
        header.getStyleClass().add("header");
        header.setPadding(new Insets(15, 40, 15, 40));
        header.setAlignment(Pos.CENTER_LEFT);

        Label logo = new Label("NovelUp");
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        logo.setTextFill(Color.WHITE);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search for novels, authors...");
        searchBar.getStyleClass().add("search-field");
        searchBar.setPrefWidth(400);

        HBox userInfo = new HBox(12);
        userInfo.setAlignment(Pos.CENTER_RIGHT);
        
        Label userName = new Label(user != null ? user.getUsername() : "Guest");
        userName.getStyleClass().add("novel-title");
        
        ImageView avatar = new ImageView();
        avatar.setFitWidth(35);
        avatar.setFitHeight(35);
        Circle clip = new Circle(17.5, 17.5, 17.5);
        avatar.setClip(clip);
        
        if (user != null && user.getProfilePicUrl() != null && !user.getProfilePicUrl().isBlank()) {
            avatar.setImage(new Image(user.getProfilePicUrl(), 35, 35, true, true));
        } else {
            // Placeholder
            avatar.setImage(new Image("https://ui-avatars.com/api/?name=" + (user != null ? user.getUsername() : "G") + "&background=3b82f6&color=fff"));
        }

        userInfo.getChildren().addAll(userName, avatar);

        header.getChildren().addAll(logo, searchBar, spacer, userInfo);
        return header;
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox(8);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPadding(new Insets(30, 15, 30, 15));
        sidebar.setPrefWidth(240);

        Label menuLabel = new Label("MENU");
        menuLabel.setStyle("-fx-text-fill: #475569; -fx-font-size: 11px; -fx-font-weight: bold;");
        menuLabel.setPadding(new Insets(0, 0, 10, 15));

        sidebar.getChildren().addAll(
            menuLabel,
            createNavButton("🏠  Dashboard", e -> showDashboard()),
            createNavButton("📚  My Library", e -> showLibrary()),
            createNavButton("📜  Reading History", e -> showHistory()),
            createNavButton("🔔  Notifications", e -> showNotifications()),
            createNavButton("⚙️  Settings", e -> showSettings())
        );

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        sidebar.getChildren().add(spacer);

        Button logoutBtn = createNavButton("🚪  Logout", e -> handleLogout());
        logoutBtn.setStyle("-fx-text-fill: #ef4444;");
        sidebar.getChildren().add(logoutBtn);

        return sidebar;
    }

    private Button createNavButton(String text, javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        Button btn = new Button(text);
        btn.getStyleClass().add("nav-button");
        btn.setPrefWidth(Double.MAX_VALUE);
        btn.setOnAction(handler);
        return btn;
    }

    private void showDashboard() {
        // We'll create a DashboardPane soon
        contentArea.getChildren().setAll(new DashboardPane().getPane());
    }

    private void showLibrary() {
        contentArea.getChildren().setAll(new LibraryPane().getPane());
    }

    private void showHistory() {
        contentArea.getChildren().setAll(new HistoryScreenPane().getPane());
    }

    private void showNotifications() {
        contentArea.getChildren().setAll(new NotificationScreenPane().getPane());
    }

    private void showSettings() {
        contentArea.getChildren().setAll(new ProfileSettingsPane().getPane());
    }

    private void handleLogout() {
        utils.ScreenManager.showScreen("LoginScreen");
    }

    public Parent getRoot() {
        return mainLayout;
    }
}
