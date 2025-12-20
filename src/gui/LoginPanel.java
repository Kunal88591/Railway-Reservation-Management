package gui;

import models.User;
import utils.SessionManager;
import exceptions.AuthenticationException;

import javax.swing.*;
import java.awt.*;

/**
 * Login Panel
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
        setBackground(UIStyles.LIGHT_COLOR);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Create login form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(UIStyles.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIStyles.PRIMARY_COLOR, 2),
            BorderFactory.createEmptyBorder(40, 40, 40, 40)
        ));
        
        GridBagConstraints formGbc = new GridBagConstraints();
        formGbc.insets = new Insets(10, 10, 10, 10);
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Title with icon
        JLabel titleLabel = new JLabel("🚂 Railway Reservation System");
        titleLabel.setFont(UIStyles.TITLE_FONT);
        titleLabel.setForeground(UIStyles.PRIMARY_COLOR);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        formGbc.gridx = 0;
        formGbc.gridy = 0;
        formGbc.gridwidth = 2;
        formPanel.add(titleLabel, formGbc);
        
        // Subtitle
        JLabel subtitleLabel = new JLabel("Login to your account");
        subtitleLabel.setFont(UIStyles.NORMAL_FONT);
        subtitleLabel.setForeground(UIStyles.DARK_COLOR);
        subtitleLabel.setHorizontalAlignment(JLabel.CENTER);
        formGbc.gridy = 1;
        formGbc.insets = new Insets(5, 10, 20, 10);
        formPanel.add(subtitleLabel, formGbc);
        
        // Username
        formGbc.gridwidth = 1;
        formGbc.gridy = 2;
        formGbc.insets = new Insets(10, 10, 10, 10);
        JLabel usernameLabel = UIStyles.createStyledLabel("Username:", UIStyles.SUBHEADING_FONT);
        formPanel.add(usernameLabel, formGbc);
        
        formGbc.gridx = 1;
        usernameField = UIStyles.createStyledTextField();
        usernameField.setPreferredSize(new Dimension(250, 40));
        formPanel.add(usernameField, formGbc);
        
        // Password
        formGbc.gridx = 0;
        formGbc.gridy = 3;
        JLabel passwordLabel = UIStyles.createStyledLabel("Password:", UIStyles.SUBHEADING_FONT);
        formPanel.add(passwordLabel, formGbc);
        
        formGbc.gridx = 1;
        passwordField = UIStyles.createStyledPasswordField();
        passwordField.setPreferredSize(new Dimension(250, 40));
        formPanel.add(passwordField, formGbc);
        
        // Default credentials info
        JLabel infoLabel = new JLabel("<html><center>Default Admin: username: <b>admin</b>, password: <b>admin123</b></center></html>");
        infoLabel.setFont(UIStyles.SMALL_FONT);
        infoLabel.setForeground(UIStyles.SECONDARY_COLOR);
        formGbc.gridx = 0;
        formGbc.gridy = 4;
        formGbc.gridwidth = 2;
        formGbc.insets = new Insets(5, 10, 15, 10);
        formPanel.add(infoLabel, formGbc);
        
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(UIStyles.WHITE);
        
        loginButton = UIStyles.createStyledButton("Login", UIStyles.PRIMARY_COLOR);
        loginButton.setPreferredSize(new Dimension(120, 40));
        loginButton.addActionListener(e -> handleLogin());
        buttonPanel.add(loginButton);
        
        registerButton = UIStyles.createStyledButton("Register", UIStyles.SUCCESS_COLOR);
        registerButton.setPreferredSize(new Dimension(120, 40));
        registerButton.addActionListener(e -> mainApp.showPanel(RailwayReservationApp.REGISTER_PANEL));
        buttonPanel.add(registerButton);
        
        formGbc.gridy = 5;
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
