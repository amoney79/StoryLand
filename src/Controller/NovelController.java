package Controller;

import Models.*;
import db.NovelDAO;
import java.util.ArrayList;
import java.util.List;

public class NovelController {

    private final NovelDAO novelDAO;

    public NovelController() {
        this.novelDAO = new NovelDAO();
    }

    public List<Novel> getAllNovels() {
        return novelDAO.getAllNovels();
    }

    public List<Chapter> getChaptersByNovelId(int novelId) {
        try {
            return novelDAO.getChaptersByNovelId(novelId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Bookmark> getBookmarks(int userId, int novelId) {
        try {
            return novelDAO.getBookmarks(userId, novelId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Note> getNotes(int userId, int novelId) {
        try {
            return novelDAO.getNotes(userId, novelId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean addBookmark(Bookmark bookmark) {
        try {
            return novelDAO.addBookmark(bookmark);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeBookmark(int bookmarkId) {
        try {
            return novelDAO.removeBookmark(bookmarkId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addNote(Note note) {
        try {
            return novelDAO.addNote(note);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteNote(int noteId) {
        try {
            return novelDAO.deleteNote(noteId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void saveProgress(ReadingProgress progress) {
        try {
            novelDAO.saveProgress(progress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ReadingProgress getProgress(int userId, int novelId) {
        try {
            return novelDAO.getProgress(userId, novelId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveHistory(int userId, int novelId) {
        try {
            novelDAO.saveHistory(userId, novelId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
