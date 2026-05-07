package UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NotificationScreenPane {
    public Region getPane() {
        VBox root = new VBox(25);
        root.setPadding(new Insets(10));
        root.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());

        Label title = new Label("Notifications");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextFill(javafx.scene.paint.Color.WHITE);

        VBox notifList = new VBox(12);
        notifList.getChildren().addAll(
            createNotificationItem("Welcome to NovelUp!", "Enjoy your reading journey with us.", "2 hours ago"),
            createNotificationItem("New Chapter Alert", "A new chapter for 'Midnight Sun' is now available.", "Yesterday"),
            createNotificationItem("System Maintenance", "Scheduled maintenance on Sunday at 2 AM.", "2 days ago")
        );

        root.getChildren().addAll(title, notifList);
        return root;
    }

    private VBox createNotificationItem(String title, String message, String time) {
        VBox item = new VBox(8);
        item.getStyleClass().add("card");
        item.setPadding(new Insets(20));
        
        HBox header = new HBox();
        Label titleLbl = new Label(title);
        titleLbl.getStyleClass().add("novel-title");
        titleLbl.setStyle("-fx-font-size: 15px;");
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Label timeLbl = new Label(time);
        timeLbl.getStyleClass().add("novel-author");
        timeLbl.setStyle("-fx-font-size: 11px;");
        
        header.getChildren().addAll(titleLbl, spacer, timeLbl);
        
        Label msgLbl = new Label(message);
        msgLbl.getStyleClass().add("novel-author");
        msgLbl.setWrapText(true);

        item.getChildren().addAll(header, msgLbl);
        
        // Hover effect
        item.setOnMouseEntered(e -> item.setStyle("-fx-background-color: #334155; -fx-background-radius: 15;"));
        item.setOnMouseExited(e -> item.setStyle("-fx-background-color: #1e293b; -fx-background-radius: 15;"));
        
        return item;
    }
}
