package Models;

import java.time.LocalDateTime;

public class Notification {
    private int id;
    private String message;
    private String type;
    private boolean isRead;
    private LocalDateTime timestamp;

    public Notification(int id, String message, String type, boolean isRead, LocalDateTime timestamp) {
        this.id = id;
        this.message = message;
        this.type = type;
        this.isRead = isRead;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { isRead = read; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
