package gui;

import models.Booking;
import utils.DatabaseManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Panel to view all bookings (Admin)
 */
public class ViewAllBookingsPanel extends JPanel {
    
    private AdminDashboard dashboard;
    private JTable bookingsTable;
    private DefaultTableModel tableModel;
    private List<Booking> bookings;
    
    public ViewAllBookingsPanel(AdminDashboard dashboard) {
        this.dashboard = dashboard;
        initializeUI();
        loadBookings();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(UIStyles.LIGHT_COLOR);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UIStyles.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel titleLabel = new JLabel("All Bookings");
        titleLabel.setFont(UIStyles.HEADING_FONT);
        titleLabel.setForeground(UIStyles.PRIMARY_COLOR);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        JButton refreshButton = UIStyles.createStyledButton("Refresh", UIStyles.SECONDARY_COLOR);
        refreshButton.setPreferredSize(new Dimension(120, 35));
        refreshButton.addActionListener(e -> loadBookings());
        headerPanel.add(refreshButton, BorderLayout.EAST);
        
        add(headerPanel, BorderLayout.NORTH);
        
        // Table
        add(createTablePanel(), BorderLayout.CENTER);
        
        // Statistics
        add(createStatsPanel(), BorderLayout.SOUTH);
    }
    
    private JPanel createTablePanel() {
        JPanel panel = UIStyles.createStyledPanel();
        panel.setLayout(new BorderLayout());
        
        String[] columnNames = {"PNR", "User ID", "Train", "Date", "Passengers", 
                               "Fare (Rs)", "Status", "Booking Date"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        bookingsTable = new JTable(tableModel);
        bookingsTable.setFont(UIStyles.NORMAL_FONT);
        bookingsTable.setRowHeight(40);
        bookingsTable.getTableHeader().setFont(UIStyles.SUBHEADING_FONT);
        bookingsTable.getTableHeader().setBackground(UIStyles.PRIMARY_COLOR);
        bookingsTable.getTableHeader().setForeground(UIStyles.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(bookingsTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createStatsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 10, 0));
        panel.setBackground(UIStyles.LIGHT_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        // Will be updated in loadBookings
        panel.add(createStatCard("Total Bookings", "0", UIStyles.PRIMARY_COLOR));
        panel.add(createStatCard("Confirmed", "0", UIStyles.SUCCESS_COLOR));
        panel.add(createStatCard("Cancelled", "0", UIStyles.DANGER_COLOR));
        panel.add(createStatCard("Total Revenue", "Rs0.00", UIStyles.WARNING_COLOR));
        
        return panel;
    }
    
    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(UIStyles.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(UIStyles.SUBHEADING_FONT);
        titleLabel.setForeground(UIStyles.DARK_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(UIStyles.HEADING_FONT);
        valueLabel.setForeground(color);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(valueLabel);
        
        return card;
    }
    
    private void loadBookings() {
        bookings = DatabaseManager.getInstance().getAllBookings();
        tableModel.setRowCount(0);
        
        int totalBookings = bookings.size();
        int confirmedCount = 0;
        int cancelledCount = 0;
        double totalRevenue = 0.0;
        
        for (Booking booking : bookings) {
            Object[] row = {
                booking.getPnr(),
                booking.getUserId(),
                booking.getTrain().getTrainNumber() + " - " + booking.getTrain().getTrainName(),
                booking.getTrain().getOperationDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                booking.getPassengers().size(),
                String.format("%.2f", booking.getTotalFare()),
                booking.getStatus(),
                booking.getBookingDateTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
            };
            tableModel.addRow(row);
            
            if (booking.getStatus() == Booking.BookingStatus.CONFIRMED) {
                confirmedCount++;
                totalRevenue += booking.getTotalFare();
            } else if (booking.getStatus() == Booking.BookingStatus.CANCELLED) {
                cancelledCount++;
            }
        }
        
        // Update statistics
        Component statsPanel = getComponent(2);
        if (statsPanel instanceof JPanel) {
            JPanel panel = (JPanel) statsPanel;
            panel.removeAll();
            panel.add(createStatCard("Total Bookings", String.valueOf(totalBookings), UIStyles.PRIMARY_COLOR));
            panel.add(createStatCard("Confirmed", String.valueOf(confirmedCount), UIStyles.SUCCESS_COLOR));
            panel.add(createStatCard("Cancelled", String.valueOf(cancelledCount), UIStyles.DANGER_COLOR));
            panel.add(createStatCard("Total Revenue", String.format("Rs%.2f", totalRevenue), UIStyles.WARNING_COLOR));
            panel.revalidate();
            panel.repaint();
        }
    }
}
