package gui;

import models.User;
import utils.SessionManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.format.DateTimeFormatter;

/**
 * Modern Profile Panel
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
        setLayout(new BorderLayout(15, 15));
        setBackground(UIStyles.LIGHT_BG);
        
        // Create modern profile card
        JPanel profilePanel = UIStyles.createStyledPanel();
        profilePanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 25, 15, 25);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        
        // Title
        JLabel titleLabel = new JLabel("My Profile");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(UIStyles.PRIMARY_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 25, 25, 25);
        profilePanel.add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.insets = new Insets(12, 25, 12, 25);
        
        // User ID
        gbc.gridy = 1;
        addProfileField(profilePanel, gbc, "User ID", currentUser.getUserId());
        
        // Username
        gbc.gridy = 2;
        addProfileField(profilePanel, gbc, "Username", currentUser.getUsername());
        
        // Full Name
        gbc.gridy = 3;
        addProfileField(profilePanel, gbc, "Full Name", currentUser.getFullName());
        
        // Email
        gbc.gridy = 4;
        addProfileField(profilePanel, gbc, "Email", currentUser.getEmail());
        
        // Phone
        gbc.gridy = 5;
        addProfileField(profilePanel, gbc, "Phone Number", currentUser.getPhoneNumber());
        
        // Role
        gbc.gridy = 6;
        addProfileField(profilePanel, gbc, "Account Type", currentUser.getRole().toString());
        
        // Registration Date
        gbc.gridy = 7;
        String regDate = currentUser.getRegistrationDate()
                .format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        addProfileField(profilePanel, gbc, "Member Since", regDate);
        
        // Status with badge
        gbc.gridy = 8;
        gbc.gridx = 0;
        JLabel statusKeyLabel = new JLabel("Status");
        statusKeyLabel.setFont(UIStyles.SUBHEADING_FONT);
        statusKeyLabel.setForeground(UIStyles.TEXT_SECONDARY);
        profilePanel.add(statusKeyLabel, gbc);
        
        gbc.gridx = 1;
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        statusPanel.setBackground(Color.WHITE);
        
        JLabel statusBadge = new JLabel(currentUser.isActive() ? " Active " : " Inactive ");
        statusBadge.setFont(new Font("Segoe UI", Font.BOLD, 13));
        statusBadge.setForeground(Color.WHITE);
        statusBadge.setBackground(currentUser.isActive() ? UIStyles.SUCCESS_COLOR : UIStyles.DANGER_COLOR);
        statusBadge.setOpaque(true);
        statusBadge.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.GRAY, 0),
            BorderFactory.createEmptyBorder(4, 10, 4, 10)
        ));
        statusPanel.add(statusBadge);
        profilePanel.add(statusPanel, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton changePasswordButton = UIStyles.createStyledButton("Change Password", UIStyles.WARNING_COLOR);
        changePasswordButton.setPreferredSize(new Dimension(180, 42));
        changePasswordButton.addActionListener(e -> showChangePasswordDialog());
        buttonPanel.add(changePasswordButton);
        
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 25, 20, 25);
        profilePanel.add(buttonPanel, gbc);
        
        // Center the profile panel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(UIStyles.LIGHT_BG);
        GridBagConstraints centerGbc = new GridBagConstraints();
        centerPanel.add(profilePanel, centerGbc);
        
        add(centerPanel, BorderLayout.CENTER);
    }
    
    private void addProfileField(JPanel panel, GridBagConstraints gbc, String label, String value) {
        gbc.gridx = 0;
        JLabel keyLabel = new JLabel(label);
        keyLabel.setFont(UIStyles.SUBHEADING_FONT);
        keyLabel.setForeground(UIStyles.TEXT_SECONDARY);
        panel.add(keyLabel, gbc);
        
        gbc.gridx = 1;
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        valueLabel.setForeground(UIStyles.TEXT_PRIMARY);
        panel.add(valueLabel, gbc);
    }
    
    private void showChangePasswordDialog() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel currentLabel = new JLabel("Current Password:");
        currentLabel.setFont(UIStyles.NORMAL_FONT);
        JPasswordField currentField = UIStyles.createStyledPasswordField();
        currentField.setPreferredSize(new Dimension(250, 35));
        
        JLabel newLabel = new JLabel("New Password:");
        newLabel.setFont(UIStyles.NORMAL_FONT);
        JPasswordField newField = UIStyles.createStyledPasswordField();
        newField.setPreferredSize(new Dimension(250, 35));
        
        JLabel confirmLabel = new JLabel("Confirm Password:");
        confirmLabel.setFont(UIStyles.NORMAL_FONT);
        JPasswordField confirmField = UIStyles.createStyledPasswordField();
        confirmField.setPreferredSize(new Dimension(250, 35));
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(currentLabel, gbc);
        gbc.gridx = 1;
        panel.add(currentField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(newLabel, gbc);
        gbc.gridx = 1;
        panel.add(newField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(confirmLabel, gbc);
        gbc.gridx = 1;
        panel.add(confirmField, gbc);
        
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
