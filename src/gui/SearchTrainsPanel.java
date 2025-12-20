package gui;

import models.Train;
import utils.DatabaseManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

/**
 * Panel for searching trains
 */
public class SearchTrainsPanel extends JPanel {
    
    private CustomerDashboard dashboard;
    private JComboBox<String> sourceCombo;
    private JComboBox<String> destinationCombo;
    private JTextField dateField;
    private JButton searchButton;
    private JTable trainTable;
    private DefaultTableModel tableModel;
    private List<Train> searchResults;
    
    public SearchTrainsPanel(CustomerDashboard dashboard) {
        this.dashboard = dashboard;
        initializeUI();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(UIStyles.LIGHT_COLOR);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Search panel
        add(createSearchPanel(), BorderLayout.NORTH);
        
        // Results panel
        add(createResultsPanel(), BorderLayout.CENTER);
    }
    
    private JPanel createSearchPanel() {
        JPanel searchPanel = UIStyles.createStyledPanel();
        searchPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Title
        JLabel titleLabel = new JLabel("Search Trains");
        titleLabel.setFont(UIStyles.HEADING_FONT);
        titleLabel.setForeground(UIStyles.PRIMARY_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        searchPanel.add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        
        // Source
        gbc.gridy = 1;
        gbc.gridx = 0;
        JLabel sourceLabel = UIStyles.createStyledLabel("From:", UIStyles.SUBHEADING_FONT);
        searchPanel.add(sourceLabel, gbc);
        
        gbc.gridx = 1;
        Set<String> cities = DatabaseManager.getInstance().getAvailableCities();
        sourceCombo = new JComboBox<>(cities.toArray(new String[0]));
        sourceCombo.setFont(UIStyles.NORMAL_FONT);
        sourceCombo.setPreferredSize(new Dimension(180, 35));
        searchPanel.add(sourceCombo, gbc);
        
        // Destination
        gbc.gridx = 2;
        JLabel destLabel = UIStyles.createStyledLabel("To:", UIStyles.SUBHEADING_FONT);
        searchPanel.add(destLabel, gbc);
        
        gbc.gridx = 3;
        destinationCombo = new JComboBox<>(cities.toArray(new String[0]));
        destinationCombo.setFont(UIStyles.NORMAL_FONT);
        destinationCombo.setPreferredSize(new Dimension(180, 35));
        searchPanel.add(destinationCombo, gbc);
        
        // Date
        gbc.gridy = 2;
        gbc.gridx = 0;
        JLabel dateLabel = UIStyles.createStyledLabel("Date:", UIStyles.SUBHEADING_FONT);
        searchPanel.add(dateLabel, gbc);
        
        gbc.gridx = 1;
        dateField = UIStyles.createStyledTextField();
        dateField.setText(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        dateField.setPreferredSize(new Dimension(180, 35));
        searchPanel.add(dateField, gbc);
        
        // Search button
        gbc.gridx = 2;
        gbc.gridwidth = 2;
        searchButton = UIStyles.createStyledButton("Search Trains", UIStyles.PRIMARY_COLOR);
        searchButton.setPreferredSize(new Dimension(180, 40));
        searchButton.addActionListener(e -> handleSearch());
        searchPanel.add(searchButton, gbc);
        
        return searchPanel;
    }
    
    private JPanel createResultsPanel() {
        JPanel resultsPanel = UIStyles.createStyledPanel();
        resultsPanel.setLayout(new BorderLayout(10, 10));
        
        JLabel titleLabel = new JLabel("Available Trains");
        titleLabel.setFont(UIStyles.HEADING_FONT);
        titleLabel.setForeground(UIStyles.PRIMARY_COLOR);
        resultsPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Create table
        String[] columnNames = {"Train No.", "Train Name", "Type", "Departure", "Arrival", "Available Seats", "Fare (Rs)", "Action"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7; // Only Action column is editable (for button)
            }
        };
        
        trainTable = new JTable(tableModel);
        trainTable.setFont(UIStyles.NORMAL_FONT);
        trainTable.setRowHeight(40);
        trainTable.getTableHeader().setFont(UIStyles.SUBHEADING_FONT);
        trainTable.getTableHeader().setBackground(UIStyles.PRIMARY_COLOR);
        trainTable.getTableHeader().setForeground(UIStyles.WHITE);
        
        // Add button column
        trainTable.getColumn("Action").setCellRenderer(new ButtonRenderer());
        trainTable.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox()));
        
        JScrollPane scrollPane = new JScrollPane(trainTable);
        resultsPanel.add(scrollPane, BorderLayout.CENTER);
        
        return resultsPanel;
    }
    
    private void handleSearch() {
        String source = (String) sourceCombo.getSelectedItem();
        String destination = (String) destinationCombo.getSelectedItem();
        String dateStr = dateField.getText().trim();
        
        if (source == null || destination == null || dateStr.isEmpty()) {
            UIStyles.showError(this, "Please fill all fields");
            return;
        }
        
        if (source.equals(destination)) {
            UIStyles.showError(this, "Source and destination cannot be the same");
            return;
        }
        
        try {
            LocalDate date = LocalDate.parse(dateStr);
            
            if (date.isBefore(LocalDate.now())) {
                UIStyles.showError(this, "Date cannot be in the past");
                return;
            }
            
            DatabaseManager dbManager = DatabaseManager.getInstance();
            searchResults = dbManager.searchTrains(source, destination, date);
            
            // Update table
            tableModel.setRowCount(0);
            for (Train train : searchResults) {
                Object[] row = {
                    train.getTrainNumber(),
                    train.getTrainName(),
                    train.getTrainType(),
                    train.getDepartureTime(),
                    train.getArrivalTime(),
                    train.getAvailableSeats(),
                    String.format("%.2f", train.getFare()),
                    "Book"
                };
                tableModel.addRow(row);
            }
            
            if (searchResults.isEmpty()) {
                UIStyles.showWarning(this, "No trains found for the selected route and date");
            }
            
        } catch (Exception ex) {
            UIStyles.showError(this, "Invalid date format. Use YYYY-MM-DD");
        }
    }
    
    // Button Renderer for table
    class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }
        
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Book" : value.toString());
            setBackground(UIStyles.SUCCESS_COLOR);
            setForeground(UIStyles.WHITE);
            setFont(UIStyles.NORMAL_FONT);
            return this;
        }
    }
    
    // Button Editor for table
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean clicked;
        private int row;
        
        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.setBackground(UIStyles.SUCCESS_COLOR);
            button.setForeground(UIStyles.WHITE);
            button.setFont(UIStyles.NORMAL_FONT);
            
            button.addActionListener(e -> {
                fireEditingStopped();
            });
        }
        
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            this.row = row;
            label = (value == null) ? "Book" : value.toString();
            button.setText(label);
            clicked = true;
            return button;
        }
        
        public Object getCellEditorValue() {
            if (clicked) {
                // Open booking dialog
                Train selectedTrain = searchResults.get(row);
                openBookingDialog(selectedTrain);
            }
            clicked = false;
            return label;
        }
        
        public boolean stopCellEditing() {
            clicked = false;
            return super.stopCellEditing();
        }
    }
    
    private void openBookingDialog(Train train) {
        BookingDialog dialog = new BookingDialog((Frame) SwingUtilities.getWindowAncestor(this), 
                                                 train, dashboard);
        dialog.setVisible(true);
    }
}
