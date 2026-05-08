package UI;

import Models.Novel;
import Models.User;
import db.NovelDAO;
import db.UserDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import utils.ExploreScreenHelper;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class NovelAdminScreen implements ProfileSettingsScreen.SceneAware {
    private final User currentUser;
    private final UserDAO userDAO = new UserDAO();
    private final NovelDAO novelDAO = new NovelDAO();
    private BorderPane root;

    public NovelAdminScreen(User user) {
        this.currentUser = user;
        initializeUI();
    }

    private void initializeUI() {
        if (currentUser == null || !"admin".equalsIgnoreCase(currentUser.getRole())) {
            root = new BorderPane(new Label("❌ Access Denied: Admins only."));
            return;
        }

        TabPane tabs = new TabPane();
        tabs.getTabs().add(new Tab("➕ Add Novel", createAddNovelForm()));
        tabs.getTabs().add(new Tab("✏️ Update Novel", createUpdateNovelForm()));
        tabs.getTabs().add(new Tab("🗑 Delete Novel", createDeleteNovelForm()));
        tabs.getTabs().add(new Tab("📄 Add Chapter", createAddChapterForm()));
        tabs.getTabs().add(new Tab("🧹 Delete Chapter", createDeleteChapterForm()));
        tabs.getTabs().add(new Tab("🛡 Update User Role", createUpdateUserRoleForm()));

        root = new BorderPane();
        root.setCenter(tabs);
    }

    @Override
    public Node getContent() {
        return root;
    }

    // === FORM METHODS ===

    private Node createAddNovelForm() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(10));

        TextField searchField = new TextField("Search novels...");
        ListView<String> listView = createNovelListView(searchField);

        TextField titleField = new TextField();
        titleField.setPromptText("Novel Title");

        TextArea descField = new TextArea();
        descField.setPromptText("Description");

        ComboBox<String> genreCombo = new ComboBox<>();
        genreCombo.getItems().addAll("All", "Action", "Romance", "Fantasy", "Sci-Fi", "Horror", "Drama", "Comedy");
        genreCombo.setPromptText("Select genre");

        ComboBox<String> ageCombo = new ComboBox<>();
        ageCombo.getItems().addAll("All", "Children", "Teen", "Adult");
        ageCombo.setPromptText("Select age bracket");

        HBox coverBox = new HBox(5);
        TextField coverField = new TextField();
        coverField.setPromptText("Cover Image Path");
        Button browseBtn = new Button("Browse");
        browseBtn.setOnAction(e -> chooseFile(coverField));
        coverBox.getChildren().addAll(coverField, browseBtn);

        Button addBtn = new Button("Add Novel");
        addBtn.setOnAction(e -> {
            String genre = genreCombo.getValue();
            String age = ageCombo.getValue();
            if (genre == null || age == null) {
                showAlert("⚠️ Please select genre and age bracket.");
                return;
            }

            Novel novel = new Novel();
            novel.setUserId(currentUser.getId());
            novel.setTitle(titleField.getText());
            novel.setDescription(descField.getText());
            novel.setCoverImagePath(coverField.getText());
            novel.setGenre(genre);
            novel.setAgeBracket(age);

            boolean success = novelDAO.addNovel(novel);
            showAlert(success ? "✅ Novel added!" : "❌ Failed to add.");
            refreshListView(listView);
        });

        box.getChildren().addAll(
            new Label("➕ Add Novel"),
            searchField, listView,
            titleField, descField,
            new Label("Genre:"), genreCombo,
            new Label("Age Bracket:"), ageCombo,
            coverBox, addBtn
        );
        return box;
    }

    private Node createUpdateNovelForm() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(10));

        TextField searchField = new TextField("Search novels...");
        ListView<String> listView = createNovelListView(searchField);

        TextField idField = new TextField();
        idField.setPromptText("Novel ID");

        TextField titleField = new TextField();
        titleField.setPromptText("New Title");

        TextArea descField = new TextArea();
        descField.setPromptText("New Description");

        HBox coverBox = new HBox(5);
        TextField coverField = new TextField();
        coverField.setPromptText("New Cover Path");
        Button browseBtn = new Button("Browse");
        browseBtn.setOnAction(e -> chooseFile(coverField));
        coverBox.getChildren().addAll(coverField, browseBtn);

        Button updateBtn = new Button("Update Novel");
        updateBtn.setOnAction(e -> {
            Novel novel = new Novel();
            novel.setId(Integer.parseInt(idField.getText()));
            novel.setTitle(titleField.getText());
            novel.setDescription(descField.getText());
            novel.setCoverImagePath(coverField.getText());

            boolean success = novelDAO.updateNovel(novel);
            showAlert(success ? "✅ Updated!" : "❌ Update failed.");
            refreshListView(listView);
        });

        box.getChildren().addAll(
            new Label("✏️ Update Novel"),
            searchField, listView,
            idField, titleField, descField, coverBox, updateBtn
        );
        return box;
    }

    private Node createDeleteNovelForm() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(10));

        TextField searchField = new TextField("Search novels...");
        ListView<String> listView = createNovelListView(searchField);

        TextField idField = new TextField();
        idField.setPromptText("Novel ID");

        Button deleteBtn = new Button("Delete Novel");
        deleteBtn.setOnAction(e -> {
            boolean success = novelDAO.deleteNovel(Integer.parseInt(idField.getText()));
            showAlert(success ? "✅ Deleted!" : "❌ Failed to delete.");
            refreshListView(listView);
        });

        box.getChildren().addAll(new Label("🗑 Delete Novel"), searchField, listView, idField, deleteBtn);
        return box;
    }

    private Node createAddChapterForm() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(10));

        TextField searchField = new TextField("Search novels...");
        ListView<String> listView = createNovelListView(searchField);

        TextField novelIdField = new TextField();
        novelIdField.setPromptText("Novel ID");

        TextField titleField = new TextField();
        titleField.setPromptText("Chapter Title");

        TextArea contentField = new TextArea();
        contentField.setPromptText("Chapter Content");

        Button addBtn = new Button("Add Chapter");
        addBtn.setOnAction(e -> {
            boolean success = novelDAO.addChapter(
                    Integer.parseInt(novelIdField.getText()),
                    titleField.getText(),
                    contentField.getText());
            showAlert(success ? "✅ Chapter added!" : "❌ Failed to add chapter.");
        });

        box.getChildren().addAll(new Label("📄 Add Chapter"), searchField, listView, novelIdField, titleField, contentField, addBtn);
        return box;
    }

    private Node createDeleteChapterForm() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(10));

        TextField searchField = new TextField("Search novels...");
        ListView<String> listView = createNovelListView(searchField);

        TextField chapterIdField = new TextField();
        chapterIdField.setPromptText("Chapter ID");

        Button deleteBtn = new Button("Delete Chapter");
        deleteBtn.setOnAction(e -> {
            boolean success = novelDAO.deleteChapter(Integer.parseInt(chapterIdField.getText()));
            showAlert(success ? "✅ Chapter deleted!" : "❌ Failed to delete.");
        });

        box.getChildren().addAll(new Label("🧹 Delete Chapter"), searchField, listView, chapterIdField, deleteBtn);
        return box;
    }

    private Node createUpdateUserRoleForm() {
        VBox box = new VBox(10);
        box.setPadding(new Insets(10));

        Label title = new Label("🛡️ Update User Role");

        TextField txtUserId = new TextField();
        txtUserId.setPromptText("User ID");

        ComboBox<String> cmbRole = new ComboBox<>();
        cmbRole.getItems().addAll("admin", "user");

        Button btnUpdate = new Button("Update Role");
        Label lblStatus = new Label();

        ListView<String> userListView = new ListView<>();
        refreshUserList(userListView);

        btnUpdate.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtUserId.getText());
                String newRole = cmbRole.getValue();
                if (newRole == null) {
                    lblStatus.setText("⚠️ Select a role.");
                    return;
                }
                boolean success = userDAO.updateUserRole(id, newRole);
                lblStatus.setText(success ? "✅ Updated!" : "❌ Failed.");
                refreshUserList(userListView);
            } catch (NumberFormatException ex) {
                lblStatus.setText("⚠️ Invalid ID.");
            }
        });

        userListView.setOnMouseClicked(e -> {
            String selected = userListView.getSelectionModel().getSelectedItem();
            if (selected != null && selected.contains("ID:")) {
                String[] parts = selected.split("\\|");
                txtUserId.setText(parts[0].replace("ID:", "").trim());
                cmbRole.setValue(parts[2].replace("Role:", "").trim());
            }
        });

        box.getChildren().addAll(title, txtUserId, cmbRole, btnUpdate, lblStatus, new Label("👥 All Users:"), userListView);
        return box;
    }

    private ListView<String> createNovelListView(TextField searchField) {
        ListView<String> listView = new ListView<>();
        refreshListView(listView);

        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            List<Novel> filtered = novelDAO.getAllNovels().stream()
                    .filter(n -> n.getTitle().toLowerCase().contains(newVal.toLowerCase()))
                    .collect(Collectors.toList());
            listView.getItems().setAll(filtered.stream()
                    .map(n -> "ID:" + n.getId() + " | " + n.getTitle())
                    .collect(Collectors.toList()));
        });

        return listView;
    }

    private void chooseFile(TextField targetField) {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        if (file != null) {
            targetField.setText(file.getAbsolutePath());
        }
    }

    private void refreshListView(ListView<String> listView) {
        listView.getItems().setAll(novelDAO.getAllNovels().stream()
                .map(n -> "ID:" + n.getId() + " | " + n.getTitle())
                .collect(Collectors.toList()));
    }

    private void refreshUserList(ListView<String> listView) {
        listView.getItems().setAll(UserDAO.getAllUsers().stream()
                .map(u -> "ID:" + u.getId() + " | Username:" + u.getUsername() + " | Role:" + u.getRole())
                .collect(Collectors.toList()));
    }

    private void showAlert(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }
}