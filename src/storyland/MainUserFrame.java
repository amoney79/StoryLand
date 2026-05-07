package storyland;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


/**
 * Main application frame for StoryLand.
 * Implements the UI seen in the app screenshots.
 */
public class MainUserFrame extends JFrame {
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private Color primaryColor = new Color(0, 153, 204); // A nice blue for accents
    private Color backgroundColor = Color.WHITE;


    public MainUserFrame() {
        setTitle("StoryLand");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());

        // Main content area with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        
        contentPanel.add(createLibraryPanel(), "Library");
        contentPanel.add(createExplorePanel(), "Explore");
        contentPanel.add(createGenresPanel(), "Genres");
        contentPanel.add(createMePanel(), "Me");

        add(contentPanel, BorderLayout.CENTER);

        // Bottom Navigation Bar
        add(createBottomNav(), BorderLayout.SOUTH);

        // Show Explore by default as in the screenshot
        cardLayout.show(contentPanel, "Explore");
    }

    private JPanel createExplorePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(backgroundColor);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Top Header Section
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(backgroundColor);

        JLabel titleLabel = new JLabel("Explore by Genre");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(10));

        // Search Bar
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(800, 35));
        searchField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        searchField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(primaryColor, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        searchField.setAlignmentX(Component.LEFT_ALIGNMENT);
        headerPanel.add(searchField);
        headerPanel.add(Box.createVerticalStrut(15));

        // Genre Buttons
        JPanel genrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        genrePanel.setBackground(backgroundColor);
        genrePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        String[] genres = {"Action", "All", "Comedy", "Fantasy", "Horror", "Romance", "Sci-Fi"};
        for (String genre : genres) {
            JButton btn = createStyledButton(genre);
            genrePanel.add(btn);
        }
        headerPanel.add(genrePanel);
        headerPanel.add(Box.createVerticalStrut(20));

        panel.add(headerPanel, BorderLayout.NORTH);

        // Stories Grid
        JPanel gridPanel = new JPanel(new GridLayout(0, 5, 20, 30));
        gridPanel.setBackground(backgroundColor);
        gridPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        // Mock Stories
        addStoryCard(gridPanel, "Dr. Love", "Love Mister", "4.5", "12k");
        addStoryCard(gridPanel, "Breaking chains", "Author name", "4.8", "8k");
        addStoryCard(gridPanel, "My Life", "Author name", "4.2", "5k");
        addStoryCard(gridPanel, "Introvert Girl", "Author name", "4.7", "15k");
        addStoryCard(gridPanel, "MoonLit", "Author name", "4.9", "2k");
        addStoryCard(gridPanel, "Computer Guru", "Author name", "4.5", "1k");
        addStoryCard(gridPanel, "Life in the village", "Author name", "4.3", "3k");
        addStoryCard(gridPanel, "A lone Pair", "Author name", "4.6", "9k");
        addStoryCard(gridPanel, "knowledge", "Author name", "4.1", "4k");

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void addStoryCard(JPanel grid, String title, String author, String rating, String views) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(backgroundColor);

        // Mock Image Placeholder
        JPanel imagePlaceholder = new JPanel();
        imagePlaceholder.setPreferredSize(new Dimension(150, 200));
        imagePlaceholder.setBackground(new Color(230, 230, 230));
        imagePlaceholder.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        // In a real app, you'd add an image icon here
        
        JLabel titleLbl = new JLabel(title);
        titleLbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        JLabel authorLbl = new JLabel(author);
        authorLbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        authorLbl.setForeground(Color.GRAY);

        JLabel infoLbl = new JLabel("★ " + rating + "  👁 " + views);
        infoLbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        infoLbl.setForeground(Color.DARK_GRAY);

        card.add(imagePlaceholder);
        card.add(Box.createVerticalStrut(5));
        card.add(titleLbl);
        card.add(authorLbl);
        card.add(infoLbl);

        grid.add(card);
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        return btn;
    }

    private JPanel createBottomNav() {
        JPanel nav = new JPanel(new GridLayout(1, 4));
        nav.setBackground(new Color(245, 245, 245));
        nav.setPreferredSize(new Dimension(1000, 70));
        nav.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 220, 220)));

        nav.add(createNavButton("Library", "Library", "/Images/books.png"));
        nav.add(createNavButton("Explore", "Explore", "/Images/saturn.png"));
        nav.add(createNavButton("Genres", "Genres", "/Images/list.png"));
        nav.add(createNavButton("Me", "Me", "/Images/user.png"));

        return nav;
    }

    private JButton createNavButton(String text, String cardName, String iconPath) {
        ImageIcon icon = null;
        try {
            icon = new ImageIcon(getClass().getResource(iconPath));
        } catch (Exception e) {
            System.err.println("Could not find icon: " + iconPath);
        }
        
        JButton btn = new JButton(text, icon);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setForeground(Color.DARK_GRAY);
        
        btn.addActionListener(e -> cardLayout.show(contentPanel, cardName));
        
        return btn;
    }

    private JPanel createLibraryPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(backgroundColor);
        p.add(new JLabel("Your Library will appear here."));
        return p;
    }

    private JPanel createGenresPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(backgroundColor);
        p.add(new JLabel("Browse stories by category."));
        return p;
    }

    private JPanel createMePanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(backgroundColor);
        p.setBorder(new EmptyBorder(50, 50, 50, 50));

        JLabel title = new JLabel("Account Settings");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        p.add(title);
        p.add(Box.createVerticalStrut(30));

        JToggleButton darkToggle = new JToggleButton("Dark Mode: OFF");
        darkToggle.setAlignmentX(Component.LEFT_ALIGNMENT);
        darkToggle.addActionListener(e -> {
            if (darkToggle.isSelected()) {
                applyDarkMode();
                darkToggle.setText("Dark Mode: ON");
            } else {
                applyLightMode();
                darkToggle.setText("Dark Mode: OFF");
            }
        });
        p.add(darkToggle);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        logoutBtn.addActionListener(e -> {
            new SignInSignUp().setVisible(true);
            this.dispose();
        });
        p.add(Box.createVerticalStrut(20));
        p.add(logoutBtn);

        return p;
    }

    private void applyDarkMode() {
        backgroundColor = Color.DARK_GRAY;
        textColor = Color.WHITE;
        updateUIThemes();
    }

    private void applyLightMode() {
        backgroundColor = Color.WHITE;
        textColor = Color.BLACK;
        updateUIThemes();
    }

    private void updateUIThemes() {
        contentPanel.setBackground(backgroundColor);
        // This is a simplified theme update. In a real app, you'd iterate through components.
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainUserFrame().setVisible(true));
    }
}
