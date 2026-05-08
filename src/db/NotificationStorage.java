package db;

import Models.Notification;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationStorage {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/novelup_clone";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "amoney0819";

    // Fetch all notifications
    public static List<Notification> fetchAll() {
        List<Notification> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM notifications ORDER BY timestamp DESC");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Notification(
                        rs.getInt("id"),
                        rs.getString("message"),
                        rs.getString("type"),
                        rs.getBoolean("is_read"),
                        rs.getTimestamp("timestamp").toLocalDateTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Insert notification by message & type
    public static void insert(String message, String type) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO notifications (message, type, is_read, timestamp) VALUES (?, ?, FALSE, NOW())");
            ps.setString(1, message);
            ps.setString(2, type);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert full Notification object
    public static void insert(Notification notification) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO notifications (message, type, is_read, timestamp) VALUES (?, ?, ?, ?)");
            ps.setString(1, notification.getMessage());
            ps.setString(2, notification.getType());
            ps.setBoolean(3, notification.isRead());
            ps.setTimestamp(4, Timestamp.valueOf(notification.getTimestamp()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mark a single notification as read
    public static void markAsRead(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = conn.prepareStatement("UPDATE notifications SET is_read = TRUE WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mark all as read
    public static void markAllAsRead() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE notifications SET is_read = TRUE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Clear all notifications
    public static void clearAll() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE FROM notifications");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get unread count
    public static int getUnreadCount() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM notifications WHERE is_read = FALSE");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Filter by type
    public static List<Notification> getFiltered(String type) {
        List<Notification> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement ps = conn
                    .prepareStatement("SELECT * FROM notifications WHERE type = ? ORDER BY timestamp DESC");
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Notification(
                        rs.getInt("id"),
                        rs.getString("message"),
                        rs.getString("type"),
                        rs.getBoolean("is_read"),
                        rs.getTimestamp("timestamp").toLocalDateTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}