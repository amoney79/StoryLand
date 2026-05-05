package Controller;

import Models.Novel;
import db.NovelDAO;
import java.util.List;

public class NovelController {
    private NovelDAO novelDAO;

    public NovelController() {
        this.novelDAO = new NovelDAO();
    }

    public List<Novel> getAllNovels() {
        return novelDAO.getAllNovels();
    }
}
