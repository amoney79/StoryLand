package UI;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NovelAdminScreenPane {
    public Region getPane() {
        VBox root = new VBox(25);
        root.setPadding(new Insets(10));
        root.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());

        HBox header = new HBox();
        header.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        
        Label title = new Label("Novel Administration");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextFill(javafx.scene.paint.Color.WHITE);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button addBtn = new Button("+ Add New Novel");
        addBtn.getStyleClass().add("primary-button");
        
        header.getChildren().addAll(title, spacer, addBtn);

        VBox contentCard = new VBox(15);
        contentCard.getStyleClass().add("card");
        contentCard.setPadding(new Insets(25));
        
        // Mock Novel List for Admin
        contentCard.getChildren().addAll(
            createAdminItem("The Shadow of Eternity", "John Doe", "Active"),
            createAdminItem("Midnight Sun", "Bella Swan", "Draft"),
            createAdminItem("Echoes of War", "Unknown Author", "Suspended")
        );

        root.getChildren().addAll(header, contentCard);
        return root;
    }

    private HBox createAdminItem(String title, String author, String status) {
        HBox item = new HBox(20);
        item.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        item.setPadding(new Insets(10, 0, 10, 0));
        item.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");

        VBox info = new VBox(4);
        Label titleLbl = new Label(title);
        titleLbl.getStyleClass().add("novel-title");
        Label authorLbl = new Label(author);
        authorLbl.getStyleClass().add("novel-author");
        info.getChildren().addAll(titleLbl, authorLbl);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label statusLbl = new Label(status);
        statusLbl.getStyleClass().add("category-chip");
        if (status.equals("Draft")) statusLbl.setStyle("-fx-background-color: rgba(251, 191, 36, 0.1); -fx-text-fill: #fbbf24;");
        if (status.equals("Suspended")) statusLbl.setStyle("-fx-background-color: rgba(239, 68, 68, 0.1); -fx-text-fill: #ef4444;");

        Button editBtn = new Button("Edit");
        editBtn.getStyleClass().add("secondary-button");

        item.getChildren().addAll(info, spacer, statusLbl, editBtn);
        return item;
    }
}
