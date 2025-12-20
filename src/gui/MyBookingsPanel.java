package gui;

import models.Booking;
import utils.DatabaseManager;
import utils.SessionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Modern My Bookings Panel
 */
public class MyBookingsPanel extends JPanel {
    
    private CustomerDashboard dashboard;
    private JTable bookingsTable;
    private DefaultTableModel tableModel;
    private List<Booking> bookings;
    
    public MyBookingsPanel(CustomerDashboard dashboard) {
        this.dashboard = dashboard;
        initializeUI();
        loadBookings();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(15, 15));
        setBackground(UIStyles.LIGHT_BG);
        
        // Header
        JPanel headerPanel = UIStyles.createStyledPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JLabel titleLabel = UIStyles.createSectionLabel("My Bookings");
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        JButton refreshButton = UIStyles.createStyledButton("Refresh", UIStyles.SECONDARY_COLOR);
        refreshButton.setPreferredSize(new Dimension(120, 38));
        refreshButton.addActionListener(e -> loadBookings());
        headerPanel.add(refreshButton, BorderLayout.EAST);
        
        add(headerPanel, BorderLayout.NORTH);
        
        // Table
        add(createTablePanel(), BorderLayout.CENTER);
    }
    
    private JPanel createTablePanel() {
        JPanel panel = UIStyles.createStyledPanel();
        panel.setLayout(new BorderLayout());
        
        String[] columnNames = {"PNR", "Train", "Date", "Passengers", "Fare (₹)", "Status", "Action"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };
        
        bookingsTable = new JTable(tableModel);
        bookingsTable.setFont(UIStyles.NORMAL_FONT);
        bookingsTable.setRowHeight(45);
        bookingsTable.setShowGrid(false);
        bookingsTable.setIntercellSpacing(new Dimension(0, 0));
        bookingsTable.setSelectionBackground(new Color(232, 240, 254));
        bookingsTable.setSelectionForeground(UIStyles.TEXT_PRIMARY);
        
        // Header styling
        bookingsTable.getTableHeader().setFont(UIStyles.SUBHEADING_FONT);
        bookingsTable.getTableHeader().setBackground(UIStyles.PRIMARY_COLOR);
        bookingsTable.getTableHeader().setForeground(UIStyles.WHITE);
        bookingsTable.getTableHeader().setPreferredSize(new Dimension(0, 40));
        bookingsTable.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        
        // Striped rows
        bookingsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(new Color(248, 249, 250));
                    }
                }
                
                setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        });
        
        bookingsTable.getColumn("Action").setCellRenderer(new ButtonRenderer());
        bookingsTable.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox()));
        
        JScrollPane scrollPane = new JScrollPane(bookingsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void loadBookings() {
        String userId = SessionManager.getInstance().getCurrentUser().getUserId();
        bookings = DatabaseManager.getInstance().getBookingsByUserId(userId);
        
        tableModel.setRowCount(0);
        
        for (Booking booking : bookings) {
            Object[] row = {
                booking.getPnr(),
                booking.getTrain().getTrainNumber() + " - " + booking.getTrain().getTrainName(),
                booking.getTrain().getOperationDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                booking.getPassengers().size(),
                String.format("%.2f", booking.getTotalFare()),
                booking.getStatus(),
                booking.getStatus() == Booking.BookingStatus.CONFIRMED ? "Cancel" : "View"
            };
            tableModel.addRow(row);
        }
        
        if (bookings.isEmpty()) {
            UIStyles.showWarning(this, "No bookings found");
        }
    }
    
    // Button Renderer
    class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value == null ? "" : value.toString());
            
            if ("Cancel".equals(value.toString())) {
                setBackground(UIStyles.DANGER_COLOR);
            } else {
                setBackground(UIStyles.SECONDARY_COLOR);
            }
            
            setForeground(UIStyles.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 13));
            setBorderPainted(false);
            setFocusPainted(false);
            return this;
        }
    }
    
    // Button Editor
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean clicked;
        private int row;
        
        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.setForeground(UIStyles.WHITE);
            button.setFont(new Font("Segoe UI", Font.BOLD, 13));
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            
            button.addActionListener(e -> fireEditingStopped());
        }
        
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            this.row = row;
            label = value == null ? "" : value.toString();
            button.setText(label);
            
            if ("Cancel".equals(label)) {
                button.setBackground(UIStyles.DANGER_COLOR);
            } else {
                button.setBackground(UIStyles.SECONDARY_COLOR);
            }
            
            clicked = true;
            return button;
        }
        
        public Object getCellEditorValue() {
            if (clicked) {
                Booking booking = bookings.get(row);
                
                if ("Cancel".equals(label)) {
                    handleCancelBooking(booking);
                } else {
                    showBookingDetails(booking);
                }
            }
            clicked = false;
            return label;
        }
        
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }
    }
    
    private void handleCancelBooking(Booking booking) {
        if (UIStyles.showConfirmation(this, "Are you sure you want to cancel this booking?")) {
            if (booking.cancelBooking()) {
                DatabaseManager.getInstance().updateBooking(booking);
                DatabaseManager.getInstance().updateTrain(booking.getTrain());
                UIStyles.showSuccess(this, "Booking cancelled successfully");
                loadBookings();
            } else {
                UIStyles.showError(this, "Unable to cancel booking");
            }
        }
    }
    
    private void showBookingDetails(Booking booking) {
        StringBuilder details = new StringBuilder();
        details.append("PNR: ").append(booking.getPnr()).append("\n");
        details.append("Train: ").append(booking.getTrain().getTrainNumber())
               .append(" - ").append(booking.getTrain().getTrainName()).append("\n");
        details.append("Route: ").append(booking.getTrain().getSource())
               .append(" → ").append(booking.getTrain().getDestination()).append("\n");
        details.append("Date: ").append(booking.getTrain().getOperationDate()).append("\n");
        details.append("Departure: ").append(booking.getTrain().getDepartureTime()).append("\n");
        details.append("Seats: ").append(booking.getSeatNumbers()).append("\n");
        details.append("Passengers: ").append(booking.getPassengers().size()).append("\n");
        details.append("Total Fare: ₹").append(String.format("%.2f", booking.getTotalFare())).append("\n");
        details.append("Payment: ").append(booking.getPaymentMethod()).append("\n");
        details.append("Status: ").append(booking.getStatus()).append("\n");
        details.append("Booked On: ").append(booking.getBookingDateTime()
               .format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
        
        JTextArea textArea = new JTextArea(details.toString());
        textArea.setFont(UIStyles.NORMAL_FONT);
        textArea.setEditable(false);
        textArea.setBackground(UIStyles.WHITE);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), 
            "Booking Details", JOptionPane.INFORMATION_MESSAGE);
    }
}
