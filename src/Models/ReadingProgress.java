package Models;

public class ReadingProgress {
    private int userId;
    private int novelId;
    private int chapterIndex;
    private double scrollPosition;

    public ReadingProgress(int userId, int novelId, int chapterIndex, double scrollPosition) {
        this.userId = userId;
        this.novelId = novelId;
        this.chapterIndex = chapterIndex;
        this.scrollPosition = scrollPosition;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNovelId() {
        return novelId;
    }

    public void setNovelId(int novelId) {
        this.novelId = novelId;
    }

    public int getChapterIndex() {
        return chapterIndex;
    }

    public void setChapterIndex(int chapterIndex) {
        this.chapterIndex = chapterIndex;
    }

    public double getScrollPosition() {
        return scrollPosition;
    }

    public void setScrollPosition(double scrollPosition) {
        this.scrollPosition = scrollPosition;
    }
}
