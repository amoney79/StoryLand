package utils;

import javafx.scene.Scene;

public class ThemeManager {
    private static boolean darkMode = false;

    public static void applyTheme(Scene scene) {
        if (scene == null) return;
        scene.getStylesheets().remove("/styles/light-theme.css");
        scene.getStylesheets().remove("/styles/dark-theme.css");
        
        if (darkMode) {
            scene.getStylesheets().add(ThemeManager.class.getResource("/styles/dark-theme.css").toExternalForm());
        } else {
            scene.getStylesheets().add(ThemeManager.class.getResource("/styles/light-theme.css").toExternalForm());
        }
    }

    public static void toggleTheme() {
        darkMode = !darkMode;
    }

    public static void toggleDarkModeAndRefresh(Scene scene) {
        darkMode = !darkMode;
        applyTheme(scene);
    }

    public static String getCurrentTheme() {
        return darkMode ? "dark" : "light";
    }

    public static boolean isDarkMode() {
        return darkMode;
    }
}
