package Models;

public class Bookmark {
    private int id;
    private int userId;
    private int novelId;
    private int chapterId;

    public Bookmark() {}

    public Bookmark(int userId, int novelId, int chapterId) {
        this.userId = userId;
        this.novelId = novelId;
        this.chapterId = chapterId;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getNovelId() {
        return novelId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setNovelId(int novelId) {
        this.novelId = novelId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }
}