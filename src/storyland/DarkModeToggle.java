/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storyland;

/**
 *
 * @author macke
 */
import java.awt.*;
import javax.swing.*;

public class DarkModeToggle extends JFrame {
    private JPanel panel;
    private JToggleButton toggleButton;

    public DarkModeToggle() {
        // Initialize components
        panel = new JPanel();
        toggleButton = new JToggleButton("Dark Mode OFF");

        // Set default light mode colors
        panel.setBackground(Color.WHITE);
        toggleButton.setBackground(Color.LIGHT_GRAY);
        toggleButton.setForeground(Color.BLACK);

        // Toggle button action listener
        toggleButton.addActionListener(e -> toggleDarkMode());

        // Layout settings
        panel.add(toggleButton);
        add(panel);
        
        setTitle("Dark Mode Toggle");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void toggleDarkMode() {
        if (toggleButton.isSelected()) {
            // Apply Dark Mode
            panel.setBackground(Color.DARK_GRAY);
            toggleButton.setBackground(Color.BLACK);
            toggleButton.setForeground(Color.WHITE);
            toggleButton.setText("Dark Mode ON");
        } else {
            // Apply Light Mode
            panel.setBackground(Color.WHITE);
            toggleButton.setBackground(Color.LIGHT_GRAY);
            toggleButton.setForeground(Color.BLACK);
            toggleButton.setText("Dark Mode OFF");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DarkModeToggle().setVisible(true));
    }
}
