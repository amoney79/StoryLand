import javafx.application.Application;
import javafx.stage.Stage;
import UI.LoginScreen;
import utils.ScreenManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Initialize Screen Manager
        ScreenManager.setPrimaryStage(primaryStage);
        
        // Add screens
        ScreenManager.addScreen("LoginScreen", new LoginScreen().getRoot());
        ScreenManager.addScreen("RegisterScreen", new RegisterScreen().getRoot());
        
        // Set window title and show initial screen
        primaryStage.setTitle("NovelUp - Login");
        ScreenManager.showScreen("LoginScreen");
        
        primaryStage.setWidth(1100);
        primaryStage.setHeight(800);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
