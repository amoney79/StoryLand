package Models;

public class Note {
    private int id;
    private int userId;
    private int novelId;
    private int chapterId;
    private String content;

    public Note() {}

    public Note(int userId, int novelId, int chapterId, String content) {
        this.userId = userId;
        this.novelId = novelId;
        this.chapterId = chapterId;
        this.content = content;
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

    public String getContent() {
        return content;
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

    public void setContent(String content) {
        this.content = content;
    }
}