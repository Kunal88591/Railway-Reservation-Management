package gui;

import models.Train;
import utils.DatabaseManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

/**
 * Modern Search Trains Panel
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
        setLayout(new BorderLayout(15, 15));
        setBackground(UIStyles.LIGHT_BG);
        
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
        JLabel titleLabel = UIStyles.createSectionLabel("Search Trains");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.WEST;
        searchPanel.add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        
        // From city
        gbc.gridx = 0;
        gbc.insets = new Insets(15, 10, 5, 10);
        JLabel sourceLabel = UIStyles.createStyledLabel("From", UIStyles.SUBHEADING_FONT);
        searchPanel.add(sourceLabel, gbc);
        
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 10, 10, 10);
        Set<String> cities = DatabaseManager.getInstance().getAvailableCities();
        sourceCombo = new JComboBox<>(cities.toArray(new String[0]));
        sourceCombo.setFont(UIStyles.NORMAL_FONT);
        sourceCombo.setPreferredSize(new Dimension(200, 40));
        sourceCombo.setBackground(Color.WHITE);
        searchPanel.add(sourceCombo, gbc);
        
        // To city
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(15, 10, 5, 10);
        JLabel destLabel = UIStyles.createStyledLabel("To", UIStyles.SUBHEADING_FONT);
        searchPanel.add(destLabel, gbc);
        
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 10, 10, 10);
        destinationCombo = new JComboBox<>(cities.toArray(new String[0]));
        destinationCombo.setFont(UIStyles.NORMAL_FONT);
        destinationCombo.setPreferredSize(new Dimension(200, 40));
        destinationCombo.setBackground(Color.WHITE);
        searchPanel.add(destinationCombo, gbc);
        
        // Date
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.insets = new Insets(15, 10, 5, 10);
        JLabel dateLabel = UIStyles.createStyledLabel("Travel Date", UIStyles.SUBHEADING_FONT);
        searchPanel.add(dateLabel, gbc);
        
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 10, 10, 10);
        dateField = UIStyles.createStyledTextField();
        dateField.setText(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        dateField.setPreferredSize(new Dimension(180, 40));
        searchPanel.add(dateField, gbc);
        
        // Search button
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 10, 10, 10);
        searchButton = UIStyles.createStyledButton("Search", UIStyles.PRIMARY_COLOR);
        searchButton.setPreferredSize(new Dimension(130, 40));
        searchButton.addActionListener(e -> handleSearch());
        searchPanel.add(searchButton, gbc);
        
        return searchPanel;
    }
    
    private JPanel createResultsPanel() {
        JPanel resultsPanel = UIStyles.createStyledPanel();
        resultsPanel.setLayout(new BorderLayout(10, 10));
        
        JLabel titleLabel = UIStyles.createSectionLabel("Available Trains");
        resultsPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Create modern table
        String[] columnNames = {"Train No.", "Train Name", "Type", "Departure", "Arrival", "Seats", "Fare (₹)", "Action"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7; // Only Action column is editable
            }
        };
        
        trainTable = new JTable(tableModel);
        trainTable.setFont(UIStyles.NORMAL_FONT);
        trainTable.setRowHeight(45);
        trainTable.setShowGrid(false);
        trainTable.setIntercellSpacing(new Dimension(0, 0));
        trainTable.setSelectionBackground(new Color(232, 240, 254));
        trainTable.setSelectionForeground(UIStyles.TEXT_PRIMARY);
        
        // Header styling
        trainTable.getTableHeader().setFont(UIStyles.SUBHEADING_FONT);
        trainTable.getTableHeader().setBackground(UIStyles.PRIMARY_COLOR);
        trainTable.getTableHeader().setForeground(UIStyles.WHITE);
        trainTable.getTableHeader().setPreferredSize(new Dimension(0, 40));
        trainTable.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        
        // Center align cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < trainTable.getColumnCount() - 1; i++) {
            trainTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Add button column
        trainTable.getColumn("Action").setCellRenderer(new ButtonRenderer());
        trainTable.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox()));
        
        // Striped rows
        trainTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
        
        JScrollPane scrollPane = new JScrollPane(trainTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
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
            setContentAreaFilled(true);
        }
        
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Book" : value.toString());
            setBackground(UIStyles.SUCCESS_COLOR);
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 13));
            setBorderPainted(true);
            setBorder(BorderFactory.createLineBorder(UIStyles.SUCCESS_COLOR.darker(), 1));
            setFocusPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
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
            button.setContentAreaFilled(true);
            button.setBackground(UIStyles.SUCCESS_COLOR);
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Segoe UI", Font.BOLD, 13));
            button.setBorderPainted(true);
            button.setBorder(BorderFactory.createLineBorder(UIStyles.SUCCESS_COLOR.darker(), 1));
            button.setFocusPainted(false);
            
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
            if (clicked && searchResults != null && row < searchResults.size()) {
                // Open booking dialog
                Train selectedTrain = searchResults.get(row);
                SwingUtilities.invokeLater(() -> openBookingDialog(selectedTrain));
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
