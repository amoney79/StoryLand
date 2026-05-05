import javafx.application.Application;
import javafx.stage.Stage;
import UI.LoginPage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Initializing the application with the Login Page
        LoginPage loginPage = new LoginPage();
        loginPage.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
