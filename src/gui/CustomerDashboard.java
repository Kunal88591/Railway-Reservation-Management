package gui;

import models.User;
import utils.SessionManager;

import javax.swing.*;
import java.awt.*;

/**
 * Customer Dashboard - Main panel for customer operations
 */
public class CustomerDashboard extends JPanel {
    
    private RailwayReservationApp mainApp;
    private JPanel contentPanel;
    private CardLayout contentLayout;
    
    // Panel names
    private static final String SEARCH_PANEL = "SEARCH";
    private static final String MY_BOOKINGS_PANEL = "MY_BOOKINGS";
    private static final String PROFILE_PANEL = "PROFILE";
    
    public CustomerDashboard(RailwayReservationApp mainApp) {
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
        contentPanel.add(new SearchTrainsPanel(this), SEARCH_PANEL);
        contentPanel.add(new MyBookingsPanel(this), MY_BOOKINGS_PANEL);
        contentPanel.add(new ProfilePanel(this), PROFILE_PANEL);
        
        add(contentPanel, BorderLayout.CENTER);
        
        // Show search panel by default
        showContent(SEARCH_PANEL);
    }
    
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UIStyles.PRIMARY_COLOR);
        header.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        User currentUser = SessionManager.getInstance().getCurrentUser();
        
        JLabel titleLabel = new JLabel("Railway Reservation System");
        titleLabel.setFont(UIStyles.HEADING_FONT);
        titleLabel.setForeground(UIStyles.WHITE);
        
        JLabel userLabel = new JLabel("Welcome, " + currentUser.getFullName());
        userLabel.setFont(UIStyles.NORMAL_FONT);
        userLabel.setForeground(UIStyles.WHITE);
        
        JButton logoutButton = UIStyles.createStyledButton("Logout", UIStyles.DANGER_COLOR);
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
        addMenuItem(sidebar, "Search Trains", SEARCH_PANEL);
        addMenuItem(sidebar, "My Bookings", MY_BOOKINGS_PANEL);
        addMenuItem(sidebar, "Profile", PROFILE_PANEL);
        
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
                menuButton.setBackground(UIStyles.PRIMARY_COLOR);
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
    
    public void refreshMyBookings() {
        // Remove and recreate My Bookings panel
        Component[] components = contentPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof MyBookingsPanel) {
                contentPanel.remove(comp);
                contentPanel.add(new MyBookingsPanel(this), MY_BOOKINGS_PANEL);
                break;
            }
        }
        showContent(MY_BOOKINGS_PANEL);
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
