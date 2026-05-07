package UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LibraryPane {
    public Region getPane() {
        VBox root = new VBox(25);
        root.setPadding(new Insets(10));
        root.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());

        Label title = new Label("My Library");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextFill(Color.WHITE);

        FlowPane grid = new FlowPane(20, 30);
        grid.getChildren().addAll(
            createNovelCard("Dr. Love", "Love Mister", "4.5", "https://picsum.photos/seed/10/200/300"),
            createNovelCard("Breaking Chains", "Author Name", "4.8", "https://picsum.photos/seed/11/200/300"),
            createNovelCard("Introvert Girl", "Author Name", "4.7", "https://picsum.photos/seed/12/200/300"),
            createNovelCard("MoonLit", "Author Name", "4.9", "https://picsum.photos/seed/13/200/300")
        );

        root.getChildren().addAll(title, grid);
        
        ScrollPane scroll = new ScrollPane(root);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        
        return scroll;
    }

    private VBox createNovelCard(String title, String author, String rating, String coverUrl) {
        VBox card = new VBox(10);
        card.getStyleClass().add("card");
        card.setPadding(new Insets(10));
        card.setPrefWidth(180);

        ImageView cover = new ImageView();
        cover.setFitWidth(160);
        cover.setFitHeight(230);
        javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(160, 230);
        clip.setArcWidth(15);
        clip.setArcHeight(15);
        cover.setClip(clip);

        try {
            cover.setImage(new Image(coverUrl, 160, 230, true, true));
        } catch (Exception e) {}

        Label titleLbl = new Label(title);
        titleLbl.getStyleClass().add("novel-title");
        Label authorLbl = new Label(author);
        authorLbl.getStyleClass().add("novel-author");

        card.getChildren().addAll(cover, titleLbl, authorLbl);
        return card;
    }
}
