package UI;

import Models.Notification;
import Models.User;
import db.NotificationStorage;
import utils.NotificationManager;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.List;

public class NotificationScreen implements ProfileSettingsScreen.SceneAware {
    private final User user;
    private final VBox root;
    private final VBox listBox;

    public NotificationScreen(User user) {
        this.user = user;
        this.root = new VBox(10);
        this.listBox = new VBox(10);
        initializeUI();
        loadNotifications();
        NotificationManager.startPolling(this::loadNotifications); // Optional: real-time updates
    }

    private void initializeUI() {
        ScrollPane scrollPane = new ScrollPane(listBox);
        scrollPane.setFitToWidth(true);

        Label header = new Label("🔔 Notifications");
        Button refresh = new Button("🔄 Refresh");
        Button clearAll = new Button("🗑 Clear All");

        HBox top = new HBox(20, header, refresh, clearAll);
        top.setPadding(new Insets(0, 0, 10, 0));

        refresh.setOnAction(e -> loadNotifications());
        clearAll.setOnAction(e -> {
            NotificationStorage.clearAll();  // assumes static access
            loadNotifications();
        });

        root.setPadding(new Insets(20));
        root.getChildren().addAll(top, scrollPane);
    }

    private void loadNotifications() {
        listBox.getChildren().clear();
        List<Notification> list = NotificationStorage.fetchAll();

        for (Notification notif : list) {
            CheckBox cb = new CheckBox(notif.getMessage());
            cb.setSelected(notif.isRead());
            cb.setOnAction(e -> {
                if (!notif.isRead()) {
                    NotificationStorage.markAsRead(notif.getId());
                    notif.setRead(true);
                }
            });
            listBox.getChildren().add(cb);
        }
    }

    @Override
    public Node getContent() {
        return root;
    }
}