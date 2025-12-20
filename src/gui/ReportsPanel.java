package gui;

import models.*;
import utils.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Panel for reports and analytics (Admin)
 */
public class ReportsPanel extends JPanel {
    
    private AdminDashboard dashboard;
    
    public ReportsPanel(AdminDashboard dashboard) {
        this.dashboard = dashboard;
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(UIStyles.LIGHT_COLOR);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UIStyles.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel titleLabel = new JLabel("Reports & Analytics");
        titleLabel.setFont(UIStyles.HEADING_FONT);
        titleLabel.setForeground(UIStyles.PRIMARY_COLOR);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        add(headerPanel, BorderLayout.NORTH);
        
        // Content
        add(createReportContent(), BorderLayout.CENTER);
    }
    
    private JPanel createReportContent() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(UIStyles.LIGHT_COLOR);
        
        DatabaseManager dbManager = DatabaseManager.getInstance();
        
        // Overall Statistics
        panel.add(createOverallStatsPanel());
        panel.add(Box.createVerticalStrut(15));
        
        // Train Statistics
        panel.add(createTrainStatsPanel());
        panel.add(Box.createVerticalStrut(15));
        
        // Booking Statistics
        panel.add(createBookingStatsPanel());
        panel.add(Box.createVerticalStrut(15));
        
        // Revenue Report
        panel.add(createRevenuePanel());
        
        return panel;
    }
    
    private JPanel createOverallStatsPanel() {
        JPanel panel = UIStyles.createStyledPanel();
        panel.setLayout(new GridLayout(1, 4, 15, 0));
        
        DatabaseManager dbManager = DatabaseManager.getInstance();
        
        int totalUsers = dbManager.getAllUsers().size();
        int totalTrains = dbManager.getAllTrains().size();
        int totalBookings = dbManager.getAllBookings().size();
        double totalRevenue = dbManager.getAllBookings().stream()
                .filter(b -> b.getStatus() == Booking.BookingStatus.CONFIRMED)
                .mapToDouble(Booking::getTotalFare)
                .sum();
        
        panel.add(createStatCard("Total Users", String.valueOf(totalUsers), UIStyles.PRIMARY_COLOR));
        panel.add(createStatCard("Total Trains", String.valueOf(totalTrains), UIStyles.SUCCESS_COLOR));
        panel.add(createStatCard("Total Bookings", String.valueOf(totalBookings), UIStyles.WARNING_COLOR));
        panel.add(createStatCard("Total Revenue", String.format("Rs%.2f", totalRevenue), UIStyles.DANGER_COLOR));
        
        return panel;
    }
    
    private JPanel createTrainStatsPanel() {
        JPanel panel = UIStyles.createStyledPanel();
        panel.setLayout(new BorderLayout(10, 10));
        
        JLabel titleLabel = new JLabel("Train Statistics");
        titleLabel.setFont(UIStyles.SUBHEADING_FONT);
        titleLabel.setForeground(UIStyles.PRIMARY_COLOR);
        panel.add(titleLabel, BorderLayout.NORTH);
        
        JTextArea textArea = new JTextArea();
        textArea.setFont(UIStyles.NORMAL_FONT);
        textArea.setEditable(false);
        textArea.setBackground(UIStyles.LIGHT_COLOR);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        DatabaseManager dbManager = DatabaseManager.getInstance();
        List<Train> trains = dbManager.getAllTrains();
        
        Map<String, Long> trainsByType = trains.stream()
                .collect(Collectors.groupingBy(Train::getTrainType, Collectors.counting()));
        
        int totalSeats = trains.stream().mapToInt(Train::getTotalSeats).sum();
        int occupiedSeats = trains.stream().mapToInt(t -> t.getTotalSeats() - t.getAvailableSeats()).sum();
        double occupancyRate = totalSeats > 0 ? (occupiedSeats * 100.0 / totalSeats) : 0;
        
        StringBuilder stats = new StringBuilder();
        stats.append("Total Trains: ").append(trains.size()).append("\n\n");
        stats.append("Trains by Type:\n");
        trainsByType.forEach((type, count) -> 
            stats.append("  - ").append(type).append(": ").append(count).append("\n"));
        stats.append("\nSeat Occupancy:\n");
        stats.append("  - Total Seats: ").append(totalSeats).append("\n");
        stats.append("  - Occupied Seats: ").append(occupiedSeats).append("\n");
        stats.append("  - Occupancy Rate: ").append(String.format("%.2f%%", occupancyRate));
        
        textArea.setText(stats.toString());
        
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createBookingStatsPanel() {
        JPanel panel = UIStyles.createStyledPanel();
        panel.setLayout(new BorderLayout(10, 10));
        
        JLabel titleLabel = new JLabel("Booking Statistics");
        titleLabel.setFont(UIStyles.SUBHEADING_FONT);
        titleLabel.setForeground(UIStyles.PRIMARY_COLOR);
        panel.add(titleLabel, BorderLayout.NORTH);
        
        JTextArea textArea = new JTextArea();
        textArea.setFont(UIStyles.NORMAL_FONT);
        textArea.setEditable(false);
        textArea.setBackground(UIStyles.LIGHT_COLOR);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        DatabaseManager dbManager = DatabaseManager.getInstance();
        List<Booking> bookings = dbManager.getAllBookings();
        
        Map<Booking.BookingStatus, Long> bookingsByStatus = bookings.stream()
                .collect(Collectors.groupingBy(Booking::getStatus, Collectors.counting()));
        
        LocalDate today = LocalDate.now();
        long todayBookings = bookings.stream()
                .filter(b -> b.getBookingDateTime().toLocalDate().equals(today))
                .count();
        
        int totalPassengers = bookings.stream()
                .mapToInt(b -> b.getPassengers().size())
                .sum();
        
        double avgBookingValue = bookings.stream()
                .filter(b -> b.getStatus() == Booking.BookingStatus.CONFIRMED)
                .mapToDouble(Booking::getTotalFare)
                .average()
                .orElse(0.0);
        
        StringBuilder stats = new StringBuilder();
        stats.append("Total Bookings: ").append(bookings.size()).append("\n\n");
        stats.append("Bookings by Status:\n");
        bookingsByStatus.forEach((status, count) -> 
            stats.append("  - ").append(status).append(": ").append(count).append("\n"));
        stats.append("\nOther Statistics:\n");
        stats.append("  - Today's Bookings: ").append(todayBookings).append("\n");
        stats.append("  - Total Passengers: ").append(totalPassengers).append("\n");
        stats.append("  - Average Booking Value: Rs").append(String.format("%.2f", avgBookingValue));
        
        textArea.setText(stats.toString());
        
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createRevenuePanel() {
        JPanel panel = UIStyles.createStyledPanel();
        panel.setLayout(new BorderLayout(10, 10));
        
        JLabel titleLabel = new JLabel("Revenue Report");
        titleLabel.setFont(UIStyles.SUBHEADING_FONT);
        titleLabel.setForeground(UIStyles.PRIMARY_COLOR);
        panel.add(titleLabel, BorderLayout.NORTH);
        
        JTextArea textArea = new JTextArea();
        textArea.setFont(UIStyles.NORMAL_FONT);
        textArea.setEditable(false);
        textArea.setBackground(UIStyles.LIGHT_COLOR);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        DatabaseManager dbManager = DatabaseManager.getInstance();
        List<Booking> confirmedBookings = dbManager.getAllBookings().stream()
                .filter(b -> b.getStatus() == Booking.BookingStatus.CONFIRMED)
                .collect(Collectors.toList());
        
        double totalRevenue = confirmedBookings.stream()
                .mapToDouble(Booking::getTotalFare)
                .sum();
        
        LocalDate today = LocalDate.now();
        double todayRevenue = confirmedBookings.stream()
                .filter(b -> b.getBookingDateTime().toLocalDate().equals(today))
                .mapToDouble(Booking::getTotalFare)
                .sum();
        
        Map<String, Double> revenueByPayment = confirmedBookings.stream()
                .collect(Collectors.groupingBy(Booking::getPaymentMethod,
                        Collectors.summingDouble(Booking::getTotalFare)));
        
        StringBuilder stats = new StringBuilder();
        stats.append("Total Revenue: Rs").append(String.format("%.2f", totalRevenue)).append("\n");
        stats.append("Today's Revenue: Rs").append(String.format("%.2f", todayRevenue)).append("\n\n");
        stats.append("Revenue by Payment Method:\n");
        revenueByPayment.forEach((method, revenue) -> 
            stats.append("  - ").append(method).append(": Rs")
                 .append(String.format("%.2f", revenue)).append("\n"));
        
        textArea.setText(stats.toString());
        
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(UIStyles.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 2),
            BorderFactory.createEmptyBorder(20, 15, 20, 15)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(UIStyles.SUBHEADING_FONT);
        titleLabel.setForeground(UIStyles.DARK_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        valueLabel.setForeground(color);
        valueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(valueLabel);
        
        return card;
    }
}
