package UI;

import Models.User;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.Region;

public class DownloadedNovelsScreen implements ProfileSettingsPane.SceneAware {
    private final User user;

    public DownloadedNovelsScreen(User user) {
        this.user = user;
    }

    public DownloadedNovelsScreen() {
        this.user = null;
    }

    @Override
    public Node getContent() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        
        Label title = new Label("📥 Downloaded Novels");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");
        
        Label placeholder = new Label("No downloaded novels yet. Start downloading to read offline!");
        placeholder.setStyle("-fx-text-fill: #94a3b8;");
        
        root.getChildren().addAll(title, placeholder);
        return root;
    }
    
    public Region getPane() {
        return (Region) getContent();
    }
}
