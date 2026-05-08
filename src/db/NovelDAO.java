package db;

import Models.*;
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

    public List<Chapter> getChaptersByNovelId(int novelId) {
        List<Chapter> chapters = new ArrayList<>();
        String sql = "SELECT * FROM chapters WHERE novel_id = ? ORDER BY chapter_number ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, novelId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    chapters.add(new Chapter(
                        rs.getInt("id"),
                        rs.getInt("novel_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getInt("chapter_number")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapters;
    }

    public List<Bookmark> getBookmarks(int userId, int novelId) {
        List<Bookmark> bookmarks = new ArrayList<>();
        String sql = "SELECT * FROM bookmarks WHERE user_id = ? AND novel_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, novelId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Bookmark b = new Bookmark(rs.getInt("user_id"), rs.getInt("novel_id"), rs.getInt("chapter_id"));
                    b.setId(rs.getInt("id"));
                    bookmarks.add(b);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookmarks;
    }

    public List<Note> getNotes(int userId, int novelId) {
        List<Note> notes = new ArrayList<>();
        String sql = "SELECT * FROM notes WHERE user_id = ? AND novel_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, novelId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Note n = new Note(rs.getInt("user_id"), rs.getInt("novel_id"), rs.getInt("chapter_id"), rs.getString("content"));
                    n.setId(rs.getInt("id"));
                    notes.add(n);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    public boolean addBookmark(Bookmark bookmark) {
        String sql = "INSERT INTO bookmarks (user_id, novel_id, chapter_id) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookmark.getUserId());
            pstmt.setInt(2, bookmark.getNovelId());
            pstmt.setInt(3, bookmark.getChapterId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeBookmark(int bookmarkId) {
        String sql = "DELETE FROM bookmarks WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookmarkId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addNote(Note note) {
        String sql = "INSERT INTO notes (user_id, novel_id, chapter_id, content) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, note.getUserId());
            pstmt.setInt(2, note.getNovelId());
            pstmt.setInt(3, note.getChapterId());
            pstmt.setString(4, note.getContent());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteNote(int noteId) {
        String sql = "DELETE FROM notes WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, noteId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void saveProgress(ReadingProgress progress) {
        String sql = "REPLACE INTO reading_progress (user_id, novel_id, chapter_index, scroll_position) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, progress.getUserId());
            pstmt.setInt(2, progress.getNovelId());
            pstmt.setInt(3, progress.getChapterIndex());
            pstmt.setDouble(4, progress.getScrollPosition());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ReadingProgress getProgress(int userId, int novelId) {
        String sql = "SELECT * FROM reading_progress WHERE user_id = ? AND novel_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, novelId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ReadingProgress(
                        rs.getInt("user_id"),
                        rs.getInt("novel_id"),
                        rs.getInt("chapter_index"),
                        rs.getDouble("scroll_position")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveHistory(int userId, int novelId) {
        String sql = "INSERT INTO history (user_id, novel_id, last_read) VALUES (?, ?, CURRENT_TIMESTAMP) " +
                     "ON DUPLICATE KEY UPDATE last_read = CURRENT_TIMESTAMP";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, novelId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Administrative Methods
    public static boolean addNovel(Novel novel) {
        String sql = "INSERT INTO novels (user_id, title, description, cover_image, genre, age_bracket) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, novel.getUserId());
            pstmt.setString(2, novel.getTitle());
            pstmt.setString(3, novel.getDescription());
            pstmt.setString(4, novel.getCoverImagePath());
            pstmt.setString(5, novel.getGenre());
            pstmt.setString(6, novel.getAgeBracket());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateNovel(Novel novel) {
        String sql = "UPDATE novels SET title = ?, description = ?, cover_image = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, novel.getTitle());
            pstmt.setString(2, novel.getDescription());
            pstmt.setString(3, novel.getCoverImagePath());
            pstmt.setInt(4, novel.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteNovel(int id) {
        String sql = "DELETE FROM novels WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addChapter(int novelId, String title, String content) {
        String sqlCount = "SELECT COUNT(*) FROM chapters WHERE novel_id = ?";
        int nextChapterNum = 1;
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sqlCount)) {
                pstmt.setInt(1, novelId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        nextChapterNum = rs.getInt(1) + 1;
                    }
                }
            }
            String sqlInsert = "INSERT INTO chapters (novel_id, title, content, chapter_number) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
                pstmt.setInt(1, novelId);
                pstmt.setString(2, title);
                pstmt.setString(3, content);
                pstmt.setInt(4, nextChapterNum);
                return pstmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteChapter(int chapterId) {
        String sql = "DELETE FROM chapters WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, chapterId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
