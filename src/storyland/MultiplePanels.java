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

public class MultiplePanels extends JFrame {
    private JPanel mainPanel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5 ;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JPanel buttonPanel,buttonPanel1, buttonPanel2,buttonPanel3,buttonPanel4,buttonPanel5,buttonPanel6, contentPanel ;
    private JTabbedPane tabbedPane ;
    private JPanel panel6,panel7,panel8,panel9 ;
    private JButton button10,button11,button12,button13;
    private JButton button14,button15,button16,button17;
    private JButton button18,button19,button20,button21;
    private JButton button22,button23,button24,button25;
    private JButton button26,button27,button28,button29;
    private JButton button30,button31,button32,button33;
    private JButton button34,button35,button36,button37;
    private JToggleButton toggleButton ;
   
   

    public  MultiplePanels() {
        setTitle("StoryLand");
        setSize(400, 300);
        
        
        try {
            // Set Windows Look and Feel
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new CardLayout());
       
        contentPanel = new JPanel(new BorderLayout());
        
        
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/images/books.png"));
        ImageIcon icon2 = new ImageIcon(getClass().getResource("/images/saturn.png"));
        ImageIcon icon3 = new ImageIcon(getClass().getResource("/images/abstract.png"));
        ImageIcon icon4 = new ImageIcon(getClass().getResource("/images/user.png"));
        ImageIcon icon5 = new ImageIcon(getClass().getResource("/images/list.png")) ;
        
        
        // Create panels
        panel1 = new JPanel();
        panel1.setBackground(Color.BLACK) ;
        panel2 = new JPanel();
        panel2.add(new JLabel("Explore"));
        panel2.setBackground(Color.blue) ;
        panel3 = new JPanel();
        panel3.add(new JLabel("Genre"));
        panel3.setBackground(Color.CYAN) ;
        panel4 = new JPanel(new BorderLayout());
        panel4.add(new JLabel("Me"));
        panel4.setBackground(Color.darkGray) ;
        panel5 = new JPanel() ;
        panel5.setBackground(Color.cyan) ;
        panel6 = new JPanel();
        panel6.add(new JLabel("All"));
        panel6.setBackground(Color.BLUE);
        panel7 = new JPanel();
        panel7.add(new JLabel("Unread"));
        panel7.setBackground(Color.RED);
        panel8 = new JPanel();
        panel8.add(new JLabel("Read"));
        panel8.setBackground(Color.MAGENTA);
        panel9 = new JPanel();
        panel9.add(new JLabel("Finished"));
        panel9.setBackground(Color.GRAY);
        
         // Add panels to mainPanel
        mainPanel.add(panel1, "Panel1");
        mainPanel.add(panel2, "Panel2");
        mainPanel.add(panel3, "Panel3");
        mainPanel.add(panel4, "Panel4");
        mainPanel.add(panel5, "Panel5");
        mainPanel.add(panel6, "Panel6");
        mainPanel.add(panel7, "Panel7");
        mainPanel.add(panel8, "Panel8");
        mainPanel.add(panel9, "Panel9");
        
        
         //Library
        // Panel with Buttons (Top Panel)
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align buttons in the center
        buttonPanel.setPreferredSize(new Dimension(1200, 50)); // Small height
        button5 = new JButton("Library");
        button5.setForeground(Color.WHITE); // Sets text color to red
        button5.setBorderPainted(false);
        button5.setContentAreaFilled(false); // Removes background fill
        button5.setFocusPainted(false); // Removes focus border
        button5.setOpaque(false); // Makes it fully transparent
        button6 = new JButton("History");
        button6.setForeground(Color.WHITE); // Sets text color to red
        button6.setBorderPainted(false);
        button6.setContentAreaFilled(false); // Removes background fill
        button6.setFocusPainted(false); // Removes focus border
        button6.setOpaque(false); // Makes it fully transparent
        button9 = new JButton(icon5) ;
        button9.setForeground(Color.WHITE); // Sets text color to white
        button9.setBorderPainted(false);
        button9.setContentAreaFilled(false); // Removes background fill
        button9.setFocusPainted(false); // Removes focus border
        button9.setOpaque(true); // Makes it fully transparent
        
        // "All", "Unread", "Read", "Finished" button panel (Below Library and History buttons)
        buttonPanel5 = new JPanel();
        buttonPanel5.setBackground(Color.BLACK) ;
        buttonPanel5.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel5.setPreferredSize(new Dimension(1500, 50));
        button10 = new JButton("All");
        button10.setForeground(Color.WHITE); // Sets text color to red
        button10.setBorderPainted(false);
        button10.setContentAreaFilled(false); // Removes background fill
        button10.setFocusPainted(false); // Removes focus border
        button10.setOpaque(false); // Makes it fully transparent
        button11 = new JButton("Unread");
        button11.setForeground(Color.WHITE); // Sets text color to red
        button11.setBorderPainted(false);
        button11.setContentAreaFilled(false); // Removes background fill
        button11.setFocusPainted(false); // Removes focus border
        button11.setOpaque(false); // Makes it fully transparent
        button12 = new JButton("Read");
        button12.setForeground(Color.WHITE); // Sets text color to red
        button12.setBorderPainted(false);
        button12.setContentAreaFilled(false); // Removes background fill
        button12.setFocusPainted(false); // Removes focus border
        button12.setOpaque(false); // Makes it fully transparent
        button13 = new JButton("Finished");
        button13.setForeground(Color.WHITE); // Sets text color to red
        button13.setBorderPainted(false);
        button13.setContentAreaFilled(false); // Removes background fill
        button13.setFocusPainted(false); // Removes focus border
        button13.setOpaque(false); // Makes it fully transparent
        
       
        
        //panel 6 - 9
        // "All", "Unread", "Read", "Finished" button panel (Below Library and History buttons)
        buttonPanel1 = new JPanel();
        buttonPanel1.setBackground(Color.BLACK) ;
        buttonPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel1.setPreferredSize(new Dimension(1500, 50));
        button14 = new JButton("All");
        button14.setForeground(Color.WHITE); // Sets text color to red
        button14.setBorderPainted(false);
        button14.setContentAreaFilled(false); // Removes background fill
        button14.setFocusPainted(false); // Removes focus border
        button14.setOpaque(false); // Makes it fully transparent
        button15 = new JButton("Unread");
        button15.setForeground(Color.WHITE); // Sets text color to red
        button15.setBorderPainted(false);
        button15.setContentAreaFilled(false); // Removes background fill
        button15.setFocusPainted(false); // Removes focus border
        button15.setOpaque(false); // Makes it fully transparent
        button16 = new JButton("Read");
        button16.setForeground(Color.WHITE); // Sets text color to red
        button16.setBorderPainted(false);
        button16.setContentAreaFilled(false); // Removes background fill
        button16.setFocusPainted(false); // Removes focus border
        button16.setOpaque(false); // Makes it fully transparent
        button17 = new JButton("Finished");
        button17.setForeground(Color.WHITE); // Sets text color to red
        button17.setBorderPainted(false);
        button17.setContentAreaFilled(false); // Removes background fill
        button17.setFocusPainted(false); // Removes focus border
        button17.setOpaque(false); // Makes it fully transparent
        
        // "All", "Unread", "Read", "Finished" button panel (Below Library and History buttons)
        buttonPanel2 = new JPanel();
        buttonPanel2.setBackground(Color.BLACK) ;
        buttonPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel2.setPreferredSize(new Dimension(1500, 50));
        button18 = new JButton("All");
        button18.setForeground(Color.WHITE); // Sets text color to white
        button18.setBorderPainted(false);
        button18.setContentAreaFilled(false); // Removes background fill
        button18.setFocusPainted(false); // Removes focus border
        button18.setOpaque(false); // Makes it fully transparent
        button19 = new JButton("Unread");
        button19.setForeground(Color.WHITE); // Sets text color to white
        button19.setBorderPainted(false);
        button19.setContentAreaFilled(false); // Removes background fill
        button19.setFocusPainted(false); // Removes focus border
        button19.setOpaque(false); // Makes it fully transparent
        button20 = new JButton("Read");
        button20.setForeground(Color.WHITE); // Sets text color to white
        button20.setBorderPainted(false);
        button20.setContentAreaFilled(false); // Removes background fill
        button20.setFocusPainted(false); // Removes focus border
        button20.setOpaque(false); // Makes it fully transparent
        button21 = new JButton("Finished");
        button21.setForeground(Color.WHITE); // Sets text color to white
        button21.setBorderPainted(false);
        button21.setContentAreaFilled(false); // Removes background fill
        button21.setFocusPainted(false); // Removes focus border
        button21.setOpaque(false); // Makes it fully transparent
        
        // "All", "Unread", "Read", "Finished" button panel (Below Library and History buttons)
        buttonPanel3 = new JPanel();
        buttonPanel3.setBackground(Color.BLACK) ;
        buttonPanel3.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel3.setPreferredSize(new Dimension(1500, 50));
        button22 = new JButton("All");
        button22.setForeground(Color.WHITE); // Sets text color to white
        button22.setBorderPainted(false);
        button22.setContentAreaFilled(false); // Removes background fill
        button22.setFocusPainted(false); // Removes focus border
        button22.setOpaque(false); // Makes it fully transparent
        button23 = new JButton("Unread");
        button23.setForeground(Color.WHITE); // Sets text color to white
        button23.setBorderPainted(false);
        button23.setContentAreaFilled(false); // Removes background fill
        button23.setFocusPainted(false); // Removes focus border
        button23.setOpaque(false); // Makes it fully transparent
        button24 = new JButton("Read");
        button24.setForeground(Color.WHITE); // Sets text color to white
        button24.setBorderPainted(false);
        button24.setContentAreaFilled(false); // Removes background fill
        button24.setFocusPainted(false); // Removes focus border
        button24.setOpaque(false); // Makes it fully transparent
        button25 = new JButton("Finished");
        button25.setForeground(Color.WHITE); // Sets text color to white
        button25.setBorderPainted(false);
        button25.setContentAreaFilled(false); // Removes background fill
        button25.setFocusPainted(false); // Removes focus border
        button25.setOpaque(false); // Makes it fully transparent
        
        // "All", "Unread", "Read", "Finished" button panel (Below Library and History buttons)
        buttonPanel4 = new JPanel();
        buttonPanel4.setBackground(Color.BLACK) ;
        buttonPanel4.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel4.setPreferredSize(new Dimension(1500, 50));
        button26 = new JButton("All");
        button26.setForeground(Color.WHITE); // Sets text color to white
        button26.setBorderPainted(false);
        button26.setContentAreaFilled(false); // Removes background fill
        button26.setFocusPainted(false); // Removes focus border
        button26.setOpaque(false); // Makes it fully transparent
        button27 = new JButton("Unread");
        button27.setForeground(Color.WHITE); // Sets text color to white
        button27.setBorderPainted(false);
        button27.setContentAreaFilled(false); // Removes background fill
        button27.setFocusPainted(false); // Removes focus border
        button27.setOpaque(false); // Makes it fully transparent
        button28 = new JButton("Read");
        button28.setForeground(Color.WHITE); // Sets text color to white
        button28.setBorderPainted(false);
        button28.setContentAreaFilled(false); // Removes background fill
        button28.setFocusPainted(false); // Removes focus border
        button28.setOpaque(false); // Makes it fully transparent
        button29 = new JButton("Finished");
        button29.setForeground(Color.WHITE); // Sets text color to white
        button29.setBorderPainted(false);
        button29.setContentAreaFilled(false); // Removes background fill
        button29.setFocusPainted(false); // Removes focus border
        button29.setOpaque(false); // Makes it fully transparent
        
        //buttons to Me Panel
        buttonPanel6 = new JPanel();
        buttonPanel6.setBackground(Color.BLACK) ;
        buttonPanel6.setLayout(new BoxLayout(buttonPanel6, BoxLayout.Y_AXIS));
        buttonPanel6.setPreferredSize(new Dimension(1500, 50));
        button30 = new JButton("History");
        button30.setForeground(Color.WHITE); // Sets text color to white
        button30.setBorderPainted(false);
        button30.setContentAreaFilled(false); // Removes background fill
        button30.setFocusPainted(false); // Removes focus border
        button30.setOpaque(false); // Makes it fully transparent
        button31 = new JButton("Notes");
        button31.setForeground(Color.WHITE); // Sets text color to white
        button31.setBorderPainted(false);
        button31.setContentAreaFilled(false); // Removes background fill
        button31.setFocusPainted(false); // Removes focus border
        button31.setOpaque(false); // Makes it fully transparent
        button32 = new JButton("Download");
        button32.setForeground(Color.WHITE); // Sets text color to white
        button32.setBorderPainted(false);
        button32.setContentAreaFilled(false); // Removes background fill
        button32.setFocusPainted(false); // Removes focus border
        button32.setOpaque(false); // Makes it fully transparent
        button33 = new JButton("Reading Preferences");
        button33.setForeground(Color.WHITE); // Sets text color to white
        button33.setBorderPainted(false);
        button33.setContentAreaFilled(false); // Removes background fill
        button33.setFocusPainted(false); // Removes focus border
        button33.setOpaque(false); // Makes it fully transparent
        button34 = new JButton("Notifications");
        button34.setForeground(Color.WHITE); // Sets text color to white
        button34.setBorderPainted(false);
        button34.setContentAreaFilled(false); // Removes background fill
        button34.setFocusPainted(false); // Removes focus border
        button34.setOpaque(false); // Makes it fully transparent
        button35 = new JButton("About");
        button35.setForeground(Color.WHITE); // Sets text color to white
        button35.setBorderPainted(false);
        button35.setContentAreaFilled(false); // Removes background fill
        button35.setFocusPainted(false); // Removes focus border
        button35.setOpaque(false); // Makes it fully transparent
        button36 = new JButton("Help & Feedback");
        button36.setForeground(Color.WHITE); // Sets text color to white
        button36.setBorderPainted(false);
        button36.setContentAreaFilled(false); // Removes background fill
        button36.setFocusPainted(false); // Removes focus border
        button36.setOpaque(false); // Makes it fully transparent
        button37 = new JButton("Rate Us");
        button37.setForeground(Color.WHITE); // Sets text color to white
        button37.setBorderPainted(false);
        button37.setContentAreaFilled(false); // Removes background fill
        button37.setFocusPainted(false); // Removes focus border
        button37.setOpaque(false); // Makes it fully transparent
        toggleButton = new JToggleButton("Dark Mode");
        toggleButton.setForeground(Color.WHITE); // Sets text color to white
        toggleButton.setBorderPainted(false);
        toggleButton.setContentAreaFilled(false); // Removes background fill
        toggleButton.setFocusPainted(false); // Removes focus border
        toggleButton.setOpaque(false); // Makes it fully transparent
        
         // Align buttons and add spacing
        button30.setAlignmentX(Component.LEFT_ALIGNMENT);
        button31.setAlignmentX(Component.LEFT_ALIGNMENT);
        button32.setAlignmentX(Component.LEFT_ALIGNMENT);
        button33.setAlignmentX(Component.LEFT_ALIGNMENT);
        button34.setAlignmentX(Component.LEFT_ALIGNMENT);
        button35.setAlignmentX(Component.LEFT_ALIGNMENT);
        button36.setAlignmentX(Component.LEFT_ALIGNMENT);
        button37.setAlignmentX(Component.LEFT_ALIGNMENT);
        toggleButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        
        //add buttons to the panel
        buttonPanel6.add(button30);
         buttonPanel6.add(Box.createVerticalStrut(20)); // Space between buttons
        buttonPanel6.add(button31);
         buttonPanel6.add(Box.createVerticalStrut(20)); // Space between buttons
        buttonPanel6.add(button32) ;
         buttonPanel6.add(Box.createVerticalStrut(20)); // Space between buttons
        buttonPanel6.add(button33);
         buttonPanel6.add(Box.createVerticalStrut(20)); // Space between buttons
        buttonPanel6.add(button34);
         buttonPanel6.add(Box.createVerticalStrut(20)); // Space between buttons
        buttonPanel6.add(button35) ;
         buttonPanel6.add(Box.createVerticalStrut(20)); // Space between buttons
        buttonPanel6.add(button36);
         buttonPanel6.add(Box.createVerticalStrut(20)); // Space between buttons
        buttonPanel6.add(button37);
         buttonPanel6.add(Box.createVerticalStrut(20)); // Space between buttons
        buttonPanel6.add(toggleButton);
         buttonPanel6.add(Box.createVerticalStrut(20)); // Space between buttons

        // Add buttons to the button panel
        buttonPanel.setBackground(Color.BLACK) ;
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(button9) ;
        buttonPanel1.add(button10);//all
        buttonPanel1.add(button11);//unread
        buttonPanel1.add(button12);//read
        buttonPanel1.add(button13);//finished
        
        // Add buttons to the button panel
        buttonPanel2.setBackground(Color.BLUE) ;
        buttonPanel2.add(button14);//all
        buttonPanel2.add(button15);//unread
        buttonPanel2.add(button16);//read
        buttonPanel2.add(button17);//finished
        
        // Add buttons to the button panel
        buttonPanel3.setBackground(Color.RED) ;
        buttonPanel3.add(button18);//all
        buttonPanel3.add(button19);//unread
        buttonPanel3.add(button20);//read
        buttonPanel3.add(button21);//finished
        
        // Add buttons to the button panel
        buttonPanel4.setBackground(Color.MAGENTA) ;
        buttonPanel4.add(button22);//all
        buttonPanel4.add(button23);//unread
        buttonPanel4.add(button24);//read
        buttonPanel4.add(button25);//finished
        
        // Add buttons to the button panel
        buttonPanel5.setBackground(Color.GRAY) ;
        buttonPanel5.add(button26);//all
        buttonPanel5.add(button27);//unread
        buttonPanel5.add(button28);//read
        buttonPanel5.add(button29);//finished


        // Add panels to panel1
        panel1.add(buttonPanel, BorderLayout.NORTH); // Library & History at the top
        panel1.add(buttonPanel1, BorderLayout.CENTER); // All, Unread, Read, Finished below
        panel1.add(contentPanel, BorderLayout.SOUTH); // Content at the bottom
        panel6.add(buttonPanel2, BorderLayout.NORTH); // Library & History at the top
        panel7.add(buttonPanel3, BorderLayout.NORTH); // Library & History at the top
        panel8.add(buttonPanel4, BorderLayout.NORTH); // Library & History at the top
        panel9.add(buttonPanel5, BorderLayout.NORTH); // Library & History at the top
        panel4.setLayout(new BorderLayout());
        panel4.add(buttonPanel6, BorderLayout.WEST);
        
        
          // Add action listeners
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel1");
            }
        });
        
          // Add action listeners
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel5");
            }
        });
        
          // Panel with Buttons (Top Panel)5
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Align buttons in the center
        buttonPanel.setPreferredSize(new Dimension(1500, 50)); // Small height
        button7 = new JButton("Library");
        button7.setForeground(Color.WHITE); // Sets text color to white
        button7.setBorderPainted(false);
        button7.setContentAreaFilled(false); // Removes background fill
        button7.setFocusPainted(false); // Removes focus border
        button7.setOpaque(false); // Makes it fully transparent
        button8 = new JButton("History");
        button8.setForeground(Color.WHITE); // Sets text color to white
        button8.setBorderPainted(false);
        button8.setContentAreaFilled(false); // Removes background fill
        button8.setFocusPainted(false); // Removes focus border
        button8.setOpaque(false); // Makes it fully transparent

        // Add buttons to the button panel
        buttonPanel.setBackground(Color.CYAN) ;
        buttonPanel.add(button7);
        buttonPanel.add(button8);

        // Content Panel (Bottom Panel)
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.LIGHT_GRAY);
        contentPanel.add(new JLabel("Main Content Here"));

        // Add panels to the main panel
        panel5.add(buttonPanel, BorderLayout.NORTH); // Add button panel at the top
        panel5.add(contentPanel, BorderLayout.CENTER); // Add content panel below
        
           // Add action listeners
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel1");
            }
        });
        
          // Add action listeners
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel5");
            }
        });
         
          
         //end of Library

        // Create buttons with icons
        button1 = new JButton("Library",icon1);
        button1.setForeground(Color.WHITE); // Sets text color to white
        button1.setBorderPainted(false);
        button1.setContentAreaFilled(false); // Removes background fill
        button1.setFocusPainted(false); // Removes focus border
        button1.setOpaque(false); // Makes it fully transparent
        button2 = new JButton("Explore",icon2);
        button2.setForeground(Color.WHITE); // Sets text color to white
        button2.setBorderPainted(false);
        button2.setContentAreaFilled(false); // Removes background fill
        button2.setFocusPainted(false); // Removes focus border
        button2.setOpaque(false); // Makes it fully transparent
        button3 = new JButton("Genre",icon3);
        button3.setForeground(Color.WHITE); // Sets text color to white
        button3.setBorderPainted(false);
        button3.setContentAreaFilled(false); // Removes background fill
        button3.setFocusPainted(false); // Removes focus border
        button3.setOpaque(false); // Makes it fully transparent
        button4 = new JButton("Me",icon4);
        button4.setForeground(Color.WHITE); // Sets text color to white
        button4.setBorderPainted(false);
        button4.setContentAreaFilled(false); // Removes background fill
        button4.setFocusPainted(false); // Removes focus border
        button4.setOpaque(false); // Makes it fully transparent
        
        
        
        button1.setHorizontalTextPosition(JButton.CENTER);
        button1.setVerticalTextPosition(JButton.BOTTOM);
        button2.setHorizontalTextPosition(JButton.CENTER);
        button2.setVerticalTextPosition(JButton.BOTTOM);
        button3.setHorizontalTextPosition(JButton.CENTER);
        button3.setVerticalTextPosition(JButton.BOTTOM);
        button4.setHorizontalTextPosition(JButton.CENTER);
        button4.setVerticalTextPosition(JButton.BOTTOM);

        // Add action listeners
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel1");
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel2");
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel3");
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel4");
            }
        });
        
        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel6");
            }
        });
        
        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel7");
            }
        });
        
        button12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel8");
            }
        });
        
        button13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel9");
            }
        });
        
        //Action Listeners for buttons 14 - 29
        // Add action listeners
         button14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel6");
            }
        });
         
          button15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel7");
            }
        });
          
           button16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel8");
            }
        });
           
            button17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel9");
            }
        });
            
             button18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel6");
            }
        });
             
              button19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel7");
            }
        });
              
               button20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel8");
            }
        });
               
                button21.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel9");
            }
        });
                
                 button22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel6");
            }
        });
                 
                  button23.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel7");
            }
        });
                  
                   button24.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel8");
            }
        });
                   
                    button25.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel9");
            }
        });
                    
                     button26.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel6");
            }
        });
                     
                      button27.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel7");
            }
        });
                      
                       button28.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel8");
            }
        });
                       
                        button29.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel9");
            }
        });
                                     button30.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (mainPanel.getLayout());
                cl.show(mainPanel, "Panel5");
            }
        });
                                    
     
        if (toggleButton.isSelected()) {
            // Apply Dark Mode
            mainPanel.setBackground(Color.DARK_GRAY);
            toggleButton.setBackground(Color.BLACK);
            toggleButton.setForeground(Color.WHITE);
            toggleButton.setText("Dark Mode ON");
        } else {
            // Apply Light Mode
            mainPanel.setBackground(Color.WHITE);
            toggleButton.setBackground(Color.LIGHT_GRAY);
            toggleButton.setForeground(Color.BLACK);
            toggleButton.setText("Dark Mode OFF");
        }
                        
           //end of button 14 - 29 action listener.

        // Add components to the frame
        add(mainPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK) ;
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        add(buttonPanel, BorderLayout.SOUTH);
      
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MultiplePanels frame = new MultiplePanels();
            frame.setVisible(true);
        });
    }
}


