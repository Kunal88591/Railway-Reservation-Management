package gui;

import models.*;
import utils.DatabaseManager;
import utils.SessionManager;
import utils.ValidationUtils;
import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dialog for booking tickets
 */
public class BookingDialog extends JDialog {
    
    private Train train;
    private CustomerDashboard dashboard;
    private JSpinner seatsSpinner;
    private JPanel passengersPanel;
    private List<PassengerForm> passengerForms;
    private JComboBox<String> paymentCombo;
    private JLabel totalFareLabel;
    
    public BookingDialog(Frame parent, Train train, CustomerDashboard dashboard) {
        super(parent, "Book Tickets - " + train.getTrainName(), true);
        this.train = train;
        this.dashboard = dashboard;
        this.passengerForms = new ArrayList<>();
        initializeUI();
    }
    
    private void initializeUI() {
        setSize(700, 600);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout(10, 10));
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(UIStyles.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Train details
        mainPanel.add(createTrainDetailsPanel(), BorderLayout.NORTH);
        
        // Passenger details
        JScrollPane scrollPane = new JScrollPane(createPassengerPanel());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Bottom panel with total and buttons
        mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JPanel createTrainDetailsPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 5));
        panel.setBackground(UIStyles.LIGHT_COLOR);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIStyles.PRIMARY_COLOR, 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        panel.add(UIStyles.createStyledLabel("Train:", UIStyles.SUBHEADING_FONT));
        panel.add(UIStyles.createStyledLabel(train.getTrainNumber() + " - " + train.getTrainName(), UIStyles.NORMAL_FONT));
        
        panel.add(UIStyles.createStyledLabel("Route:", UIStyles.SUBHEADING_FONT));
        panel.add(UIStyles.createStyledLabel(train.getSource() + " - " + train.getDestination(), UIStyles.NORMAL_FONT));
        
        panel.add(UIStyles.createStyledLabel("Available Seats:", UIStyles.SUBHEADING_FONT));
        panel.add(UIStyles.createStyledLabel(String.valueOf(train.getAvailableSeats()), UIStyles.NORMAL_FONT));
        
        return panel;
    }
    
    private JPanel createPassengerPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(UIStyles.WHITE);
        
        // Number of passengers
        JPanel seatsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        seatsPanel.setBackground(UIStyles.WHITE);
        seatsPanel.add(UIStyles.createStyledLabel("Number of Passengers:", UIStyles.SUBHEADING_FONT));
        
        seatsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Math.min(6, train.getAvailableSeats()), 1));
        seatsSpinner.setFont(UIStyles.NORMAL_FONT);
        seatsSpinner.setPreferredSize(new Dimension(80, 30));
        seatsSpinner.addChangeListener(e -> updatePassengerForms());
        seatsPanel.add(seatsSpinner);
        
        panel.add(seatsPanel);
        panel.add(Box.createVerticalStrut(15));
        
        // Passengers panel (will be populated dynamically)
        passengersPanel = new JPanel();
        passengersPanel.setLayout(new BoxLayout(passengersPanel, BoxLayout.Y_AXIS));
        passengersPanel.setBackground(UIStyles.WHITE);
        panel.add(passengersPanel);
        
        // Initial passenger form
        updatePassengerForms();
        
        return panel;
    }
    
    private void updatePassengerForms() {
        int numberOfSeats = (Integer) seatsSpinner.getValue();
        passengersPanel.removeAll();
        passengerForms.clear();
        
        for (int i = 0; i < numberOfSeats; i++) {
            PassengerForm form = new PassengerForm(i + 1);
            passengerForms.add(form);
            passengersPanel.add(form);
            passengersPanel.add(Box.createVerticalStrut(10));
        }
        
        updateTotalFare();
        passengersPanel.revalidate();
        passengersPanel.repaint();
    }
    
    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(UIStyles.WHITE);
        
        // Payment and total
        JPanel paymentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        paymentPanel.setBackground(UIStyles.WHITE);
        
        paymentPanel.add(UIStyles.createStyledLabel("Payment Method:", UIStyles.SUBHEADING_FONT));
        String[] paymentMethods = {"Credit Card", "Debit Card", "UPI", "Net Banking", "Cash"};
        paymentCombo = new JComboBox<>(paymentMethods);
        paymentCombo.setFont(UIStyles.NORMAL_FONT);
        paymentCombo.setPreferredSize(new Dimension(150, 30));
        paymentPanel.add(paymentCombo);
        
        totalFareLabel = UIStyles.createStyledLabel("Total Fare: Rs" + train.getFare(), UIStyles.HEADING_FONT);
        totalFareLabel.setForeground(UIStyles.SUCCESS_COLOR);
        paymentPanel.add(Box.createHorizontalStrut(30));
        paymentPanel.add(totalFareLabel);
        
        panel.add(paymentPanel, BorderLayout.NORTH);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(UIStyles.WHITE);
        
        JButton confirmButton = UIStyles.createStyledButton("Confirm Booking", UIStyles.SUCCESS_COLOR);
        confirmButton.setPreferredSize(new Dimension(150, 40));
        confirmButton.addActionListener(e -> handleBooking());
        buttonPanel.add(confirmButton);
        
        JButton cancelButton = UIStyles.createStyledButton("Cancel", UIStyles.DANGER_COLOR);
        cancelButton.setPreferredSize(new Dimension(120, 40));
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(cancelButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void updateTotalFare() {
        int seats = (Integer) seatsSpinner.getValue();
        double total = train.getFare() * seats;
        totalFareLabel.setText(String.format("Total Fare: Rs%.2f", total));
    }
    
    private void handleBooking() {
        try {
            // Validate passenger details
            List<Passenger> passengers = new ArrayList<>();
            DatabaseManager dbManager = DatabaseManager.getInstance();
            
            for (PassengerForm form : passengerForms) {
                Passenger passenger = form.getPassenger();
                dbManager.addPassenger(passenger);
                passengers.add(passenger);
            }
            
            // Check seat availability
            int requestedSeats = passengers.size();
            if (train.getAvailableSeats() < requestedSeats) {
                throw new InsufficientSeatsException(requestedSeats, train.getAvailableSeats());
            }
            
            // Book seats
            if (!train.bookSeats(requestedSeats)) {
                throw new BookingException("Failed to book seats");
            }
            
            // Create booking
            String pnr = dbManager.generatePnr();
            String userId = SessionManager.getInstance().getCurrentUser().getUserId();
            double totalFare = train.getFare() * requestedSeats;
            String paymentMethod = (String) paymentCombo.getSelectedItem();
            
            Booking booking = new Booking(pnr, userId, train, passengers, totalFare, paymentMethod);
            dbManager.addBooking(booking);
            dbManager.updateTrain(train);
            
            // Show confirmation
            String message = String.format(
                "Booking Successful!\n\nPNR: %s\nTrain: %s\nPassengers: %d\nTotal Fare: Rs%.2f\n\nSeats: %s",
                pnr, train.getTrainName(), requestedSeats, totalFare, booking.getSeatNumbers()
            );
            UIStyles.showSuccess(this, message);
            
            dispose();
            dashboard.refreshMyBookings();
            
        } catch (ValidationException | BookingException ex) {
            UIStyles.showError(this, ex.getMessage());
        }
    }
    
    // Inner class for passenger form
    class PassengerForm extends JPanel {
        private JTextField nameField;
        private JSpinner ageSpinner;
        private JComboBox<String> genderCombo;
        private JComboBox<String> idProofCombo;
        private JTextField idNumberField;
        
        public PassengerForm(int passengerNumber) {
            setBackground(UIStyles.LIGHT_COLOR);
            setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(UIStyles.PRIMARY_COLOR),
                "Passenger " + passengerNumber,
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                UIStyles.SUBHEADING_FONT
            ));
            
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 10, 5, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            
            // Name
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(UIStyles.createStyledLabel("Name:", UIStyles.NORMAL_FONT), gbc);
            
            gbc.gridx = 1;
            nameField = UIStyles.createStyledTextField();
            nameField.setPreferredSize(new Dimension(200, 30));
            add(nameField, gbc);
            
            // Age
            gbc.gridx = 2;
            add(UIStyles.createStyledLabel("Age:", UIStyles.NORMAL_FONT), gbc);
            
            gbc.gridx = 3;
            ageSpinner = new JSpinner(new SpinnerNumberModel(25, 1, 120, 1));
            ageSpinner.setPreferredSize(new Dimension(80, 30));
            add(ageSpinner, gbc);
            
            // Gender
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(UIStyles.createStyledLabel("Gender:", UIStyles.NORMAL_FONT), gbc);
            
            gbc.gridx = 1;
            String[] genders = {"Male", "Female", "Other"};
            genderCombo = new JComboBox<>(genders);
            genderCombo.setPreferredSize(new Dimension(200, 30));
            add(genderCombo, gbc);
            
            // ID Proof Type
            gbc.gridx = 2;
            add(UIStyles.createStyledLabel("ID Type:", UIStyles.NORMAL_FONT), gbc);
            
            gbc.gridx = 3;
            String[] idTypes = {"Aadhaar", "PAN", "Passport", "Driving License"};
            idProofCombo = new JComboBox<>(idTypes);
            idProofCombo.setPreferredSize(new Dimension(150, 30));
            add(idProofCombo, gbc);
            
            // ID Number
            gbc.gridx = 0;
            gbc.gridy = 2;
            add(UIStyles.createStyledLabel("ID Number:", UIStyles.NORMAL_FONT), gbc);
            
            gbc.gridx = 1;
            gbc.gridwidth = 3;
            idNumberField = UIStyles.createStyledTextField();
            idNumberField.setPreferredSize(new Dimension(400, 30));
            add(idNumberField, gbc);
        }
        
        public Passenger getPassenger() throws ValidationException {
            String name = nameField.getText().trim();
            int age = (Integer) ageSpinner.getValue();
            String gender = (String) genderCombo.getSelectedItem();
            String idType = (String) idProofCombo.getSelectedItem();
            String idNumber = idNumberField.getText().trim();
            
            ValidationUtils.validateName(name);
            ValidationUtils.validateAge(age);
            
            if (idNumber.isEmpty()) {
                throw new ValidationException("ID number cannot be empty");
            }
            
            String passengerId = DatabaseManager.getInstance().generatePassengerId();
            User currentUser = SessionManager.getInstance().getCurrentUser();
            
            return new Passenger(passengerId, name, age, gender,
                               currentUser.getEmail(), currentUser.getPhoneNumber(),
                               idType, idNumber);
        }
    }
}
