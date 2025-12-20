package gui;

import models.User;
import utils.DatabaseManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Panel to view all users (Admin)
 */
public class ViewUsersPanel extends JPanel {
    
    private AdminDashboard dashboard;
    private JTable usersTable;
    private DefaultTableModel tableModel;
    private List<User> users;
    
    public ViewUsersPanel(AdminDashboard dashboard) {
        this.dashboard = dashboard;
        initializeUI();
        loadUsers();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(UIStyles.LIGHT_COLOR);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UIStyles.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel titleLabel = new JLabel("All Users");
        titleLabel.setFont(UIStyles.HEADING_FONT);
        titleLabel.setForeground(UIStyles.PRIMARY_COLOR);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        JButton refreshButton = UIStyles.createStyledButton("Refresh", UIStyles.SECONDARY_COLOR);
        refreshButton.setPreferredSize(new Dimension(120, 35));
        refreshButton.addActionListener(e -> loadUsers());
        headerPanel.add(refreshButton, BorderLayout.EAST);
        
        add(headerPanel, BorderLayout.NORTH);
        
        // Table
        add(createTablePanel(), BorderLayout.CENTER);
    }
    
    private JPanel createTablePanel() {
        JPanel panel = UIStyles.createStyledPanel();
        panel.setLayout(new BorderLayout());
        
        String[] columnNames = {"User ID", "Username", "Full Name", "Email", 
                               "Phone", "Role", "Registration Date", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        usersTable = new JTable(tableModel);
        usersTable.setFont(UIStyles.NORMAL_FONT);
        usersTable.setRowHeight(40);
        usersTable.getTableHeader().setFont(UIStyles.SUBHEADING_FONT);
        usersTable.getTableHeader().setBackground(UIStyles.PRIMARY_COLOR);
        usersTable.getTableHeader().setForeground(UIStyles.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(usersTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void loadUsers() {
        users = DatabaseManager.getInstance().getAllUsers();
        tableModel.setRowCount(0);
        
        for (User user : users) {
            Object[] row = {
                user.getUserId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole(),
                user.getRegistrationDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                user.isActive() ? "Active" : "Inactive"
            };
            tableModel.addRow(row);
        }
    }
}
