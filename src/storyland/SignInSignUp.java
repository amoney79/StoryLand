package storyland;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SignInSignUp extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField visiblePasswordField;

    private JButton signInButton;
    private JButton signUpButton;
    private JButton googleButton;
    private JButton appleButton;
    private JButton togglePasswordButton;

    private JLabel messageLabel;
    private JLabel capsLockLabel;
    private JLabel profileNameLabel;
    private JLabel profilePicLabel;

    private JProgressBar loadingBar;

    private final List<JComponent> focusables = new ArrayList<>();
    private int focusIndex = 0;

    public SignInSignUp() {
        setTitle("NovelUp Login");
        setSize(460, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(new Color(245, 247, 250));

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(24, 28, 24, 28));
        card.setBackground(Color.WHITE);

        JLabel title = new JLabel("Login to NovelUp");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(title);
        card.add(Box.createVerticalStrut(18));

        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        usernameField.setPreferredSize(new Dimension(320, 38));
        usernameField.setBorder(BorderFactory.createTitledBorder("Email or Username"));

        passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        visiblePasswordField = new JTextField();
        visiblePasswordField.setBorder(BorderFactory.createTitledBorder("Password"));
        visiblePasswordField.setVisible(false);

        togglePasswordButton = new JButton("👁");
        togglePasswordButton.setFocusable(false);

        JPanel passwordPanel = new JPanel(new BorderLayout(6, 0));
        passwordPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        passwordPanel.setBackground(Color.WHITE);
        passwordPanel.add(passwordField, BorderLayout.CENTER);
        passwordPanel.add(togglePasswordButton, BorderLayout.EAST);

        capsLockLabel = new JLabel("Caps Lock is ON");
        capsLockLabel.setForeground(Color.RED);
        capsLockLabel.setVisible(false);

        signInButton = createPrimaryButton("Login", new Color(59, 130, 246));
        signUpButton = createPrimaryButton("Go to Register", new Color(16, 185, 129));

        googleButton = createSecondaryButton("Continue with Google");
        appleButton = createSecondaryButton("Continue with Apple ID");

        profilePicLabel = new JLabel();
        profilePicLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        profilePicLabel.setVisible(false);

        profileNameLabel = new JLabel();
        profileNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileNameLabel.setVisible(false);

        loadingBar = new JProgressBar();
        loadingBar.setIndeterminate(true);
        loadingBar.setVisible(false);

        messageLabel = new JLabel(" ");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(usernameField);
        card.add(Box.createVerticalStrut(10));
        card.add(passwordPanel);
        card.add(Box.createVerticalStrut(4));
        card.add(capsLockLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(signInButton);
        card.add(Box.createVerticalStrut(8));
        card.add(signUpButton);
        card.add(Box.createVerticalStrut(10));
        card.add(loadingBar);
        card.add(Box.createVerticalStrut(12));
        card.add(new JSeparator());
        card.add(Box.createVerticalStrut(12));
        card.add(googleButton);
        card.add(Box.createVerticalStrut(8));
        card.add(appleButton);
        card.add(Box.createVerticalStrut(14));
        card.add(profilePicLabel);
        card.add(Box.createVerticalStrut(4));
        card.add(profileNameLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(messageLabel);

        root.add(card, BorderLayout.CENTER);
        setContentPane(root);

        focusables.add(usernameField);
        focusables.add(passwordField);
        focusables.add(signInButton);
        focusables.add(signUpButton);
        focusables.add(googleButton);
        focusables.add(appleButton);

        togglePasswordButton.addActionListener(e -> togglePassword());

        signInButton.addActionListener(e -> signIn());

        signUpButton.addActionListener(e -> signUp());

        googleButton.addActionListener(e ->
                socialLogin(
                        "John Doe",
                        "johndoe@gmail.com",
                        "https://i.pravatar.cc/100?img=3"
                )
        );

        appleButton.addActionListener(e ->
                socialLogin(
                        "Alice Smith",
                        "alice@icloud.com",
                        "https://i.pravatar.cc/100?img=5"
                )
        );

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                checkCapsLock(e);
            }
        });

        visiblePasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                checkCapsLock(e);
            }
        });

        setupKeyboardNavigation();

        setVisible(true);
    }

    private JButton createPrimaryButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        return btn;
    }

    private JButton createSecondaryButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(241, 245, 249));
        btn.setFocusPainted(false);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        return btn;
    }

    private void togglePassword() {
        if (visiblePasswordField.isVisible()) {
            passwordField.setText(visiblePasswordField.getText());
            visiblePasswordField.setVisible(false);
            passwordField.setVisible(true);
            passwordField.requestFocus();
        } else {
            visiblePasswordField.setText(new String(passwordField.getPassword()));
            visiblePasswordField.setVisible(true);
            passwordField.setVisible(false);
            visiblePasswordField.requestFocus();
        }

        revalidate();
        repaint();
    }

    private void signIn() {
        loadingBar.setVisible(true);
        messageLabel.setText(" ");

        Timer timer = new Timer(1000, e -> {
            loadingBar.setVisible(false);

            String username = usernameField.getText();
            String password = getPasswordValue();

            if (username.equals("user") && password.equals("pass")) {
                messageLabel.setForeground(new Color(34, 197, 94));
                messageLabel.setText("Login successful!");

                openMainFrame(username, null);

            } else {
                messageLabel.setForeground(Color.RED);
                messageLabel.setText("Invalid credentials");
                shakeWindow();
            }
        });

        timer.setRepeats(false);
        timer.start();
    }

    private void socialLogin(String name, String email, String imageUrl) {
        setSocialProfile(name, imageUrl);

        messageLabel.setForeground(new Color(34, 197, 94));
        messageLabel.setText("Login successful!");

        openMainFrame(name, imageUrl);
    }

    private void setSocialProfile(String name, String imageUrl) {
        profileNameLabel.setText(name);
        profileNameLabel.setVisible(true);

        try {
            ImageIcon icon = new ImageIcon(URI.create(imageUrl).toURL());
            Image scaled = icon.getImage().getScaledInstance(52, 52, Image.SCALE_SMOOTH);
            profilePicLabel.setIcon(new ImageIcon(scaled));
            profilePicLabel.setVisible(true);
        } catch (Exception ignored) {
        }
    }

    private void signUp() {
        JOptionPane.showMessageDialog(
                this,
                "Redirecting to Sign Up Page...",
                "Sign Up",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void openMainFrame(String username, String profileImageUrl) {
        MainUserFrame frame = new MainUserFrame();

        // Optional if MainUserFrame supports it
        // frame.setUser(username, profileImageUrl);

        frame.setVisible(true);
        dispose();
    }

    private void checkCapsLock(KeyEvent e) {
        char c = e.getKeyChar();

        if (!Character.isLetter(c)) {
            return;
        }

        boolean capsOn = Character.isUpperCase(c) && !e.isShiftDown();
        capsLockLabel.setVisible(capsOn);
    }

    private String getPasswordValue() {
        if (visiblePasswordField.isVisible()) {
            return visiblePasswordField.getText();
        }
        return new String(passwordField.getPassword());
    }

    private void setupKeyboardNavigation() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addKeyEventDispatcher(e -> {

                    if (e.getID() != KeyEvent.KEY_PRESSED) {
                        return false;
                    }

                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        focusNext();
                        return true;
                    }

                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        focusPrevious();
                        return true;
                    }

                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        Component focused = KeyboardFocusManager
                                .getCurrentKeyboardFocusManager()
                                .getFocusOwner();

                        if (focused instanceof JButton) {
                            ((JButton) focused).doClick();
                            return true;
                        }
                    }

                    return false;
                });
    }

    private void focusNext() {
        focusIndex = (focusIndex + 1) % focusables.size();
        focusables.get(focusIndex).requestFocus();
    }

    private void focusPrevious() {
        focusIndex = (focusIndex - 1 + focusables.size()) % focusables.size();
        focusables.get(focusIndex).requestFocus();
    }

    private void shakeWindow() {
        Point p = getLocation();

        Timer timer = new Timer(25, null);

        timer.addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                setLocation(
                        p.x + ((count % 2 == 0) ? 8 : -8),
                        p.y
                );

                count++;

                if (count >= 6) {
                    timer.stop();
                    setLocation(p);
                }
            }
        });

        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignInSignUp::new);
    }
}