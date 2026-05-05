package UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LibraryPane {
    public Pane getPane() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(10));

        Label title = new Label("Your Library");
        title.setFont(Font.font("System", FontWeight.BOLD, 24));
        title.setTextFill(Color.web("#2c3e50"));

        FlowPane novelGrid = new FlowPane();
        novelGrid.setHgap(20);
        novelGrid.setVgap(20);

        // Mock novels
        novelGrid.getChildren().addAll(
            createNovelCard("Dr. Love", "Love Mister"),
            createNovelCard("Breaking Chains", "Author Name"),
            createNovelCard("Introvert Girl", "Author Name"),
            createNovelCard("MoonLit", "Author Name")
        );

        root.getChildren().addAll(title, novelGrid);
        return root;
    }

    private VBox createNovelCard(String title, String author) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);");
        card.setPrefWidth(160);

        Region cover = new Region();
        cover.setPrefHeight(220);
        cover.setStyle("-fx-background-color: #ddd; -fx-background-radius: 4;");

        Label titleLbl = new Label(title);
        titleLbl.setFont(Font.font("System", FontWeight.BOLD, 14));
        titleLbl.setWrapText(true);

        Label authorLbl = new Label(author);
        authorLbl.setFont(Font.font("System", 12));
        authorLbl.setTextFill(Color.GRAY);

        card.getChildren().addAll(cover, titleLbl, authorLbl);
        return card;
    }
}
