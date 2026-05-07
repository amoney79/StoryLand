package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HistoryScreenPane {
    public Pane getPane() {
        VBox root = new VBox(25);
        root.setPadding(new Insets(10));
        root.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());

        Label title = new Label("Reading History");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextFill(javafx.scene.paint.Color.WHITE);

        VBox historyList = new VBox(15);
        historyList.setPadding(new Insets(20));
        historyList.getStyleClass().add("card");
        
        Label emptyMsg = new Label("No history found");
        emptyMsg.getStyleClass().add("novel-title");
        Label subMsg = new Label("Your reading journey will appear here.");
        subMsg.getStyleClass().add("novel-author");

        historyList.getChildren().addAll(emptyMsg, subMsg);
        historyList.setAlignment(Pos.CENTER);

        root.getChildren().addAll(title, historyList);
        return root;
    }
}
