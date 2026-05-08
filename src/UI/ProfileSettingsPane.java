package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ProfileSettingsPane {
    public Region getPane() {
        VBox root = new VBox(25);
        root.setPadding(new Insets(10));
        root.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());

        Label title = new Label("Account Settings");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextFill(Color.WHITE);

        VBox formCard = new VBox(20);
        formCard.getStyleClass().add("card");
        formCard.setPadding(new Insets(30));
        formCard.setMaxWidth(600);

        formCard.getChildren().addAll(
                createFieldGroup("Username", "user123"),
                createFieldGroup("Email Address", "user@novelup.com"),
                createPasswordFieldGroup("New Password"),
                createPasswordFieldGroup("Confirm Password"));

        Button saveBtn = new Button("Save Changes");
        saveBtn.getStyleClass().add("primary-button");
        saveBtn.setPrefWidth(200);

        HBox footer = new HBox(saveBtn);
        footer.setAlignment(Pos.CENTER_RIGHT);

        formCard.getChildren().add(footer);
        root.getChildren().addAll(title, formCard);

        return root;
    }

    private VBox createFieldGroup(String label, String value) {
        VBox group = new VBox(8);
        Label lbl = new Label(label);
        lbl.getStyleClass().add("novel-author");
        lbl.setStyle("-fx-font-weight: bold;");

        TextField field = new TextField(value);
        field.getStyleClass().add("search-field");
        field.setPrefHeight(40);

        group.getChildren().addAll(lbl, field);
        return group;
    }

    private VBox createPasswordFieldGroup(String label) {
        VBox group = new VBox(8);
        Label lbl = new Label(label);
        lbl.getStyleClass().add("novel-author");
        lbl.setStyle("-fx-font-weight: bold;");

        PasswordField field = new PasswordField();
        field.getStyleClass().add("search-field");
        field.setPrefHeight(40);

        group.getChildren().addAll(lbl, field);
        return group;
    }
}
