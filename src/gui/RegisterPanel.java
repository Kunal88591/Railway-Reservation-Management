package gui;

import models.User;
import utils.DatabaseManager;
import utils.ValidationUtils;
import exceptions.ValidationException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Modern Registration Panel
 */
public class RegisterPanel extends JPanel {
    
    private RailwayReservationApp mainApp;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JButton registerButton;
    private JButton backButton;
    
    public RegisterPanel(RailwayReservationApp mainApp) {
        this.mainApp = mainApp;
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new GridBagLayout());
        
        // Gradient background
        setBackground(new Color(232, 245, 233));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Create modern registration card
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
            BorderFactory.createEmptyBorder(40, 50, 40, 50)
        ));
        
        GridBagConstraints formGbc = new GridBagConstraints();
        formGbc.insets = new Insets(8, 10, 8, 10);
        formGbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Title
        JLabel titleLabel = new JLabel("Create New Account");
        titleLabel.setFont(UIStyles.TITLE_FONT);
        titleLabel.setForeground(UIStyles.SUCCESS_COLOR);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        formGbc.gridx = 0;
        formGbc.gridy = 0;
        formGbc.gridwidth = 2;
        formPanel.add(titleLabel, formGbc);
        
        // Subtitle
        JLabel subtitleLabel = new JLabel("Join us today!");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(UIStyles.TEXT_SECONDARY);
        subtitleLabel.setHorizontalAlignment(JLabel.CENTER);
        formGbc.gridy = 1;
        formGbc.insets = new Insets(5, 10, 25, 10);
        formPanel.add(subtitleLabel, formGbc);
        
        formGbc.gridwidth = 1;
        formGbc.anchor = GridBagConstraints.WEST;
        
        // Full Name
        formGbc.gridy = 2;
        formGbc.gridx = 0;
        formGbc.insets = new Insets(10, 10, 5, 10);
        JLabel nameLabel = UIStyles.createStyledLabel("Full Name", UIStyles.SUBHEADING_FONT);
        formPanel.add(nameLabel, formGbc);
        
        formGbc.gridy = 3;
        formGbc.gridwidth = 2;
        formGbc.insets = new Insets(5, 10, 10, 10);
        fullNameField = UIStyles.createStyledTextField();
        fullNameField.setPreferredSize(new Dimension(320, 45));
        formPanel.add(fullNameField, formGbc);
        
        // Username
        formGbc.gridy = 4;
        formGbc.gridwidth = 1;
        formGbc.insets = new Insets(10, 10, 5, 10);
        JLabel usernameLabel = UIStyles.createStyledLabel("Username", UIStyles.SUBHEADING_FONT);
        formPanel.add(usernameLabel, formGbc);
        
        formGbc.gridy = 5;
        formGbc.gridwidth = 2;
        formGbc.insets = new Insets(5, 10, 10, 10);
        usernameField = UIStyles.createStyledTextField();
        usernameField.setPreferredSize(new Dimension(320, 45));
        formPanel.add(usernameField, formGbc);
        
        // Email
        formGbc.gridy = 6;
        formGbc.gridwidth = 1;
        formGbc.insets = new Insets(10, 10, 5, 10);
        JLabel emailLabel = UIStyles.createStyledLabel("Email", UIStyles.SUBHEADING_FONT);
        formPanel.add(emailLabel, formGbc);
        
        formGbc.gridy = 7;
        formGbc.gridwidth = 2;
        formGbc.insets = new Insets(5, 10, 10, 10);
        emailField = UIStyles.createStyledTextField();
        emailField.setPreferredSize(new Dimension(320, 45));
        formPanel.add(emailField, formGbc);
        
        // Phone
        formGbc.gridy = 8;
        formGbc.gridwidth = 1;
        formGbc.insets = new Insets(10, 10, 5, 10);
        JLabel phoneLabel = UIStyles.createStyledLabel("Phone", UIStyles.SUBHEADING_FONT);
        formPanel.add(phoneLabel, formGbc);
        
        formGbc.gridy = 9;
        formGbc.gridwidth = 2;
        formGbc.insets = new Insets(5, 10, 10, 10);
        phoneField = UIStyles.createStyledTextField();
        phoneField.setPreferredSize(new Dimension(320, 45));
        formPanel.add(phoneField, formGbc);
        
        // Password
        formGbc.gridy = 10;
        formGbc.gridwidth = 1;
        formGbc.insets = new Insets(10, 10, 5, 10);
        JLabel passwordLabel = UIStyles.createStyledLabel("Password", UIStyles.SUBHEADING_FONT);
        formPanel.add(passwordLabel, formGbc);
        
        formGbc.gridy = 11;
        formGbc.gridwidth = 2;
        formGbc.insets = new Insets(5, 10, 10, 10);
        passwordField = UIStyles.createStyledPasswordField();
        passwordField.setPreferredSize(new Dimension(320, 45));
        formPanel.add(passwordField, formGbc);
        
        // Confirm Password
        formGbc.gridy = 12;
        formGbc.gridwidth = 1;
        formGbc.insets = new Insets(10, 10, 5, 10);
        JLabel confirmPasswordLabel = UIStyles.createStyledLabel("Confirm Password", UIStyles.SUBHEADING_FONT);
        formPanel.add(confirmPasswordLabel, formGbc);
        
        formGbc.gridy = 13;
        formGbc.gridwidth = 2;
        formGbc.insets = new Insets(5, 10, 10, 10);
        confirmPasswordField = UIStyles.createStyledPasswordField();
        confirmPasswordField.setPreferredSize(new Dimension(320, 45));
        formPanel.add(confirmPasswordField, formGbc);
        
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        registerButton = UIStyles.createStyledButton("Register", UIStyles.SUCCESS_COLOR);
        registerButton.setPreferredSize(new Dimension(140, 45));
        registerButton.addActionListener(e -> handleRegistration());
        buttonPanel.add(registerButton);
        
        backButton = UIStyles.createStyledButton("Back to Login", UIStyles.DARK_COLOR);
        backButton.setPreferredSize(new Dimension(140, 45));
        backButton.addActionListener(e -> mainApp.showPanel(RailwayReservationApp.LOGIN_PANEL));
        buttonPanel.add(backButton);
        
        formGbc.gridy = 14;
        formGbc.insets = new Insets(25, 10, 10, 10);
        formPanel.add(buttonPanel, formGbc);
        
        // Add form panel to main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(formPanel, gbc);
    }
    
    private void handleRegistration() {
        try {
            // Get values
            String fullName = fullNameField.getText().trim();
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            
            // Validate
            ValidationUtils.validateName(fullName);
            ValidationUtils.validateUsername(username);
            ValidationUtils.validateEmail(email);
            ValidationUtils.validatePhoneNumber(phone);
            ValidationUtils.validatePassword(password);
            
            if (!password.equals(confirmPassword)) {
                throw new ValidationException("Passwords do not match");
            }
            
            DatabaseManager dbManager = DatabaseManager.getInstance();
            
            // Check if username exists
            if (dbManager.usernameExists(username)) {
                throw new ValidationException("Username already exists");
            }
            
            // Create new user
            String userId = dbManager.generateUserId();
            User newUser = new User(userId, username, password, fullName, 
                                   email, phone, User.UserRole.CUSTOMER);
            
            dbManager.addUser(newUser);
            
            UIStyles.showSuccess(this, "Registration successful! Please login.");
            
            // Clear fields
            clearFields();
            
            // Navigate to login
            mainApp.showPanel(RailwayReservationApp.LOGIN_PANEL);
            
        } catch (ValidationException ex) {
            UIStyles.showError(this, ex.getMessage());
        }
    }
    
    private void clearFields() {
        fullNameField.setText("");
        usernameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }
}
