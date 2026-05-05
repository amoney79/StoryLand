package utils;

import javafx.scene.Scene;

public class ThemeManager {
    private static boolean darkMode = false;

    public static void applyTheme(Scene scene) {
        if (darkMode) {
            // Apply dark theme CSS
            // scene.getStylesheets().add("dark-theme.css");
        } else {
            // Apply light theme CSS
        }
    }

    public static void toggleTheme() {
        darkMode = !darkMode;
    }

    public static boolean isDarkMode() {
        return darkMode;
    }
}
