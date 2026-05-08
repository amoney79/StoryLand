package UI;

import Models.Notification;
import Models.User;
import db.NotificationStorage;
import utils.NotificationManager;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Region;

import java.util.List;

public class NotificationScreenPane implements ProfileSettingsPane.SceneAware {
    private final User user;
    private final VBox root;
    private final VBox listBox;

    public NotificationScreenPane(User user) {
        this.user = user;
        this.root = new VBox(10);
        this.listBox = new VBox(10);
        initializeUI();
        loadNotifications();
        NotificationManager.startPolling(this::loadNotifications);
    }

    public NotificationScreenPane() {
        this(null);
    }

    private void initializeUI() {
        ScrollPane scrollPane = new ScrollPane(listBox);
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("transparent-scroll");

        Label header = new Label("🔔 Notifications");
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");
        
        Button refresh = new Button("🔄 Refresh");
        refresh.getStyleClass().add("secondary-button");
        
        Button clearAll = new Button("🗑 Clear All");
        clearAll.getStyleClass().add("secondary-button");

        HBox top = new HBox(20, header, refresh, clearAll);
        top.setPadding(new Insets(0, 0, 10, 0));
        top.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        refresh.setOnAction(e -> loadNotifications());
        clearAll.setOnAction(e -> {
            NotificationStorage.clearAll();
            loadNotifications();
        });

        root.setPadding(new Insets(20));
        root.getChildren().addAll(top, scrollPane);
    }

    private void loadNotifications() {
        javafx.application.Platform.runLater(() -> {
            listBox.getChildren().clear();
            List<Notification> list = NotificationStorage.fetchAll();

            if (list.isEmpty()) {
                Label empty = new Label("No new notifications.");
                empty.setStyle("-fx-text-fill: #94a3b8;");
                listBox.getChildren().add(empty);
                return;
            }

            for (Notification notif : list) {
                HBox card = new HBox(15);
                card.getStyleClass().add("notification-card");
                card.setPadding(new Insets(15));
                card.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
                card.setStyle("-fx-background-color: " + (notif.isRead() ? "rgba(255,255,255,0.05)" : "rgba(59,130,246,0.1)") + "; -fx-background-radius: 10;");

                VBox textInfo = new VBox(5);
                Label msg = new Label(notif.getMessage());
                msg.setStyle("-fx-text-fill: white; -fx-font-weight: " + (notif.isRead() ? "normal" : "bold") + ";");
                msg.setWrapText(true);
                
                Label time = new Label(notif.getTimestamp().toString());
                time.setStyle("-fx-text-fill: #94a3b8; -fx-font-size: 11px;");
                
                textInfo.getChildren().addAll(msg, time);
                
                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                
                CheckBox cb = new CheckBox();
                cb.setSelected(notif.isRead());
                cb.setOnAction(e -> {
                    if (!notif.isRead()) {
                        NotificationStorage.markAsRead(notif.getId());
                        notif.setRead(true);
                        loadNotifications();
                    }
                });

                card.getChildren().addAll(textInfo, spacer, cb);
                listBox.getChildren().add(card);
            }
        });
    }

    public Region getPane() {
        return root;
    }

    @Override
    public Node getContent() {
        return root;
    }
}
