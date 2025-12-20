package gui;

import models.User;
import utils.DatabaseManager;
import utils.ValidationUtils;
import exceptions.ValidationException;

import javax.swing.*;
import java.awt.*;

/**
 * Registration Panel
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
        setBackground(UIStyles.LIGHT_COLOR);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Create registration form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(UIStyles.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIStyles.SUCCESS_COLOR, 2),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
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
        
        formGbc.gridwidth = 1;
        
        // Full Name
        formGbc.gridy = 1;
        formGbc.insets = new Insets(15, 10, 8, 10);
        JLabel nameLabel = UIStyles.createStyledLabel("Full Name:", UIStyles.SUBHEADING_FONT);
        formPanel.add(nameLabel, formGbc);
        
        formGbc.gridx = 1;
        fullNameField = UIStyles.createStyledTextField();
        fullNameField.setPreferredSize(new Dimension(250, 35));
        formPanel.add(fullNameField, formGbc);
        
        // Username
        formGbc.gridx = 0;
        formGbc.gridy = 2;
        formGbc.insets = new Insets(8, 10, 8, 10);
        JLabel usernameLabel = UIStyles.createStyledLabel("Username:", UIStyles.SUBHEADING_FONT);
        formPanel.add(usernameLabel, formGbc);
        
        formGbc.gridx = 1;
        usernameField = UIStyles.createStyledTextField();
        usernameField.setPreferredSize(new Dimension(250, 35));
        formPanel.add(usernameField, formGbc);
        
        // Email
        formGbc.gridx = 0;
        formGbc.gridy = 3;
        JLabel emailLabel = UIStyles.createStyledLabel("Email:", UIStyles.SUBHEADING_FONT);
        formPanel.add(emailLabel, formGbc);
        
        formGbc.gridx = 1;
        emailField = UIStyles.createStyledTextField();
        emailField.setPreferredSize(new Dimension(250, 35));
        formPanel.add(emailField, formGbc);
        
        // Phone
        formGbc.gridx = 0;
        formGbc.gridy = 4;
        JLabel phoneLabel = UIStyles.createStyledLabel("Phone:", UIStyles.SUBHEADING_FONT);
        formPanel.add(phoneLabel, formGbc);
        
        formGbc.gridx = 1;
        phoneField = UIStyles.createStyledTextField();
        phoneField.setPreferredSize(new Dimension(250, 35));
        formPanel.add(phoneField, formGbc);
        
        // Password
        formGbc.gridx = 0;
        formGbc.gridy = 5;
        JLabel passwordLabel = UIStyles.createStyledLabel("Password:", UIStyles.SUBHEADING_FONT);
        formPanel.add(passwordLabel, formGbc);
        
        formGbc.gridx = 1;
        passwordField = UIStyles.createStyledPasswordField();
        passwordField.setPreferredSize(new Dimension(250, 35));
        formPanel.add(passwordField, formGbc);
        
        // Confirm Password
        formGbc.gridx = 0;
        formGbc.gridy = 6;
        JLabel confirmPasswordLabel = UIStyles.createStyledLabel("Confirm Password:", UIStyles.SUBHEADING_FONT);
        formPanel.add(confirmPasswordLabel, formGbc);
        
        formGbc.gridx = 1;
        confirmPasswordField = UIStyles.createStyledPasswordField();
        confirmPasswordField.setPreferredSize(new Dimension(250, 35));
        formPanel.add(confirmPasswordField, formGbc);
        
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(UIStyles.WHITE);
        
        registerButton = UIStyles.createStyledButton("Register", UIStyles.SUCCESS_COLOR);
        registerButton.setPreferredSize(new Dimension(120, 40));
        registerButton.addActionListener(e -> handleRegistration());
        buttonPanel.add(registerButton);
        
        backButton = UIStyles.createStyledButton("Back to Login", UIStyles.DARK_COLOR);
        backButton.setPreferredSize(new Dimension(130, 40));
        backButton.addActionListener(e -> mainApp.showPanel(RailwayReservationApp.LOGIN_PANEL));
        buttonPanel.add(backButton);
        
        formGbc.gridx = 0;
        formGbc.gridy = 7;
        formGbc.gridwidth = 2;
        formGbc.insets = new Insets(20, 10, 10, 10);
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
