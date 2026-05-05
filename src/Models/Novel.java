package Models;

public class Novel {
    private int id;
    private String title;
    private String author;
    private String description;
    private String coverImage;
    private double rating;
    private String genre;

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
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
}
