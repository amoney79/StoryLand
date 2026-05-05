package UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NotificationScreenPane {
    public Pane getPane() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(10));

        Label title = new Label("Notifications");
        title.setFont(Font.font("System", FontWeight.BOLD, 24));

        VBox notifList = new VBox(15);
        notifList.getChildren().addAll(
            createNotificationItem("Welcome to StoryLand!", "2 hours ago"),
            createNotificationItem("New chapter available: MoonLit", "Yesterday")
        );

        root.getChildren().addAll(title, notifList);
        return root;
    }

    private HBox createNotificationItem(String message, String time) {
        HBox item = new HBox(20);
        item.setPadding(new Insets(15));
        item.setStyle("-fx-background-color: white; -fx-background-radius: 5; -fx-border-color: #eee;");
        
        Label msgLbl = new Label(message);
        msgLbl.setStyle("-fx-font-size: 14px;");
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Label timeLbl = new Label(time);
        timeLbl.setStyle("-fx-text-fill: gray; -fx-font-size: 12px;");

        item.getChildren().addAll(msgLbl, spacer, timeLbl);
        return item;
    }
}
