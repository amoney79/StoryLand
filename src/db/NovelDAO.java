package db;

import Models.Novel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NovelDAO {
    public List<Novel> getAllNovels() {
        List<Novel> novels = new ArrayList<>();
        String sql = "SELECT * FROM novels";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                novels.add(new Novel(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("description"),
                    rs.getString("cover_image"),
                    rs.getDouble("rating"),
                    rs.getString("genre")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return novels;
    }
}
