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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInSignUp extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signInButton, signUpButton;

    public SignInSignUp() {
        setTitle("Sign In / Sign Up");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        // UI Components
        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        signInButton = new JButton("Sign In");
        signUpButton = new JButton("Sign Up");

        add(signInButton);
        add(signUpButton);

        // Action Listeners
        signInButton.addActionListener(e -> signIn());
        signUpButton.addActionListener(e -> signUp());

        setVisible(true);
    }

    private void signIn() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        // Placeholder authentication check
        if (username.equals("user") && password.equals("pass")) {
            JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void signUp() {
        JOptionPane.showMessageDialog(this, "Redirecting to Sign Up Page...", "Sign Up", JOptionPane.INFORMATION_MESSAGE);
        // Implement sign-up logic or open a new JFrame for registration
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignInSignUp::new);
    }
}

