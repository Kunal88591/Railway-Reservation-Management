package gui;

import models.User;
import utils.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

/**
 * Panel to view and edit profile
 */
public class ProfilePanel extends JPanel {
    
    private CustomerDashboard dashboard;
    private User currentUser;
    
    public ProfilePanel(CustomerDashboard dashboard) {
        this.dashboard = dashboard;
        this.currentUser = SessionManager.getInstance().getCurrentUser();
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(UIStyles.LIGHT_COLOR);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create profile panel
        JPanel profilePanel = UIStyles.createStyledPanel();
        profilePanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Title
        JLabel titleLabel = new JLabel("My Profile");
        titleLabel.setFont(UIStyles.TITLE_FONT);
        titleLabel.setForeground(UIStyles.PRIMARY_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        profilePanel.add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 20, 10, 20);
        
        // User ID
        gbc.gridy = 1;
        addProfileField(profilePanel, gbc, "User ID:", currentUser.getUserId());
        
        // Username
        gbc.gridy = 2;
        addProfileField(profilePanel, gbc, "Username:", currentUser.getUsername());
        
        // Full Name
        gbc.gridy = 3;
        addProfileField(profilePanel, gbc, "Full Name:", currentUser.getFullName());
        
        // Email
        gbc.gridy = 4;
        addProfileField(profilePanel, gbc, "Email:", currentUser.getEmail());
        
        // Phone
        gbc.gridy = 5;
        addProfileField(profilePanel, gbc, "Phone:", currentUser.getPhoneNumber());
        
        // Role
        gbc.gridy = 6;
        addProfileField(profilePanel, gbc, "Role:", currentUser.getRole().toString());
        
        // Registration Date
        gbc.gridy = 7;
        String regDate = currentUser.getRegistrationDate()
                .format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        addProfileField(profilePanel, gbc, "Member Since:", regDate);
        
        // Status
        gbc.gridy = 8;
        String status = currentUser.isActive() ? "Active" : "Inactive";
        JLabel statusLabel = UIStyles.createStyledLabel(status, UIStyles.NORMAL_FONT);
        statusLabel.setForeground(currentUser.isActive() ? UIStyles.SUCCESS_COLOR : UIStyles.DANGER_COLOR);
        gbc.gridx = 0;
        profilePanel.add(UIStyles.createStyledLabel("Status:", UIStyles.SUBHEADING_FONT), gbc);
        gbc.gridx = 1;
        profilePanel.add(statusLabel, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(UIStyles.WHITE);
        
        JButton changePasswordButton = UIStyles.createStyledButton("Change Password", UIStyles.WARNING_COLOR);
        changePasswordButton.setPreferredSize(new Dimension(160, 40));
        changePasswordButton.addActionListener(e -> showChangePasswordDialog());
        buttonPanel.add(changePasswordButton);
        
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(25, 20, 15, 20);
        profilePanel.add(buttonPanel, gbc);
        
        add(profilePanel, BorderLayout.CENTER);
    }
    
    private void addProfileField(JPanel panel, GridBagConstraints gbc, String label, String value) {
        gbc.gridx = 0;
        panel.add(UIStyles.createStyledLabel(label, UIStyles.SUBHEADING_FONT), gbc);
        
        gbc.gridx = 1;
        JLabel valueLabel = UIStyles.createStyledLabel(value, UIStyles.NORMAL_FONT);
        valueLabel.setForeground(UIStyles.DARK_COLOR);
        panel.add(valueLabel, gbc);
    }
    
    private void showChangePasswordDialog() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        
        JLabel currentLabel = new JLabel("Current Password:");
        JPasswordField currentField = new JPasswordField();
        
        JLabel newLabel = new JLabel("New Password:");
        JPasswordField newField = new JPasswordField();
        
        JLabel confirmLabel = new JLabel("Confirm Password:");
        JPasswordField confirmField = new JPasswordField();
        
        panel.add(currentLabel);
        panel.add(currentField);
        panel.add(newLabel);
        panel.add(newField);
        panel.add(confirmLabel);
        panel.add(confirmField);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Change Password",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            String current = new String(currentField.getPassword());
            String newPass = new String(newField.getPassword());
            String confirm = new String(confirmField.getPassword());
            
            if (!currentUser.getPassword().equals(current)) {
                UIStyles.showError(this, "Current password is incorrect");
                return;
            }
            
            if (newPass.length() < 6) {
                UIStyles.showError(this, "New password must be at least 6 characters");
                return;
            }
            
            if (!newPass.equals(confirm)) {
                UIStyles.showError(this, "New passwords do not match");
                return;
            }
            
            currentUser.setPassword(newPass);
            UIStyles.showSuccess(this, "Password changed successfully");
        }
    }
}
