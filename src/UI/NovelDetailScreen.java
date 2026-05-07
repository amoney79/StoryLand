package UI;

import Models.Novel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NovelDetailScreen {

    private final Novel novel;

    public NovelDetailScreen(Novel novel) {
        this.novel = novel;
    }

    public Region getPane() {
        VBox root = new VBox(30);
        root.setPadding(new Insets(10));
        root.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());

        // Header with Back Button
        Button backBtn = new Button("← Back to Home");
        backBtn.getStyleClass().add("secondary-button");
        backBtn.setOnAction(e -> {
            LandingPage.getInstance().showDashboard();
        });

        // Top Content: Cover + Info
        HBox topContent = new HBox(40);
        topContent.setAlignment(Pos.TOP_LEFT);

        ImageView cover = new ImageView();
        cover.setFitWidth(240);
        cover.setFitHeight(340);
        javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(240, 340);
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        cover.setClip(clip);
        
        try {
            cover.setImage(new Image(novel.getCoverImage(), 240, 340, true, true));
        } catch (Exception e) {}

        VBox details = new VBox(15);
        details.setAlignment(Pos.TOP_LEFT);

        Label title = new Label(novel.getTitle());
        title.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        title.setTextFill(Color.WHITE);

        Label author = new Label("by " + novel.getAuthor());
        author.setFont(Font.font("Arial", 18));
        author.setTextFill(Color.web("#94a3b8"));

        HBox stats = new HBox(20);
        stats.getChildren().addAll(
            createStat("Rating", "⭐ " + novel.getRating()),
            createStat("Genre", novel.getGenre()),
            createStat("Status", "Ongoing")
        );

        Label descTitle = new Label("Synopsis");
        descTitle.getStyleClass().add("novel-title");
        
        Label description = new Label(novel.getDescription());
        description.setTextFill(Color.web("#94a3b8"));
        description.setWrapText(true);
        description.setMaxWidth(600);

        HBox actions = new HBox(15);
        Button readBtn = new Button("Start Reading");
        readBtn.getStyleClass().add("primary-button");
        readBtn.setPrefWidth(180);
        
        Button libraryBtn = new Button("+ Add to Library");
        libraryBtn.getStyleClass().add("secondary-button");
        libraryBtn.setStyle("-fx-border-color: #3b82f6; -fx-border-radius: 10; -fx-text-fill: #3b82f6;");

        actions.getChildren().addAll(readBtn, libraryBtn);

        details.getChildren().addAll(title, author, stats, descTitle, description, actions);

        topContent.getChildren().addAll(cover, details);

        // Bottom Content: Chapter List
        VBox chapterSection = new VBox(15);
        Label chapterTitle = new Label("Chapters");
        chapterTitle.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        chapterTitle.setTextFill(Color.WHITE);

        VBox chapterList = new VBox(10);
        chapterList.getStyleClass().add("card");
        chapterList.setPadding(new Insets(20));

        for (int i = 1; i <= 5; i++) {
            chapterList.getChildren().add(createChapterItem("Chapter " + i + ": The Beginning of the End", "May " + i + ", 2026"));
        }

        chapterSection.getChildren().addAll(chapterTitle, chapterList);

        root.getChildren().addAll(backBtn, topContent, chapterSection);

        ScrollPane scroll = new ScrollPane(root);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        return scroll;
    }

    private VBox createStat(String label, String value) {
        VBox box = new VBox(4);
        Label lbl = new Label(label);
        lbl.getStyleClass().add("novel-author");
        lbl.setStyle("-fx-font-size: 11px;");
        Label val = new Label(value);
        val.getStyleClass().add("novel-title");
        val.setStyle("-fx-font-size: 14px;");
        box.getChildren().addAll(lbl, val);
        return box;
    }

    private HBox createChapterItem(String title, String date) {
        HBox item = new HBox();
        item.setPadding(new Insets(10, 0, 10, 0));
        item.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");
        item.setAlignment(Pos.CENTER_LEFT);
        item.setCursor(javafx.scene.Cursor.HAND);

        Label titleLbl = new Label(title);
        titleLbl.getStyleClass().add("novel-title");
        titleLbl.setStyle("-fx-font-size: 14px;");
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Label dateLbl = new Label(date);
        dateLbl.getStyleClass().add("novel-author");
        
        item.getChildren().addAll(titleLbl, spacer, dateLbl);
        
        item.setOnMouseEntered(e -> titleLbl.setStyle("-fx-text-fill: #3b82f6; -fx-font-size: 14px;"));
        item.setOnMouseExited(e -> titleLbl.setStyle("-fx-text-fill: white; -fx-font-size: 14px;"));
        
        return item;
    }
}
