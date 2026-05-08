package UI;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import Models.User;

public class HelpFeedbackScreen implements ProfileSettingsScreen.SceneAware {
    private final User user;
    private VBox root;

    public HelpFeedbackScreen(User user) {
        this.user = user;
        initializeUI();
    }

    private void initializeUI() {
        root = new VBox(15);
        root.setPadding(new Insets(20));

        Label title = new Label("❓ Help & Feedback");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button howToUse = new Button("📖 How to Use App");
        howToUse.setMaxWidth(Double.MAX_VALUE);
        howToUse.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Use the 'Explore' tab to find novels.\nTap on any novel to start reading.\nCheck 'Library' to manage your books.");
            alert.setTitle("How to Use");
            alert.setHeaderText("Using the App");
            alert.showAndWait();
        });

        Button feedback = new Button("✉️ Send Feedback");
        feedback.setMaxWidth(Double.MAX_VALUE);
        feedback.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Send your feedback to: support@imaginationWorld.com");
            alert.setTitle("Feedback");
            alert.setHeaderText("We value your thoughts!");
            alert.showAndWait();
        });

        root.getChildren().addAll(title, howToUse, feedback);
    }

    @Override
    public Node getContent() {
        return root;
    }
}