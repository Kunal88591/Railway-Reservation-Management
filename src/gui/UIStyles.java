package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * FINAL STABLE COLOR SYSTEM - DO NOT CHANGE
 */
public class UIStyles {
    
    // MASTER PALETTE (LOCKED)
    public static final Color APP_BG = new Color(0xF3F4F6);
    public static final Color PANEL_BG = new Color(0xFFFFFF);
    
    public static final Color PRIMARY = new Color(0x2563EB);
    public static final Color PRIMARY_HOVER = new Color(0x1D4ED8);
    public static final Color PRIMARY_TEXT = new Color(0xFFFFFF);
    
    public static final Color SECONDARY_BG = new Color(0xE5E7EB);
    public static final Color SECONDARY_TEXT = new Color(0x111827);
    
    public static final Color TEXT_PRIMARY = new Color(0x111827);
    public static final Color TEXT_SECONDARY = new Color(0x4B5563);
    
    public static final Color INPUT_BG = new Color(0xFFFFFF);
    public static final Color INPUT_TEXT = new Color(0x111827);
    public static final Color INPUT_BORDER = new Color(0x9CA3AF);
    
    public static final Color TABLE_HEADER_BG = new Color(0x1F2937);
    public static final Color TABLE_HEADER_TEXT = new Color(0xFFFFFF);
    public static final Color TABLE_ROW_BG = new Color(0xFFFFFF);
    public static final Color TABLE_ALT_ROW = new Color(0xF1F5F9);
    public static final Color TABLE_SELECTION_BG = new Color(0xDEAFE);
    
    public static final Color SIDEBAR_BG = new Color(0x1F2937);
    public static final Color SIDEBAR_ITEM_BG = new Color(0x2563EB);
    public static final Color SIDEBAR_ITEM_HOVER = new Color(0x1D4ED8);
    public static final Color SIDEBAR_TEXT = new Color(0xFFFFFF);
    
    public static final Color ERROR = new Color(0xDC2626);
    public static final Color SUCCESS = new Color(0x16A34A);
    
    // Fonts
    public static final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font BOLD_FONT = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 20);
    
    /**
     * NUCLEAR FIX: Style primary button with BasicButtonUI
     * This FORCES Swing to render text regardless of LookAndFeel
     */
    public static void stylePrimaryButton(JButton b) {
        b.setBackground(PRIMARY);
        b.setForeground(PRIMARY_TEXT);
        
        b.setOpaque(true);                // CRITICAL
        b.setContentAreaFilled(true);     // CRITICAL
        b.setBorderPainted(true);
        b.setFocusPainted(false);
        
        b.setFont(BOLD_FONT);
        b.setBorder(BorderFactory.createLineBorder(PRIMARY_HOVER, 2));
        
        // THE NUCLEAR FIX - kills LookAndFeel interference
        b.setUI(new BasicButtonUI());
        
        // Hover effect
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b.setBackground(PRIMARY_HOVER);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b.setBackground(PRIMARY);
            }
        });
    }
    
    /**
     * Create primary button with GUARANTEED visibility
     */
    public static JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        stylePrimaryButton(button);
        if (bgColor != null && !bgColor.equals(PRIMARY)) {
            button.setBackground(bgColor);
        }
        return button;
    }
    
    /**
     * Style sidebar menu button
     */
    public static void styleSidebarButton(JButton b) {
        b.setForeground(SIDEBAR_TEXT);
        b.setBackground(SIDEBAR_ITEM_BG);
        b.setOpaque(true);
        b.setContentAreaFilled(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setFont(BOLD_FONT);
        b.setBorder(new EmptyBorder(15, 25, 15, 25));
        b.setUI(new BasicButtonUI());
        
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b.setBackground(SIDEBAR_ITEM_HOVER);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                b.setBackground(SIDEBAR_ITEM_BG);
            }
        });
    }
    
    /**
     * Fix table cell rendering
     */
    public static void styleTable(JTable table) {
        // Fix all cell renderers
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBackground(TABLE_ROW_BG);
        renderer.setForeground(TEXT_PRIMARY);
        renderer.setOpaque(true);
        
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        
        // Style header
        table.getTableHeader().setBackground(TABLE_HEADER_BG);
        table.getTableHeader().setForeground(TABLE_HEADER_TEXT);
        table.getTableHeader().setFont(BOLD_FONT);
        
        // Table settings
        table.setBackground(TABLE_ROW_BG);
        table.setForeground(TEXT_PRIMARY);
        table.setSelectionBackground(TABLE_SELECTION_BG);
        table.setSelectionForeground(TEXT_PRIMARY);
        table.setFont(NORMAL_FONT);
        table.setRowHeight(40);
    }
    
    public static JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setBackground(INPUT_BG);
        field.setForeground(INPUT_TEXT);
        field.setCaretColor(INPUT_TEXT);
        field.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(INPUT_BORDER, 1),
            new EmptyBorder(8, 12, 8, 12)
        ));
        field.setFont(NORMAL_FONT);
        field.setOpaque(true);
        return field;
    }
    
    public static JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setBackground(INPUT_BG);
        field.setForeground(INPUT_TEXT);
        field.setCaretColor(INPUT_TEXT);
        field.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(INPUT_BORDER, 1),
            new EmptyBorder(8, 12, 8, 12)
        ));
        field.setFont(NORMAL_FONT);
        field.setOpaque(true);
        return field;
    }
    
    public static JLabel createStyledLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(TEXT_PRIMARY);
        return label;
    }
    
    public static JPanel createStyledPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(PANEL_BG);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        return panel;
    }
    
    // Compatibility aliases for existing code
    public static final Color PRIMARY_COLOR = PRIMARY;
    public static final Color SECONDARY_COLOR = new Color(0, 150, 136);
    public static final Color SUCCESS_COLOR = SUCCESS;
    public static final Color DANGER_COLOR = ERROR;
    public static final Color ERROR_COLOR = ERROR;
    public static final Color WARNING_COLOR = new Color(255, 152, 0);
    public static final Color WHITE = Color.WHITE;
    public static final Color DARK_COLOR = SIDEBAR_BG;
    public static final Color LIGHT_BG = APP_BG;
    public static final Color BORDER_COLOR = INPUT_BORDER;
    public static final Color PRIMARY_DARK = PRIMARY_HOVER;
    public static final Color BG_DARK = APP_BG;
    public static final Color BG_PANEL = PANEL_BG;
    public static final Color BUTTON_TEXT = PRIMARY_TEXT;
    
    public static final Font HEADING_FONT = TITLE_FONT;
    public static final Font SUBHEADING_FONT = BOLD_FONT;
    public static final Font SMALL_FONT = new Font("Segoe UI", Font.PLAIN, 12);
    
    /**
     * Apply light theme globally - MINIMAL UIManager changes
     */
    public static void applyDarkTheme() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            
            // Only set essential defaults
            UIManager.put("Panel.background", PANEL_BG);
            UIManager.put("Label.foreground", TEXT_PRIMARY);
            UIManager.put("OptionPane.background", PANEL_BG);
            UIManager.put("OptionPane.messageForeground", TEXT_PRIMARY);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static JLabel createSectionLabel(String text) {
        return createStyledLabel(text, TITLE_FONT);
    }
    
    public static JPanel createCardPanel(Color bg) {
        JPanel panel = new JPanel();
        panel.setBackground(bg != null ? bg : PANEL_BG);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        return panel;
    }
    
    public static void showSuccess(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showError(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void showWarning(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }
    
    public static boolean showConfirmation(Component parent, String message) {
        return JOptionPane.showConfirmDialog(parent, message, "Confirm",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}
