package gui;

import models.Train;
import utils.DatabaseManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Panel for managing trains (Admin)
 */
public class ManageTrainsPanel extends JPanel {
    
    private AdminDashboard dashboard;
    private JTable trainsTable;
    private DefaultTableModel tableModel;
    private List<Train> trains;
    
    public ManageTrainsPanel(AdminDashboard dashboard) {
        this.dashboard = dashboard;
        initializeUI();
        loadTrains();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBackground(UIStyles.LIGHT_COLOR);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UIStyles.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel titleLabel = new JLabel("Manage Trains");
        titleLabel.setFont(UIStyles.HEADING_FONT);
        titleLabel.setForeground(UIStyles.PRIMARY_COLOR);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(UIStyles.WHITE);
        
        JButton addButton = UIStyles.createStyledButton("Add Train", UIStyles.SUCCESS_COLOR);
        addButton.setPreferredSize(new Dimension(130, 35));
        addButton.addActionListener(e -> showAddTrainDialog());
        buttonPanel.add(addButton);
        
        JButton refreshButton = UIStyles.createStyledButton("Refresh", UIStyles.SECONDARY_COLOR);
        refreshButton.setPreferredSize(new Dimension(110, 35));
        refreshButton.addActionListener(e -> loadTrains());
        buttonPanel.add(refreshButton);
        
        headerPanel.add(buttonPanel, BorderLayout.EAST);
        
        add(headerPanel, BorderLayout.NORTH);
        
        // Table
        add(createTablePanel(), BorderLayout.CENTER);
    }
    
    private JPanel createTablePanel() {
        JPanel panel = UIStyles.createStyledPanel();
        panel.setLayout(new BorderLayout());
        
        String[] columnNames = {"Train No.", "Name", "Source", "Destination", "Date", 
                               "Departure", "Total Seats", "Available", "Fare", "Actions"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 9;
            }
        };
        
        trainsTable = new JTable(tableModel);
        trainsTable.setFont(UIStyles.NORMAL_FONT);
        trainsTable.setRowHeight(40);
        trainsTable.getTableHeader().setFont(UIStyles.SUBHEADING_FONT);
        trainsTable.getTableHeader().setBackground(UIStyles.PRIMARY_COLOR);
        trainsTable.getTableHeader().setForeground(UIStyles.WHITE);
        
        trainsTable.getColumn("Actions").setCellRenderer(new ButtonRenderer());
        trainsTable.getColumn("Actions").setCellEditor(new ButtonEditor(new JCheckBox()));
        
        JScrollPane scrollPane = new JScrollPane(trainsTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void loadTrains() {
        trains = DatabaseManager.getInstance().getAllTrains();
        tableModel.setRowCount(0);
        
        for (Train train : trains) {
            Object[] row = {
                train.getTrainNumber(),
                train.getTrainName(),
                train.getSource(),
                train.getDestination(),
                train.getOperationDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                train.getDepartureTime(),
                train.getTotalSeats(),
                train.getAvailableSeats(),
                String.format("%.2f", train.getFare()),
                "Delete"
            };
            tableModel.addRow(row);
        }
    }
    
    private void showAddTrainDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Add New Train", true);
        dialog.setSize(600, 500);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Fields
        JTextField trainNumberField = UIStyles.createStyledTextField();
        JTextField trainNameField = UIStyles.createStyledTextField();
        JTextField sourceField = UIStyles.createStyledTextField();
        JTextField destinationField = UIStyles.createStyledTextField();
        JTextField dateField = UIStyles.createStyledTextField();
        dateField.setText(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        JTextField depTimeField = UIStyles.createStyledTextField();
        depTimeField.setText("10:00");
        JTextField arrTimeField = UIStyles.createStyledTextField();
        arrTimeField.setText("18:00");
        JSpinner seatsSpinner = new JSpinner(new SpinnerNumberModel(100, 10, 500, 10));
        JTextField fareField = UIStyles.createStyledTextField();
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Express", "Superfast", "Passenger"});
        
        // Add components
        int row = 0;
        addFormField(panel, gbc, row++, "Train Number:", trainNumberField);
        addFormField(panel, gbc, row++, "Train Name:", trainNameField);
        addFormField(panel, gbc, row++, "Source:", sourceField);
        addFormField(panel, gbc, row++, "Destination:", destinationField);
        addFormField(panel, gbc, row++, "Date (YYYY-MM-DD):", dateField);
        addFormField(panel, gbc, row++, "Departure Time (HH:MM):", depTimeField);
        addFormField(panel, gbc, row++, "Arrival Time (HH:MM):", arrTimeField);
        addFormField(panel, gbc, row++, "Total Seats:", seatsSpinner);
        addFormField(panel, gbc, row++, "Fare:", fareField);
        addFormField(panel, gbc, row++, "Type:", typeCombo);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton saveButton = UIStyles.createStyledButton("Save", UIStyles.SUCCESS_COLOR);
        JButton cancelButton = UIStyles.createStyledButton("Cancel", UIStyles.DANGER_COLOR);
        
        saveButton.addActionListener(e -> {
            try {
                Train train = new Train(
                    trainNumberField.getText().trim(),
                    trainNameField.getText().trim(),
                    sourceField.getText().trim(),
                    destinationField.getText().trim(),
                    LocalTime.parse(depTimeField.getText().trim()),
                    LocalTime.parse(arrTimeField.getText().trim()),
                    (Integer) seatsSpinner.getValue(),
                    Double.parseDouble(fareField.getText().trim()),
                    (String) typeCombo.getSelectedItem(),
                    LocalDate.parse(dateField.getText().trim())
                );
                
                DatabaseManager.getInstance().addTrain(train);
                UIStyles.showSuccess(this, "Train added successfully");
                dialog.dispose();
                loadTrains();
            } catch (Exception ex) {
                UIStyles.showError(this, "Error adding train: " + ex.getMessage());
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        
        dialog.add(new JScrollPane(panel));
        dialog.setVisible(true);
    }
    
    private void addFormField(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        panel.add(UIStyles.createStyledLabel(label, UIStyles.SUBHEADING_FONT), gbc);
        
        gbc.gridx = 1;
        field.setPreferredSize(new Dimension(250, 35));
        panel.add(field, gbc);
    }
    
    // Button Renderer and Editor classes similar to previous panels
    class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value == null ? "" : value.toString());
            setBackground(UIStyles.DANGER_COLOR);
            setForeground(UIStyles.WHITE);
            setFont(UIStyles.NORMAL_FONT);
            return this;
        }
    }
    
    class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private int row;
        
        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.setBackground(UIStyles.DANGER_COLOR);
            button.setForeground(UIStyles.WHITE);
            button.setFont(UIStyles.NORMAL_FONT);
            button.addActionListener(e -> fireEditingStopped());
        }
        
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            this.row = row;
            button.setText(value == null ? "" : value.toString());
            return button;
        }
        
        public Object getCellEditorValue() {
            Train train = trains.get(row);
            if (UIStyles.showConfirmation(ManageTrainsPanel.this, 
                    "Delete train " + train.getTrainNumber() + "?")) {
                DatabaseManager.getInstance().deleteTrain(train.getTrainNumber(), train.getOperationDate());
                loadTrains();
            }
            return "Delete";
        }
    }
}
