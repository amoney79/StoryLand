package UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ChapterListScreenPane {
    public Pane getPane() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(10));

        Label title = new Label("Chapters");
        title.setFont(Font.font("System", FontWeight.BOLD, 24));

        ListView<String> chapterList = new ListView<>();
        chapterList.getItems().addAll("Chapter 1: The Beginning", "Chapter 2: The Journey", "Chapter 3: The Encounter");

        root.getChildren().addAll(title, chapterList);
        return root;
    }
}
