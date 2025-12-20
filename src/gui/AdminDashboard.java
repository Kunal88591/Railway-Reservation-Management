package gui;

import models.User;
import utils.SessionManager;

import javax.swing.*;
import java.awt.*;

/**
 * Modern Admin Dashboard
 */
public class AdminDashboard extends JPanel {
    
    private RailwayReservationApp mainApp;
    private JPanel contentPanel;
    private CardLayout contentLayout;
    private JPanel currentActivePanel = null;
    
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
        contentPanel.add(new ManageTrainsPanel(this), MANAGE_TRAINS_PANEL);
        contentPanel.add(new ViewAllBookingsPanel(this), VIEW_BOOKINGS_PANEL);
        contentPanel.add(new ViewUsersPanel(this), VIEW_USERS_PANEL);
        contentPanel.add(new ReportsPanel(this), REPORTS_PANEL);
        
        add(contentPanel, BorderLayout.CENTER);
        
        // Show manage trains panel by default
        showContent(MANAGE_TRAINS_PANEL);
    }
    
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                // Gradient background for admin (different colors)
                GradientPaint gradient = new GradientPaint(
                    0, 0, new Color(211, 47, 47),  // Red
                    getWidth(), 0, new Color(244, 67, 54)  // Lighter Red
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
        
        JLabel titleLabel = new JLabel("Admin Dashboard");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(UIStyles.WHITE);
        
        // Admin badge
        JPanel adminPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        adminPanel.setOpaque(false);
        
        JLabel adminIcon = new JLabel("[A]");
        adminIcon.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        adminPanel.add(adminIcon);
        
        JLabel userLabel = new JLabel("Admin: " + currentUser.getFullName());
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        userLabel.setForeground(UIStyles.WHITE);
        adminPanel.add(userLabel);
        
        JButton logoutButton = UIStyles.createStyledButton("Logout", UIStyles.DARK_COLOR);
        logoutButton.setPreferredSize(new Dimension(110, 38));
        logoutButton.addActionListener(e -> handleLogout());
        
        header.add(titleLabel, BorderLayout.WEST);
        header.add(adminPanel, BorderLayout.CENTER);
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
        addMenuItem(sidebar, "Manage Trains", MANAGE_TRAINS_PANEL, true);
        addMenuItem(sidebar, "View Bookings", VIEW_BOOKINGS_PANEL, false);
        addMenuItem(sidebar, "View Users", VIEW_USERS_PANEL, false);
        addMenuItem(sidebar, "Reports", REPORTS_PANEL, false);
        
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
