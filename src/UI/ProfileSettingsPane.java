package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ProfileSettingsPane {
    public Pane getPane() {
        VBox root = new VBox(25);
        root.setPadding(new Insets(20));
        root.setMaxWidth(600);

        Label title = new Label("Profile Settings");
        title.setFont(Font.font("System", FontWeight.BOLD, 24));

        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);

        grid.add(new Label("Username:"), 0, 0);
        TextField usernameField = new TextField("User123");
        grid.add(usernameField, 1, 0);

        grid.add(new Label("Email:"), 0, 1);
        TextField emailField = new TextField("user@example.com");
        grid.add(emailField, 1, 1);

        grid.add(new Label("New Password:"), 0, 2);
        PasswordField passField = new PasswordField();
        grid.add(passField, 1, 2);

        Button saveBtn = new Button("Save Changes");
        saveBtn.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");
        
        root.getChildren().addAll(title, grid, saveBtn);
        return root;
    }
}
