package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Cursor;
import javafx.scene.layout.Priority;

public class DashboardPane {

    public Region getPane() {
        VBox root = new VBox(30);
        root.setFillWidth(true);

        ScrollPane scroll = new ScrollPane(root);
        scroll.setFitToWidth(true);
        scroll.getStyleClass().add("transparent-scroll");
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // 1. Hero Banner
        root.getChildren().add(createHeroBanner());

        // 2. Categories
        root.getChildren().add(createCategorySection());

        // 3. Trending Now
        root.getChildren().add(createSectionHeader("Trending Now", "See All"));
        root.getChildren().add(createNovelGrid());

        // 4. Continue Reading
        root.getChildren().add(createSectionHeader("Continue Reading", "History"));
        root.getChildren().add(createHorizontalReadingList());

        return scroll;
    }

    private StackPane createHeroBanner() {
        StackPane banner = new StackPane();
        banner.getStyleClass().add("hero-banner");
        banner.setPrefHeight(300);
        banner.setStyle("-fx-background-color: linear-gradient(to right, #1e293b, #334155); -fx-background-radius: 20;");

        VBox content = new VBox(15);
        content.setAlignment(Pos.CENTER_LEFT);
        content.setPadding(new Insets(40));

        Label tag = new Label("FEATURED NOVEL");
        tag.getStyleClass().add("category-chip");
        
        Label title = new Label("The Shadow of Eternity");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        title.setTextFill(Color.WHITE);

        Label desc = new Label("A gripping tale of time travel, mystery, and forbidden love across centuries.\nRead the latest chapter now!");
        desc.setTextFill(Color.web("#94a3b8"));
        desc.setWrapText(true);
        desc.setMaxWidth(500);

        Button readBtn = new Button("Read Now");
        readBtn.getStyleClass().add("primary-button");
        readBtn.setPrefWidth(150);

        content.getChildren().addAll(tag, title, desc, readBtn);
        banner.getChildren().add(content);
        
        return banner;
    }

    private HBox createCategorySection() {
        HBox categories = new HBox(12);
        categories.setAlignment(Pos.CENTER_LEFT);
        
        String[] genres = {"All", "Fantasy", "Romance", "Sci-Fi", "Mystery", "Horror", "Action"};
        for (String genre : genres) {
            Label chip = new Label(genre);
            chip.getStyleClass().add("category-chip");
            categories.getChildren().add(chip);
        }
        
        return categories;
    }

    private HBox createSectionHeader(String title, String action) {
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        
        Label lbl = new Label(title);
        lbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        lbl.setTextFill(Color.WHITE);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Button actionBtn = new Button(action);
        actionBtn.getStyleClass().add("secondary-button");
        
        header.getChildren().addAll(lbl, spacer, actionBtn);
        return header;
    }

    private FlowPane createNovelGrid() {
        FlowPane grid = new FlowPane(20, 30);
        grid.setAlignment(Pos.CENTER_LEFT);
        
        grid.getChildren().addAll(
            createNovelCard("Midnight Sun", "Bella Swan", "4.8", "https://picsum.photos/seed/1/200/300"),
            createNovelCard("Winds of Winter", "George Martin", "4.9", "https://picsum.photos/seed/2/200/300"),
            createNovelCard("Cyber Dreams", "V. Silverhand", "4.5", "https://picsum.photos/seed/3/200/300"),
            createNovelCard("Lost in Space", "N. Armstrong", "4.7", "https://picsum.photos/seed/4/200/300"),
            createNovelCard("Echoes of War", "John Doe", "4.3", "https://picsum.photos/seed/5/200/300")
        );
        
        return grid;
    }

    private HBox createHorizontalReadingList() {
        HBox box = new HBox(20);
        box.getChildren().addAll(
            createNovelCard("Dr. Love", "Love Mister", "4.2", "https://picsum.photos/seed/6/200/300"),
            createNovelCard("Introvert Girl", "Author Name", "4.6", "https://picsum.photos/seed/7/200/300")
        );
        return box;
    }

    private VBox createNovelCard(String title, String author, String rating, String coverUrl) {
        VBox card = new VBox(10);
        card.getStyleClass().add("card");
        card.setPadding(new Insets(10));
        card.setPrefWidth(180);
        card.setCursor(Cursor.HAND);

        ImageView cover = new ImageView();
        cover.setFitWidth(160);
        cover.setFitHeight(230);
        cover.getStyleClass().add("novel-cover");
        
        // Use a clip to round the image corners
        Rectangle clip = new Rectangle(160, 230);
        clip.setArcWidth(15);
        clip.setArcHeight(15);
        cover.setClip(clip);

        try {
            cover.setImage(new Image(coverUrl, 160, 230, true, true));
        } catch (Exception e) {
            // Placeholder color if image fails
        }

        Label titleLbl = new Label(title);
        titleLbl.getStyleClass().add("novel-title");
        titleLbl.setWrapText(true);

        Label authorLbl = new Label(author);
        authorLbl.getStyleClass().add("novel-author");

        Label ratingLbl = new Label("⭐ " + rating);
        ratingLbl.setStyle("-fx-text-fill: #fbbf24; -fx-font-size: 11px;");

        card.getChildren().addAll(cover, titleLbl, authorLbl, ratingLbl);
        
        // Click effect
        card.setOnMouseClicked(e -> {
            Models.Novel novelObj = new Models.Novel(0, title, author, 
                "An amazing story that will keep you on the edge of your seat. Explore the world of " + title + " and discover secrets hidden for ages.",
                coverUrl, Double.parseDouble(rating), "Fantasy");
            LandingPage.getInstance().showNovelDetail(novelObj);
        });

        // Hover effect
        card.setOnMouseEntered(e -> card.setStyle("-fx-background-color: #334155; -fx-background-radius: 15;"));
        card.setOnMouseExited(e -> card.setStyle("-fx-background-color: #1e293b; -fx-background-radius: 15;"));

        return card;
    }
}
