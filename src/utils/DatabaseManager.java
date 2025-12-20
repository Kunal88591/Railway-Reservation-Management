package utils;

import models.*;
import exceptions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Database Manager - In-memory data storage (can be extended to use actual database)
 */
public class DatabaseManager {
    
    private static DatabaseManager instance;
    
    // In-memory data storage
    private Map<String, User> users;
    private Map<String, Train> trains;
    private Map<String, Booking> bookings;
    private Map<String, Passenger> passengers;
    
    private DatabaseManager() {
        users = new ConcurrentHashMap<>();
        trains = new ConcurrentHashMap<>();
        bookings = new ConcurrentHashMap<>();
        passengers = new ConcurrentHashMap<>();
        
        initializeSampleData();
    }
    
    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }
    
    /**
     * Initialize sample data for testing
     */
    private void initializeSampleData() {
        // Add admin user
        User admin = new User("U001", "admin", "admin123", "System Admin",
                "admin@railway.com", "9876543210", User.UserRole.ADMIN);
        users.put(admin.getUserId(), admin);
        
        // Add sample trains
        LocalDate today = LocalDate.now();
        addTrain(new Train("12345", "Rajdhani Express", "Delhi", "Mumbai",
                LocalTime.of(16, 0), LocalTime.of(8, 30), 100, 1500.0, "Superfast", today));
        addTrain(new Train("12346", "Shatabdi Express", "Delhi", "Chandigarh",
                LocalTime.of(7, 40), LocalTime.of(11, 10), 80, 800.0, "Express", today));
        addTrain(new Train("12347", "Duronto Express", "Mumbai", "Kolkata",
                LocalTime.of(22, 0), LocalTime.of(18, 0), 120, 2000.0, "Superfast", today));
        addTrain(new Train("12348", "Garib Rath", "Delhi", "Bangalore",
                LocalTime.of(20, 0), LocalTime.of(6, 0), 90, 1200.0, "Express", today));
        addTrain(new Train("12349", "Humsafar Express", "Chennai", "Delhi",
                LocalTime.of(19, 30), LocalTime.of(23, 30), 85, 1800.0, "Superfast", today));
        
        // Add trains for tomorrow as well
        LocalDate tomorrow = today.plusDays(1);
        addTrain(new Train("12345", "Rajdhani Express", "Delhi", "Mumbai",
                LocalTime.of(16, 0), LocalTime.of(8, 30), 100, 1500.0, "Superfast", tomorrow));
        addTrain(new Train("12346", "Shatabdi Express", "Delhi", "Chandigarh",
                LocalTime.of(7, 40), LocalTime.of(11, 10), 80, 800.0, "Express", tomorrow));
    }
    
    // User operations
    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }
    
    public User getUserById(String userId) {
        return users.get(userId);
    }
    
    public User getUserByUsername(String username) {
        return users.values().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
    
    public boolean usernameExists(String username) {
        return getUserByUsername(username) != null;
    }
    
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
    
    // Train operations
    public void addTrain(Train train) {
        String key = train.getTrainNumber() + "_" + train.getOperationDate();
        trains.put(key, train);
    }
    
    public Train getTrainByNumberAndDate(String trainNumber, LocalDate date) {
        String key = trainNumber + "_" + date;
        return trains.get(key);
    }
    
    public List<Train> searchTrains(String source, String destination, LocalDate date) {
        return trains.values().stream()
                .filter(t -> t.getSource().equalsIgnoreCase(source) &&
                           t.getDestination().equalsIgnoreCase(destination) &&
                           t.getOperationDate().equals(date))
                .collect(Collectors.toList());
    }
    
    public List<Train> getAllTrains() {
        return new ArrayList<>(trains.values());
    }
    
    public void updateTrain(Train train) {
        String key = train.getTrainNumber() + "_" + train.getOperationDate();
        trains.put(key, train);
    }
    
    public void deleteTrain(String trainNumber, LocalDate date) {
        String key = trainNumber + "_" + date;
        trains.remove(key);
    }
    
    // Booking operations
    public void addBooking(Booking booking) {
        bookings.put(booking.getPnr(), booking);
    }
    
    public Booking getBookingByPnr(String pnr) {
        return bookings.get(pnr);
    }
    
    public List<Booking> getBookingsByUserId(String userId) {
        return bookings.values().stream()
                .filter(b -> b.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
    
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings.values());
    }
    
    public void updateBooking(Booking booking) {
        bookings.put(booking.getPnr(), booking);
    }
    
    // Passenger operations
    public void addPassenger(Passenger passenger) {
        passengers.put(passenger.getPassengerId(), passenger);
    }
    
    public Passenger getPassengerById(String passengerId) {
        return passengers.get(passengerId);
    }
    
    public List<Passenger> getAllPassengers() {
        return new ArrayList<>(passengers.values());
    }
    
    // Utility methods
    public String generateUserId() {
        return "U" + String.format("%06d", users.size() + 1);
    }
    
    public String generatePassengerId() {
        return "P" + String.format("%06d", passengers.size() + 1);
    }
    
    public String generatePnr() {
        return "PNR" + System.currentTimeMillis();
    }
    
    /**
     * Get available cities for source/destination
     */
    public Set<String> getAvailableCities() {
        Set<String> cities = new HashSet<>();
        trains.values().forEach(train -> {
            cities.add(train.getSource());
            cities.add(train.getDestination());
        });
        return cities;
    }
}
