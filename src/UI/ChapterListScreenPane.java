package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ChapterListScreenPane {
    
    public Region getPane() {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("chat-background");
        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        // WhatsApp-style Header
        root.setTop(createChatHeader());

        // Chat Messages Area (Novel Content)
        VBox chatContent = new VBox(15);
        chatContent.setPadding(new Insets(20));
        chatContent.getStyleClass().add("chat-background");

        ScrollPane scroll = new ScrollPane(chatContent);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");
        
        // Mock Novel Content as "Messages"
        addMessage(chatContent, "In the heart of the ancient forest, a secret was buried deep within the roots of the Eldertree... 🌲✨", "10:15 AM", false);
        addMessage(chatContent, "Elara stepped cautiously over the mossy ground. Every snap of a twig sounded like a thunderclap in the silence.", "10:16 AM", false);
        addMessage(chatContent, "\"Is anyone there?\" she whispered, her voice trembling. 😰", "10:17 AM", true);
        addMessage(chatContent, "A pair of glowing eyes peered from the darkness. The creature didn't move, but Elara could feel its intense gaze.", "10:18 AM", false);
        addMessage(chatContent, "Chapter 1: The Awakening has begun. 🌑🔓", "10:20 AM", false);

        root.setCenter(scroll);

        // WhatsApp-like Footer (Input area for "Continue Reading")
        root.setBottom(createChatFooter());

        return root;
    }

    private HBox createChatHeader() {
        HBox header = new HBox(15);
        header.getStyleClass().add("chat-header");
        header.setAlignment(Pos.CENTER_LEFT);

        Button backBtn = new Button("←");
        backBtn.getStyleClass().add("secondary-button");
        backBtn.setStyle("-fx-text-fill: #8696a0; -fx-font-size: 20px; -fx-cursor: hand;");
        backBtn.setOnAction(e -> LandingPage.getInstance().showDashboard());

        ImageView avatar = new ImageView(new Image("https://ui-avatars.com/api/?name=Author&background=00a884&color=fff"));
        avatar.setFitWidth(38);
        avatar.setFitHeight(38);
        avatar.setClip(new Circle(19, 19, 19));

        VBox authorInfo = new VBox(2);
        Label name = new Label("Novel Narrator");
        name.setStyle("-fx-text-fill: #e9edef; -fx-font-weight: bold; -fx-font-size: 14px;");
        Label status = new Label("writing novel content...");
        status.setStyle("-fx-text-fill: #00a884; -fx-font-size: 11px;");
        authorInfo.getChildren().addAll(name, status);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label icons = new Label("🎥  📞  ⋮");
        icons.setStyle("-fx-text-fill: #8696a0; -fx-font-size: 18px;");

        header.getChildren().addAll(backBtn, avatar, authorInfo, spacer, icons);
        return header;
    }

    private void addMessage(VBox container, String text, String time, boolean isSent) {
        HBox wrapper = new HBox();
        wrapper.setAlignment(isSent ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        
        VBox bubble = new VBox(5);
        bubble.getStyleClass().add(isSent ? "chat-bubble-sent" : "chat-bubble-received");
        bubble.setMaxWidth(500);

        Text content = new Text(text);
        content.getStyleClass().add("chat-text");
        content.setWrappingWidth(470);
        TextFlow textFlow = new TextFlow(content);

        Label timestamp = new Label(time);
        timestamp.getStyleClass().add("chat-timestamp");
        
        HBox timeBox = new HBox(timestamp);
        timeBox.setAlignment(Pos.BOTTOM_RIGHT);

        bubble.getChildren().addAll(textFlow, timeBox);
        wrapper.getChildren().add(bubble);
        container.getChildren().add(wrapper);
    }

    private HBox createChatFooter() {
        HBox footer = new HBox(15);
        footer.getStyleClass().add("chat-input-area");
        footer.setAlignment(Pos.CENTER_LEFT);

        Label emojiIcon = new Label("😊");
        emojiIcon.setStyle("-fx-font-size: 20px; -fx-text-fill: #8696a0;");

        TextField input = new TextField();
        input.setPromptText("Type your thoughts...");
        input.setStyle("-fx-background-color: #2a3942; -fx-text-fill: #e9edef; -fx-background-radius: 20; -fx-padding: 10 15; -fx-font-size: 14px;");
        HBox.setHgrow(input, Priority.ALWAYS);

        Label micIcon = new Label("🎙️");
        micIcon.setStyle("-fx-font-size: 20px; -fx-text-fill: #8696a0;");

        footer.getChildren().addAll(emojiIcon, input, micIcon);
        return footer;
    }
}
