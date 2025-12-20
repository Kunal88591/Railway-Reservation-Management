package gui;

import models.User;
import utils.SessionManager;

import javax.swing.*;
import java.awt.*;

/**
 * Admin Dashboard - Main panel for admin operations
 */
public class AdminDashboard extends JPanel {
    
    private RailwayReservationApp mainApp;
    private JPanel contentPanel;
    private CardLayout contentLayout;
    
    // Panel names
    private static final String MANAGE_TRAINS_PANEL = "MANAGE_TRAINS";
    private static final String VIEW_BOOKINGS_PANEL = "VIEW_BOOKINGS";
    private static final String VIEW_USERS_PANEL = "VIEW_USERS";
    private static final String REPORTS_PANEL = "REPORTS";
    
    public AdminDashboard(RailwayReservationApp mainApp) {
        this.mainApp = mainApp;
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(UIStyles.LIGHT_COLOR);
        
        // Create header
        add(createHeader(), BorderLayout.NORTH);
        
        // Create sidebar
        add(createSidebar(), BorderLayout.WEST);
        
        // Create content area
        contentLayout = new CardLayout();
        contentPanel = new JPanel(contentLayout);
        contentPanel.setBackground(UIStyles.LIGHT_COLOR);
        
        // Add content panels
        contentPanel.add(new ManageTrainsPanel(this), MANAGE_TRAINS_PANEL);
        contentPanel.add(new ViewAllBookingsPanel(this), VIEW_BOOKINGS_PANEL);
        contentPanel.add(new ViewUsersPanel(this), VIEW_USERS_PANEL);
        contentPanel.add(new ReportsPanel(this), REPORTS_PANEL);
        
        add(contentPanel, BorderLayout.CENTER);
        
        // Show manage trains panel by default
        showContent(MANAGE_TRAINS_PANEL);
    }
    
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UIStyles.DANGER_COLOR);
        header.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        User currentUser = SessionManager.getInstance().getCurrentUser();
        
        JLabel titleLabel = new JLabel("🔧 Admin Dashboard");
        titleLabel.setFont(UIStyles.HEADING_FONT);
        titleLabel.setForeground(UIStyles.WHITE);
        
        JLabel userLabel = new JLabel("Admin: " + currentUser.getFullName());
        userLabel.setFont(UIStyles.NORMAL_FONT);
        userLabel.setForeground(UIStyles.WHITE);
        
        JButton logoutButton = UIStyles.createStyledButton("Logout", UIStyles.DARK_COLOR);
        logoutButton.setPreferredSize(new Dimension(100, 35));
        logoutButton.addActionListener(e -> handleLogout());
        
        header.add(titleLabel, BorderLayout.WEST);
        header.add(userLabel, BorderLayout.CENTER);
        header.add(logoutButton, BorderLayout.EAST);
        
        return header;
    }
    
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(UIStyles.DARK_COLOR);
        sidebar.setPreferredSize(new Dimension(220, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Menu items
        addMenuItem(sidebar, "🚂 Manage Trains", MANAGE_TRAINS_PANEL);
        addMenuItem(sidebar, "📋 View Bookings", VIEW_BOOKINGS_PANEL);
        addMenuItem(sidebar, "👥 View Users", VIEW_USERS_PANEL);
        addMenuItem(sidebar, "📊 Reports", REPORTS_PANEL);
        
        return sidebar;
    }
    
    private void addMenuItem(JPanel sidebar, String text, String panelName) {
        JButton menuButton = new JButton(text);
        menuButton.setFont(UIStyles.NORMAL_FONT);
        menuButton.setForeground(UIStyles.WHITE);
        menuButton.setBackground(UIStyles.DARK_COLOR);
        menuButton.setBorderPainted(false);
        menuButton.setFocusPainted(false);
        menuButton.setHorizontalAlignment(SwingConstants.LEFT);
        menuButton.setMaximumSize(new Dimension(220, 50));
        menuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menuButton.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        menuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuButton.setBackground(UIStyles.DANGER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuButton.setBackground(UIStyles.DARK_COLOR);
            }
        });
        
        menuButton.addActionListener(e -> showContent(panelName));
        
        sidebar.add(menuButton);
    }
    
    public void showContent(String panelName) {
        contentLayout.show(contentPanel, panelName);
    }
    
    public void refreshContent(String panelName) {
        // Refresh specific panel
        Component[] components = contentPanel.getComponents();
        for (Component comp : components) {
            if (panelName.equals(MANAGE_TRAINS_PANEL) && comp instanceof ManageTrainsPanel) {
                contentPanel.remove(comp);
                contentPanel.add(new ManageTrainsPanel(this), MANAGE_TRAINS_PANEL);
                break;
            } else if (panelName.equals(VIEW_BOOKINGS_PANEL) && comp instanceof ViewAllBookingsPanel) {
                contentPanel.remove(comp);
                contentPanel.add(new ViewAllBookingsPanel(this), VIEW_BOOKINGS_PANEL);
                break;
            } else if (panelName.equals(VIEW_USERS_PANEL) && comp instanceof ViewUsersPanel) {
                contentPanel.remove(comp);
                contentPanel.add(new ViewUsersPanel(this), VIEW_USERS_PANEL);
                break;
            }
        }
        showContent(panelName);
    }
    
    private void handleLogout() {
        if (UIStyles.showConfirmation(this, "Are you sure you want to logout?")) {
            mainApp.logout();
        }
    }
    
    public RailwayReservationApp getMainApp() {
        return mainApp;
    }
}
