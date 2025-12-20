package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Main Application Window
 */
public class RailwayReservationApp extends JFrame {
    
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;
    
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    // Panel names
    public static final String LOGIN_PANEL = "LOGIN";
    public static final String REGISTER_PANEL = "REGISTER";
    public static final String CUSTOMER_DASHBOARD = "CUSTOMER_DASHBOARD";
    public static final String ADMIN_DASHBOARD = "ADMIN_DASHBOARD";
    
    public RailwayReservationApp() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Railway Reservation Management System");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Add panels
        mainPanel.add(new LoginPanel(this), LOGIN_PANEL);
        mainPanel.add(new RegisterPanel(this), REGISTER_PANEL);
        
        add(mainPanel);
        
        // Show login panel initially
        showPanel(LOGIN_PANEL);
    }
    
    /**
     * Show a specific panel
     */
    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }
    
    /**
     * Show customer dashboard
     */
    public void showCustomerDashboard() {
        // Remove old dashboard if exists
        Component[] components = mainPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof CustomerDashboard) {
                mainPanel.remove(comp);
            }
        }
        
        // Add new dashboard
        CustomerDashboard dashboard = new CustomerDashboard(this);
        mainPanel.add(dashboard, CUSTOMER_DASHBOARD);
        showPanel(CUSTOMER_DASHBOARD);
    }
    
    /**
     * Show admin dashboard
     */
    public void showAdminDashboard() {
        // Remove old dashboard if exists
        Component[] components = mainPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof AdminDashboard) {
                mainPanel.remove(comp);
            }
        }
        
        // Add new dashboard
        AdminDashboard dashboard = new AdminDashboard(this);
        mainPanel.add(dashboard, ADMIN_DASHBOARD);
        showPanel(ADMIN_DASHBOARD);
    }
    
    /**
     * Logout and return to login screen
     */
    public void logout() {
        utils.SessionManager.getInstance().logout();
        showPanel(LOGIN_PANEL);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RailwayReservationApp app = new RailwayReservationApp();
            app.setVisible(true);
        });
    }
}
