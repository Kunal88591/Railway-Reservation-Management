package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Utility class for GUI components styling
 */
public class UIStyles {
    
    // Colors
    public static final Color PRIMARY_COLOR = new Color(0, 100, 200); // Bright blue
    public static final Color SECONDARY_COLOR = new Color(70, 130, 180); // Steel blue
    public static final Color SUCCESS_COLOR = new Color(0, 150, 0); // Bright green
    public static final Color DANGER_COLOR = new Color(220, 20, 60); // Crimson
    public static final Color WARNING_COLOR = new Color(255, 165, 0); // Orange
    public static final Color DARK_COLOR = new Color(20, 20, 20); // Almost black
    public static final Color LIGHT_COLOR = new Color(255, 250, 205); // Light yellow
    public static final Color WHITE = Color.WHITE;
    public static final Color BORDER_COLOR = Color.BLACK; // Black borders for maximum contrast
    
    // Fonts
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 28);
    public static final Font HEADING_FONT = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font SUBHEADING_FONT = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font SMALL_FONT = new Font("Segoe UI", Font.PLAIN, 11);
    
    /**
     * Create styled button
     */
    public static JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Bold font for better visibility
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(true); // Show border
        button.setBorder(BorderFactory.createLineBorder(bgColor.darker(), 2)); // Visible border
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 40));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
                button.setBorder(BorderFactory.createLineBorder(bgColor.darker().darker(), 2));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
                button.setBorder(BorderFactory.createLineBorder(bgColor.darker(), 2));
            }
        });
        
        return button;
    }
    
    /**
     * Create styled text field
     */
    public static JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(NORMAL_FONT);
        textField.setForeground(new Color(40, 40, 40)); // Dark text
        textField.setBackground(Color.WHITE); // White background
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(150, 150, 150), 2),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        return textField;
    }
    
    /**
     * Create styled password field
     */
    public static JPasswordField createStyledPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(NORMAL_FONT);
        passwordField.setForeground(new Color(40, 40, 40)); // Dark text
        passwordField.setBackground(Color.WHITE); // White background
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(150, 150, 150), 2),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        return passwordField;
    }
    
    /**
     * Create styled label
     */
    public static JLabel createStyledLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(new Color(40, 40, 40)); // Dark text for visibility
        return label;
    }
    
    /**
     * Create styled panel with border and padding
     */
    public static JPanel createStyledPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 240)); // Light cream background
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 3), // Thick black border
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        return panel;
    }
    
    /**
     * Show error message dialog
     */
    public static void showError(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Show success message dialog
     */
    public static void showSuccess(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Success", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Show warning message dialog
     */
    public static void showWarning(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Warning", 
            JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Show confirmation dialog
     */
    public static boolean showConfirmation(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, "Confirm",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
}
