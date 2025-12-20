package gui;

import models.User;
import utils.SessionManager;

import javax.swing.*;
import java.awt.*;

/**
 * Modern Customer Dashboard
 */
public class CustomerDashboard extends JPanel {
    
    private RailwayReservationApp mainApp;
    private JPanel contentPanel;
    private CardLayout contentLayout;
    private JPanel currentActivePanel = null;
    
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
        setBackground(UIStyles.LIGHT_BG);
        
        // Create modern header
        add(createHeader(), BorderLayout.NORTH);
        
        // Create modern sidebar
        add(createSidebar(), BorderLayout.WEST);
        
        // Create content area
        contentLayout = new CardLayout();
        contentPanel = new JPanel(contentLayout);
        contentPanel.setBackground(UIStyles.LIGHT_BG);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Add content panels
        contentPanel.add(new SearchTrainsPanel(this), SEARCH_PANEL);
        contentPanel.add(new MyBookingsPanel(this), MY_BOOKINGS_PANEL);
        contentPanel.add(new ProfilePanel(this), PROFILE_PANEL);
        
        add(contentPanel, BorderLayout.CENTER);
        
        // Show search panel by default
        showContent(SEARCH_PANEL);
    }
    
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                // Gradient background
                GradientPaint gradient = new GradientPaint(
                    0, 0, UIStyles.PRIMARY_COLOR,
                    getWidth(), 0, UIStyles.PRIMARY_DARK
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                
                g2d.dispose();
            }
        };
        
        header.setPreferredSize(new Dimension(0, 70));
        header.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        header.setOpaque(false);
        
        User currentUser = SessionManager.getInstance().getCurrentUser();
        
        JLabel titleLabel = new JLabel("Railway Reservation");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(UIStyles.WHITE);
        
        // User badge
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        userPanel.setOpaque(false);
        
        JLabel userIcon = new JLabel("[U]");
        userIcon.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        userPanel.add(userIcon);
        
        JLabel userLabel = new JLabel("Welcome, " + currentUser.getFullName());
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        userLabel.setForeground(UIStyles.WHITE);
        userPanel.add(userLabel);
        
        JButton logoutButton = UIStyles.createStyledButton("Logout", UIStyles.DANGER_COLOR);
        logoutButton.setPreferredSize(new Dimension(110, 38));
        logoutButton.addActionListener(e -> handleLogout());
        
        header.add(titleLabel, BorderLayout.WEST);
        header.add(userPanel, BorderLayout.CENTER);
        header.add(logoutButton, BorderLayout.EAST);
        
        return header;
    }
    
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(UIStyles.PRIMARY_DARK);
        sidebar.setPreferredSize(new Dimension(240, 0));
        sidebar.setBorder(BorderFactory.createEmptyBorder(25, 0, 25, 0));
        
        // Add spacing at top
        sidebar.add(Box.createVerticalStrut(10));
        
        // Menu items
        addMenuItem(sidebar, "Search Trains", SEARCH_PANEL, true);
        addMenuItem(sidebar, "My Bookings", MY_BOOKINGS_PANEL, false);
        addMenuItem(sidebar, "Profile", PROFILE_PANEL, false);
        
        // Add glue to push items to top
        sidebar.add(Box.createVerticalGlue());
        
        return sidebar;
    }
    
    
    private void addMenuItem(JPanel sidebar, String text, String panelName, boolean isFirst) {
        // Create a panel instead of button for better control
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setBackground(isFirst ? UIStyles.PRIMARY_COLOR : UIStyles.PRIMARY_DARK);
        menuPanel.setMaximumSize(new Dimension(240, 55));
        menuPanel.setPreferredSize(new Dimension(240, 55));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        menuPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add label with white text
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        textLabel.setForeground(Color.WHITE);
        menuPanel.add(textLabel, BorderLayout.WEST);
        
        if (isFirst) {
            currentActivePanel = menuPanel;
        }
        
        // Mouse listeners for hover and click
        menuPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (menuPanel != currentActivePanel) {
                    menuPanel.setBackground(UIStyles.PRIMARY_COLOR.brighter());
                }
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (menuPanel != currentActivePanel) {
                    menuPanel.setBackground(UIStyles.PRIMARY_DARK);
                }
            }
            
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (currentActivePanel != null) {
                    currentActivePanel.setBackground(UIStyles.PRIMARY_DARK);
                }
                menuPanel.setBackground(UIStyles.PRIMARY_COLOR);
                currentActivePanel = menuPanel;
                showContent(panelName);
            }
        });
        
        sidebar.add(menuPanel);
        sidebar.add(Box.createVerticalStrut(5));
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
