package storyland;

import javax.swing.SwingUtilities;

public class StoryLand {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SignInSignUp().setVisible(true);
        });
    }
}
