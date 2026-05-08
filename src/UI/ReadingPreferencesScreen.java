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

public class ReadingPreferencesScreen extends Application implements SceneAware {
    private final User user;

    public ReadingPreferencesScreen(User user) {
        this.user = user;
    }

    public ReadingPreferencesScreen() {
        this.user = null;
    }

    @Override
    public void start(Stage stage) {
        VBox root = buildContent();
        stage.setScene(new Scene(root, 400, 350));
        stage.show();
    }

    @Override
    public Node getContent() {
        return buildContent();
    }

    private VBox buildContent() {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        Label heading = new Label("📚 Reading Preferences");
        heading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label ageLabel = new Label("Select Age Group:");
        ComboBox<String> ageGroup = new ComboBox<>();
        ageGroup.getItems().addAll("14-17", "18-24", "25-34", "35-44", "45+");
        ageGroup.setPromptText("Choose your age group");

        Label genreLabel = new Label("Choose Favorite Genres:");
        VBox genreBox = new VBox(5);
        for (String genre : new String[]{"Romance", "Mystery", "Thriller", "Teen", "Marriage"}) {
            genreBox.getChildren().add(new CheckBox(genre));
        }

        ScrollPane genreScroll = new ScrollPane(genreBox);
        genreScroll.setFitToWidth(true);
        genreScroll.setPrefHeight(100);

        Button save = new Button("💾 Save Preferences");
        save.setOnAction(e -> {
            // Placeholder save logic
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Preferences saved!");
            alert.showAndWait();
        });

        root.getChildren().addAll(heading, ageLabel, ageGroup, genreLabel, genreScroll, save);
        return root;
    }
}