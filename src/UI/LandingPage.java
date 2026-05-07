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

public class LandingPage {

    private BorderPane mainLayout;
    private StackPane contentArea;

    public void start(Stage primaryStage) {
        mainLayout = new BorderPane();
        mainLayout.setStyle("-fx-background-color: #f8f9fa;");

        // Header
        HBox header = createHeader();
        mainLayout.setTop(header);

        // Sidebar Navigation
        VBox sidebar = createSidebar();
        mainLayout.setLeft(sidebar);

        // Content Area
        contentArea = new StackPane();
        contentArea.setPadding(new Insets(20));
        
        // Default content
        showLibrary();

        mainLayout.setCenter(contentArea);

        Scene scene = new Scene(mainLayout, 1100, 800);
        primaryStage.setTitle("StoryLand - Home");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createHeader() {
        HBox header = new HBox();
        header.setPadding(new Insets(15, 30, 15, 30));
        header.setStyle("-fx-background-color: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 5, 0, 0, 2);");
        header.setAlignment(Pos.CENTER_LEFT);

        Label logo = new Label("StoryLand");
        logo.setFont(Font.font("System", FontWeight.BOLD, 22));
        logo.setTextFill(Color.web("#2c3e50"));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search for novels...");
        searchBar.setPrefWidth(300);
        searchBar.setStyle("-fx-background-radius: 20; -fx-background-color: #f1f3f4;");

        header.getChildren().addAll(logo, spacer, searchBar);
        return header;
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20, 10, 20, 10));
        sidebar.setPrefWidth(220);
        sidebar.setStyle("-fx-background-color: white; -fx-border-color: #eee; -fx-border-width: 0 1 0 0;");

        sidebar.getChildren().addAll(
            createNavButton("Library", e -> showLibrary()),
            createNavButton("History", e -> showHistory()),
            createNavButton("Notifications", e -> showNotifications()),
            createNavButton("Settings", e -> showSettings()),
            createNavButton("Admin", e -> showAdmin()),
            createNavButton("Help & Feedback", e -> showHelp())
        );

        return sidebar;
    }

    private Button createNavButton(String text, javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        Button btn = new Button(text);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setPrefWidth(Double.MAX_VALUE);
        btn.setPadding(new Insets(12, 15, 12, 15));
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #34495e; -fx-font-size: 14px; -fx-cursor: hand;");
        btn.setOnAction(handler);

        // Hover effect
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #f1f3f4; -fx-text-fill: #3498db; -fx-font-size: 14px; -fx-cursor: hand;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #34495e; -fx-font-size: 14px; -fx-cursor: hand;"));

        return btn;
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

    private void showAdmin() {
        contentArea.getChildren().setAll(new NovelAdminScreenPane().getPane());
    }

    private void showHelp() {
        contentArea.getChildren().setAll(new HelpFeedbackScreenPane().getPane());
    }
}
