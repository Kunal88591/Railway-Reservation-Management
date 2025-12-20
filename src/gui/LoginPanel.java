package gui;

import models.User;
import utils.SessionManager;
import exceptions.AuthenticationException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Modern Login Panel
 */
public class LoginPanel extends JPanel {
    
    private RailwayReservationApp mainApp;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    
    public LoginPanel(RailwayReservationApp mainApp) {
        this.mainApp = mainApp;
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new GridBagLayout());
        
        // Gradient background
        setBackground(new Color(240, 248, 255));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Create modern login card
        JPanel formPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Shadow effect
                g2d.setColor(new Color(0, 0, 0, 20));
                g2d.fillRoundRect(5, 5, getWidth() - 5, getHeight() - 5, 16, 16);
                
                g2d.dispose();
            }
        };
        
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(UIStyles.BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(50, 50, 50, 50)
        ));
        
        GridBagConstraints formGbc = new GridBagConstraints();
        formGbc.insets = new Insets(10, 10, 10, 10);
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Title with modern styling
        JLabel titleLabel = new JLabel("Railway Reservation");
        titleLabel.setFont(UIStyles.TITLE_FONT);
        titleLabel.setForeground(UIStyles.PRIMARY_COLOR);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        formGbc.gridx = 0;
        formGbc.gridy = 0;
        formGbc.gridwidth = 2;
        formPanel.add(titleLabel, formGbc);
        
        // Subtitle
        JLabel subtitleLabel = new JLabel("Login to your account");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(UIStyles.TEXT_SECONDARY);
        subtitleLabel.setHorizontalAlignment(JLabel.CENTER);
        formGbc.gridy = 1;
        formGbc.insets = new Insets(5, 10, 30, 10);
        formPanel.add(subtitleLabel, formGbc);
        
        // Username label and field
        formGbc.gridwidth = 1;
        formGbc.gridy = 2;
        formGbc.gridx = 0;
        formGbc.insets = new Insets(15, 10, 5, 10);
        formGbc.anchor = GridBagConstraints.WEST;
        JLabel usernameLabel = UIStyles.createStyledLabel("Username", UIStyles.SUBHEADING_FONT);
        formPanel.add(usernameLabel, formGbc);
        
        formGbc.gridy = 3;
        formGbc.gridwidth = 2;
        formGbc.insets = new Insets(5, 10, 10, 10);
        usernameField = UIStyles.createStyledTextField();
        usernameField.setPreferredSize(new Dimension(300, 45));
        formPanel.add(usernameField, formGbc);
        
        // Password label and field
        formGbc.gridy = 4;
        formGbc.gridwidth = 1;
        formGbc.insets = new Insets(15, 10, 5, 10);
        JLabel passwordLabel = UIStyles.createStyledLabel("Password", UIStyles.SUBHEADING_FONT);
        formPanel.add(passwordLabel, formGbc);
        
        formGbc.gridy = 5;
        formGbc.gridwidth = 2;
        formGbc.insets = new Insets(5, 10, 10, 10);
        passwordField = UIStyles.createStyledPasswordField();
        passwordField.setPreferredSize(new Dimension(300, 45));
        formPanel.add(passwordField, formGbc);
        
        // Default credentials info with modern styling
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        infoPanel.setBackground(new Color(232, 245, 233));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(76, 175, 80), 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        JLabel infoLabel = new JLabel("<html><center>Default Admin<br/>Username: <b>admin</b> | Password: <b>admin123</b></center></html>");
        infoLabel.setFont(UIStyles.SMALL_FONT);
        infoLabel.setForeground(new Color(46, 125, 50));
        infoPanel.add(infoLabel);
        
        formGbc.gridy = 6;
        formGbc.insets = new Insets(15, 10, 20, 10);
        formPanel.add(infoPanel, formGbc);
        
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        loginButton = UIStyles.createStyledButton("Login", UIStyles.PRIMARY_COLOR);
        loginButton.setPreferredSize(new Dimension(130, 45));
        loginButton.addActionListener(e -> handleLogin());
        buttonPanel.add(loginButton);
        
        registerButton = UIStyles.createStyledButton("Register", UIStyles.SUCCESS_COLOR);
        registerButton.setPreferredSize(new Dimension(130, 45));
        registerButton.addActionListener(e -> mainApp.showPanel(RailwayReservationApp.REGISTER_PANEL));
        buttonPanel.add(registerButton);
        
        formGbc.gridy = 7;
        formGbc.insets = new Insets(20, 10, 10, 10);
        formPanel.add(buttonPanel, formGbc);
        
        // Add Enter key listener for password field
        passwordField.addActionListener(e -> handleLogin());
        
        // Add form panel to main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(formPanel, gbc);
    }
    
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            UIStyles.showError(this, "Please enter both username and password");
            return;
        }
        
        try {
            SessionManager sessionManager = SessionManager.getInstance();
            sessionManager.login(username, password);
            
            User currentUser = sessionManager.getCurrentUser();
            
            // Clear fields
            usernameField.setText("");
            passwordField.setText("");
            
            // Navigate based on role
            if (currentUser.getRole() == User.UserRole.ADMIN) {
                mainApp.showAdminDashboard();
            } else {
                mainApp.showCustomerDashboard();
            }
            
        } catch (AuthenticationException ex) {
            UIStyles.showError(this, ex.getMessage());
        }
    }
}
