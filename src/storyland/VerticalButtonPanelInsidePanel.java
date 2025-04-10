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
import javax.swing.*;
import java.awt.*;

public class VerticalButtonPanelInsidePanel extends JFrame {
    public VerticalButtonPanelInsidePanel() {
        setTitle("Button Panel Inside Panel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main container panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Button panel (Vertically aligned)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Create buttons
        JButton button1 = new JButton("Rate us");
         button1.setBorderPainted(false);
        button1.setContentAreaFilled(false); // Removes background fill
        button1.setFocusPainted(false); // Removes focus border
        button1.setOpaque(false); // Makes it fully transparent
        JButton button2 = new JButton("Downloads");
         button2.setBorderPainted(false);
        button2.setContentAreaFilled(false); // Removes background fill
        button2.setFocusPainted(false); // Removes focus border
        button2.setOpaque(false); // Makes it fully transparent
        JButton button3 = new JButton("Help & Feedback");
         button3.setBorderPainted(false);
        button3.setContentAreaFilled(false); // Removes background fill
        button3.setFocusPainted(false); // Removes focus border
        button3.setOpaque(false); // Makes it fully transparent

        // Align buttons and add spacing
        button1.setAlignmentX(Component.LEFT_ALIGNMENT);
        button2.setAlignmentX(Component.LEFT_ALIGNMENT);
        button3.setAlignmentX(Component.LEFT_ALIGNMENT);

        buttonPanel.add(button1);
        buttonPanel.add(Box.createVerticalStrut(10)); // Space between buttons
        buttonPanel.add(button2);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(button3);

        // Add buttonPanel to mainPanel
        JPanel containerPanel = new JPanel();  // Parent panel
        containerPanel.setLayout(new BorderLayout());
        containerPanel.add(buttonPanel, BorderLayout.WEST);

        mainPanel.add(containerPanel, BorderLayout.CENTER);

        // Add to frame
        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VerticalButtonPanelInsidePanel::new);
    }
}

