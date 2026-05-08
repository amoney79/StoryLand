package Models;

public class Novel {
    private int id;
    private int userId;
    private String title;
    private String author;
    private String description;
    private String coverImage;
    private String coverImagePath; // For local files
    private double rating;
    private String genre;
    private String ageBracket;

    public Novel() {}

    public Novel(int id, String title, String author, String description, String coverImage, double rating, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.coverImage = coverImage;
        this.rating = rating;
        this.genre = genre;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }

    public String getCoverImagePath() { return coverImagePath; }
    public void setCoverImagePath(String coverImagePath) { this.coverImagePath = coverImagePath; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getAgeBracket() { return ageBracket; }
    public void setAgeBracket(String ageBracket) { this.ageBracket = ageBracket; }
}
