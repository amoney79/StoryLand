// Updated UI component
package UI;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HelpFeedbackScreenPane {
    public Region getPane() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(10));
        root.setMaxWidth(600);

        Label title = new Label("Help & Feedback");
        title.setFont(Font.font("System", FontWeight.BOLD, 24));

        Label info = new Label("Need help? Have feedback? Let us know below.");
        
        TextArea feedbackArea = new TextArea();
        feedbackArea.setPromptText("Enter your feedback here...");
        
        Button submitBtn = new Button("Submit Feedback");
        submitBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");

        root.getChildren().addAll(title, info, feedbackArea, submitBtn);
        return root;
    }
}
