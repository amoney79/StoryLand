package utils;

import db.NotificationStorage;
import Models.Notification;
import UI.Toast;
import javafx.application.Platform;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationManager {
    private static final int POLL_INTERVAL = 10000; // 10 seconds
    private static List<Notification> cached = NotificationStorage.fetchAll();

    public static void startPolling(Runnable onUpdate) {
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            public void run() {
                List<Notification> latest = NotificationStorage.fetchAll();
                if (latest.size() > cached.size()) {
                    Notification newNotif = latest.get(0);
                    Platform.runLater(() -> {
                        Toast.show("🔔 " + newNotif.getMessage());
                        onUpdate.run();
                    });
                }
                cached = latest;
            }
        }, 0, POLL_INTERVAL);
    }

    public static int unreadCount() {
        return (int) NotificationStorage.fetchAll().stream().filter(n -> !n.isRead()).count();
    }

    public static List<Notification> getFiltered(String type) {
        return NotificationStorage.fetchAll().stream()
                .filter(n -> n.getType().equalsIgnoreCase(type)).toList();
    }
} {
    
}
