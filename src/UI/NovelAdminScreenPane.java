package UI;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NovelAdminScreenPane {
    public Pane getPane() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(10));

        Label title = new Label("Novel Administration");
        title.setFont(Font.font("System", FontWeight.BOLD, 24));

        TableView<String> table = new TableView<>();
        TableColumn<String, String> col1 = new TableColumn<>("Title");
        TableColumn<String, String> col2 = new TableColumn<>("Author");
        TableColumn<String, String> col3 = new TableColumn<>("Actions");
        table.getColumns().addAll(col1, col2, col3);

        Button addBtn = new Button("Add New Novel");
        addBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");

        root.getChildren().addAll(title, addBtn, table);
        return root;
    }
}
