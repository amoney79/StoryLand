package UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HistoryScreenPane {
    public Pane getPane() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(10));

        Label title = new Label("Reading History");
        title.setFont(Font.font("System", FontWeight.BOLD, 24));

        VBox historyList = new VBox(10);
        historyList.getChildren().addAll(
            new Label("You haven't read any novels yet."),
            new Label("Start exploring to fill your history!")
        );

        root.getChildren().addAll(title, historyList);
        return root;
    }
}
