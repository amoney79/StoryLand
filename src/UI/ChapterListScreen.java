package UI;

import Controller.NovelController;
import Models.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.List;

public class ChapterListScreen {

    private final Novel novel;
    private final User user;
    private final NovelController controller = new NovelController();

    private List<Chapter> chapters;
    private final SimpleIntegerProperty currentChapterIndex = new SimpleIntegerProperty(0);

    private BorderPane root;
    private VBox chatContent;
    private ScrollPane scrollPane;
    private Label progressLabel;

    public ChapterListScreen(Novel novel, User user) {
        this.novel = novel;
        this.user = user;
    }

    public Parent getView() {
        chapters = controller.getChaptersByNovelId(novel.getId());

        root = new BorderPane();
        root.getStyleClass().add("chat-background");
        // Ensure the style is applied.
        root.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());

        // WhatsApp Header
        root.setTop(createChatHeader());

        // Chat Area
        chatContent = new VBox(15);
        chatContent.setPadding(new Insets(20));
        chatContent.getStyleClass().add("chat-background");

        scrollPane = new ScrollPane(chatContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");

        root.setCenter(scrollPane);

        // Footer
        root.setBottom(createChatFooter());

        // Load initial content
        loadLastProgress();

        return root;
    }

    private HBox createChatHeader() {
        HBox header = new HBox(15);
        header.getStyleClass().add("chat-header");
        header.setAlignment(Pos.CENTER_LEFT);

        Button backBtn = new Button("←");
        backBtn.getStyleClass().add("secondary-button");
        backBtn.setStyle("-fx-text-fill: #8696a0; -fx-font-size: 20px; -fx-cursor: hand;");
        backBtn.setOnAction(e -> LandingPage.getInstance().showNovelDetail(novel));

        ImageView avatar = new ImageView();
        avatar.setFitWidth(38);
        avatar.setFitHeight(38);
        avatar.setClip(new Circle(19, 19, 19));
        
        try {
            avatar.setImage(new Image(novel.getCoverImage(), 38, 38, true, true));
        } catch (Exception e) {
            avatar.setImage(new Image("https://ui-avatars.com/api/?name=" + novel.getAuthor() + "&background=00a884&color=fff"));
        }

        VBox info = new VBox(2);
        Label name = new Label(novel.getTitle());
        name.setStyle("-fx-text-fill: #e9edef; -fx-font-weight: bold; -fx-font-size: 14px;");
        progressLabel = new Label("Reading...");
        progressLabel.setStyle("-fx-text-fill: #00a884; -fx-font-size: 11px;");
        info.getChildren().addAll(name, progressLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button chapterBtn = new Button("☰");
        chapterBtn.getStyleClass().add("secondary-button");
        chapterBtn.setStyle("-fx-text-fill: #8696a0; -fx-font-size: 18px; -fx-cursor: hand;");
        chapterBtn.setOnAction(e -> showChapterMenu(chapterBtn));

        header.getChildren().addAll(backBtn, avatar, info, spacer, chapterBtn);
        return header;
    }

    private void showChapterMenu(Button anchor) {
        ContextMenu menu = new ContextMenu();
        for (int i = 0; i < chapters.size(); i++) {
            int index = i;
            MenuItem item = new MenuItem("Chapter " + (i + 1) + ": " + chapters.get(i).getTitle());
            item.setOnAction(e -> openChapter(index));
            menu.getItems().add(item);
        }
        menu.show(anchor, Side.BOTTOM, 0, 0);
    }

    private void openChapter(int index) {
        if (index < 0 || index >= chapters.size()) return;
        currentChapterIndex.set(index);
        Chapter chapter = chapters.get(index);

        chatContent.getChildren().clear();
        
        // Add Title as a system message
        addMessage("Chapter " + (index + 1) + ": " + chapter.getTitle(), "SYSTEM", false);
        
        // Split content into "messages" by paragraphs for WhatsApp feel
        if (chapter.getContent() != null) {
            String[] paragraphs = chapter.getContent().split("\n");
            for (String p : paragraphs) {
                if (!p.trim().isEmpty()) {
                    addMessage(p.trim(), "", false); // Time can be empty or mock
                }
            }
        }

        updateProgress();
        saveProgress();
        
        // Scroll to top
        scrollPane.setVvalue(0);
    }

    private void addMessage(String text, String time, boolean isSent) {
        HBox wrapper = new HBox();
        wrapper.setAlignment(isSent ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        
        VBox bubble = new VBox(5);
        bubble.getStyleClass().add(isSent ? "chat-bubble-sent" : "chat-bubble-received");
        bubble.setMaxWidth(650);

        Text content = new Text(text);
        content.getStyleClass().add("chat-text");
        content.setWrappingWidth(620);
        TextFlow textFlow = new TextFlow(content);

        bubble.getChildren().add(textFlow);

        if (!time.isEmpty()) {
            Label timestamp = new Label(time);
            timestamp.getStyleClass().add("chat-timestamp");
            HBox timeBox = new HBox(timestamp);
            timeBox.setAlignment(Pos.BOTTOM_RIGHT);
            bubble.getChildren().add(timeBox);
        }

        wrapper.getChildren().add(bubble);
        chatContent.getChildren().add(wrapper);
    }

    private void updateProgress() {
        if (chapters.isEmpty()) return;
        double progress = (double) (currentChapterIndex.get() + 1) / chapters.size();
        progressLabel.setText((int)(progress * 100) + "% Read");
    }

    private void saveProgress() {
        if (user != null) {
            controller.saveProgress(new ReadingProgress(user.getId(), novel.getId(), currentChapterIndex.get(), 0));
            controller.saveHistory(user.getId(), novel.getId());
        }
    }

    private void loadLastProgress() {
        if (user != null) {
            ReadingProgress p = controller.getProgress(user.getId(), novel.getId());
            if (p != null) {
                openChapter(p.getChapterIndex());
                return;
            }
        }
        
        if (!chapters.isEmpty()) {
            openChapter(0);
        }
    }

    private HBox createChatFooter() {
        HBox footer = new HBox(15);
        footer.getStyleClass().add("chat-input-area");
        footer.setAlignment(Pos.CENTER_LEFT);

        Button prevBtn = new Button("◀ Prev Chapter");
        prevBtn.getStyleClass().add("secondary-button");
        prevBtn.setOnAction(e -> openChapter(currentChapterIndex.get() - 1));
        prevBtn.disableProperty().bind(Bindings.createBooleanBinding(
            () -> currentChapterIndex.get() <= 0,
            currentChapterIndex
        ));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button nextBtn = new Button("Next Chapter ▶");
        nextBtn.getStyleClass().add("primary-button");
        nextBtn.setOnAction(e -> openChapter(currentChapterIndex.get() + 1));
        nextBtn.disableProperty().bind(Bindings.createBooleanBinding(
            () -> chapters != null && currentChapterIndex.get() >= chapters.size() - 1,
            currentChapterIndex
        ));

        footer.getChildren().addAll(prevBtn, spacer, nextBtn);
        return footer;
    }

    public Node getRoot() {
        return getView();
    }
}