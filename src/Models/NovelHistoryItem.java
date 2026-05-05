package Models;

import java.time.LocalDateTime;

public class NovelHistoryItem {
    private int id;
    private int userId;
    private int novelId;
    private LocalDateTime lastRead;
    private int lastChapterRead;

    public NovelHistoryItem(int id, int userId, int novelId, LocalDateTime lastRead, int lastChapterRead) {
        this.id = id;
        this.userId = userId;
        this.novelId = novelId;
        this.lastRead = lastRead;
        this.lastChapterRead = lastChapterRead;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getNovelId() { return novelId; }
    public void setNovelId(int novelId) { this.novelId = novelId; }
    public LocalDateTime getLastRead() { return lastRead; }
    public void setLastRead(LocalDateTime lastRead) { this.lastRead = lastRead; }
    public int getLastChapterRead() { return lastChapterRead; }
    public void setLastChapterRead(int lastChapterRead) { this.lastChapterRead = lastChapterRead; }
}
