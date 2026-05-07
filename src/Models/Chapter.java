package Models;

public class Chapter {
    private int id;
    private int novelId;
    private String title;
    private String content;
    private int chapterNumber;

    public Chapter() {}

    public Chapter(int id, int novelId, String title, String content, int chapterNumber) {
        this.id = id;
        this.novelId = novelId;
        this.title = title;
        this.content = content;
        this.chapterNumber = chapterNumber;
    }

    public int getId() {
        return id;
    }

    public int getNovelId() {
        return novelId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNovelId(int novelId) {
        this.novelId = novelId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }
}